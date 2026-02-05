package kg.attractor.edufood.service.impl;

import kg.attractor.edufood.dto.CartItemDto;
import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.service.CartService;
import kg.attractor.edufood.service.DishService;
import kg.attractor.edufood.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final DishService dishService;

    @Override
    @SneakyThrows
    public Map<Integer, Integer> readCartFromCookie(String cookie) {
        Map<Integer, Integer> cart = new HashMap<>();
        log.info("cookieValue {}", cookie);

        if (cookie == null || cookie.isBlank()) {
            return cart;
        }

        String decodeCookie = URLDecoder.decode(cookie, StandardCharsets.UTF_8);

        for (String item : decodeCookie.split(",")) {
            String[] parts = item.split(":");
            cart.put(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1])
            );
        }
        return cart;
    }

    @Override
    @SneakyThrows
    public String writeCartToCookie(Map<Integer, Integer> cart) {
        String raw = cart.entrySet().stream()
                .map(c -> c.getKey() + ":" + c.getValue())
                .collect(Collectors.joining(","));

        log.info("raw: {}", raw);

        return URLEncoder.encode(raw, StandardCharsets.UTF_8);
    }

    @Override
    public void addDish(Map<Integer, Integer> cart, Integer dishId) {
        cart.merge(dishId, 1, Integer::sum);
    }

    @Override
    public void removeDish(Map<Integer, Integer> cart, Integer dishId) {
        cart.remove(dishId);
    }

    @Override
    public List<CartItemDto> buildCartItems(Map<Integer, Integer> cart) {
        List<CartItemDto> items = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            DishDto dish = dishService.findDishById(entry.getKey());

            items.add(ConvertHelper.convertToCartItemDto(dish, entry.getValue()));
        }
        return items;
    }

    @Override
    public Double calculateTotal(List<CartItemDto> items) {
        return items.stream()
                .mapToDouble(i -> i.getDish().getPrice() * i.getQuantity())
                .sum();
    }

    @Override
    public Integer countItems(Map<Integer, Integer> cart) {
        return cart.values().stream().mapToInt(Integer::intValue).sum();
    }
}
