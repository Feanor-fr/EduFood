package kg.attractor.edufood.controller;

import jakarta.servlet.http.Cookie;
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
            @CookieValue(value = "cart", required = false) String cartCookie,
            Model model
    ) {
        Map<Integer, Integer> cart = cartService.readCartFromCookie(cartCookie);

        List<CartItemDto> items = cartService.buildCartItems(cart);
        Double total = cartService.calculateTotal(items);

        model.addAttribute("items", items);
        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(
            @RequestParam Integer dishId,
            @CookieValue(value = "cart", required = false) String cartCookie,
            HttpServletResponse response
    ) {
        Map<Integer, Integer> cart = cartService.readCartFromCookie(cartCookie);
        cartService.addDish(cart, dishId);

        Cookie cookie = new Cookie("cart", cartService.writeCartToCookie(cart));
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(
            @RequestParam Integer dishId,
            @CookieValue(value = "cart", required = false) String cartCookie,
            HttpServletResponse response
    ) {
        Map<Integer, Integer> cart = cartService.readCartFromCookie(cartCookie);
        cartService.removeDish(cart, dishId);

        Cookie cookie = new Cookie("cart", cartService.writeCartToCookie(cart));
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(
            @CookieValue(value = "cart", required = false) String cartCookie,
            HttpServletResponse response
    ) {
        if (cartCookie != null && !cartCookie.isBlank()) {
            orderService.create(cartCookie);

            Cookie cookie = new Cookie("cart", "");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        return "redirect:/users/profile";
    }

}
