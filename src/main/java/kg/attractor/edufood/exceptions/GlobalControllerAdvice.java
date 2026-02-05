package kg.attractor.edufood.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.edufood.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final CartService cartService;


    @ExceptionHandler(NoSuchElementException.class)
    public String notFound(Model model, HttpServletRequest request){
       model.addAttribute("status", HttpStatus.NOT_FOUND.value());
       model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
       model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String businessError(RuntimeException r,Model model, HttpServletRequest request){
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("reason", r.getMessage());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ModelAttribute("cartCount")
    public Integer getCartCount(@CookieValue(value = "cart", required = false) String cartCookie) {
        if (cartCookie == null || cartCookie.isBlank()) {
            return 0;
        }
        Map<Integer, Integer> cart = cartService.readCartFromCookie(cartCookie);

        return cartService.countItems(cart);
    }
}
