package kg.attractor.edufood.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.attractor.edufood.dto.CartItemDto;
import kg.attractor.edufood.service.CartService;
import kg.attractor.edufood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final OrderService orderService;

    @GetMapping
    public String showCart(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model
    ) {
        String cookieName = cartService.getCardOwnerForCookie();
        String cartValue= cartService.getCookieValue(request, cookieName);

        Map<Integer, Integer> cart = cartService.readCartFromCookie(cartValue);
        List<CartItemDto> items = cartService.buildCartItems(cart);
        Double total = cartService.calculateTotal(items);

        model.addAttribute("items", items);
        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(
            @RequestParam Integer dishId,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String cookieName = cartService.getCardOwnerForCookie();
        String cartValue = cartService.getCookieValue(request, cookieName);

        Map<Integer, Integer> cart = cartService.readCartFromCookie(cartValue);
        cartService.addDish(cart, dishId);

        cartService.saveCartCookie(response, cookieName, cart);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(
            @RequestParam Integer dishId,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String cookieName = cartService.getCardOwnerForCookie();
        String cartValue = cartService.getCookieValue(request, cookieName);

        Map<Integer, Integer> cart = cartService.readCartFromCookie(cartValue);
        cartService.removeDish(cart, dishId);

        cartService.saveCartCookie(response, cookieName, cart);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(HttpServletRequest request,
                           HttpServletResponse response
    ) {
        String cookieName = cartService.getCardOwnerForCookie();
        String cartValue = cartService.getCookieValue(request, cookieName);

        if (cartValue != null && !cartValue.isBlank()) {
            orderService.create(cartValue);

            Cookie cookie = new Cookie(cookieName, "");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        return "redirect:/users/profile";
    }

}
