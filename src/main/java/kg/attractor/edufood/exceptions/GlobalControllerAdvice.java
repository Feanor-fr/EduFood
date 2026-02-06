package kg.attractor.edufood.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.edufood.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
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
    public Integer getCartCount(HttpServletRequest request) {
        String cookieName = cartService.getCardOwnerForCookie();
        String cartValue = cartService.getCookieValue(request, cookieName);
        Map<Integer, Integer> cart = cartService.readCartFromCookie(cartValue);
        return cartService.countItems(cart);
    }
}
