package kg.attractor.edufood.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.attractor.edufood.dto.CartItemDto;

import java.util.List;
import java.util.Map;

public interface CartService {

    Map<Integer, Integer> readCartFromCookie(String cookieValue);

    String writeCartToCookie(Map<Integer, Integer> cart);

    void addDish(Map<Integer, Integer> cart, Integer dishId);

    void removeDish(Map<Integer, Integer> cart, Integer dishId);

    List<CartItemDto> buildCartItems(Map<Integer, Integer> cart);

    Double calculateTotal(List<CartItemDto> items);

    Integer countItems(Map<Integer, Integer> cart);

    String getCardOwnerForCookie();

    String getCookieValue(HttpServletRequest request, String name);

    String mergeAndClearAnonymousCart(HttpServletRequest request, HttpServletResponse response, String personalName);

    void saveCartCookie(HttpServletResponse response, String name, Map<Integer, Integer> cart);

    void mergeCartsOnLogin(HttpServletRequest request, HttpServletResponse response);
}
