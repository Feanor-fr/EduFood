package kg.attractor.edufood.service;

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
}
