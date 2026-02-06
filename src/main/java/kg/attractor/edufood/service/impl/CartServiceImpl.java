package kg.attractor.edufood.service.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.attractor.edufood.dto.CartItemDto;
import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.UserDto;
import kg.attractor.edufood.service.CartService;
import kg.attractor.edufood.service.DishService;
import kg.attractor.edufood.utils.ConvertHelper;
import kg.attractor.edufood.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final DishService dishService;
    private final UserHelper userHelper;

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

    @Override
    public String getCardOwnerForCookie(){
        try {
            UserDto userAuth = userHelper.getAuthUser();

            if(userAuth != null && userAuth.getEmail() != null){
                return "cart_" + userAuth.getEmail().replaceAll("[@.]", "_");
            }
        } catch (Exception e) {

        }

        return "cart";
    }


    @Override
    public String getCookieValue(HttpServletRequest request, String name){
        if (request.getCookies() == null){
            return null;
        }
        return findCookie(request, name);
    }

     @Override
    public String mergeAndClearAnonymousCart(HttpServletRequest request, HttpServletResponse response, String personalName) {
        String personalValue = findCookie(request, personalName);
        String anonValue = findCookie(request, "cart");

        if (anonValue != null && !anonValue.isBlank()) {
            String mergedValue = mergeCookieValues(personalValue, anonValue);

            saveCartCookie(response, personalName, readCartFromCookie(mergedValue));

            Cookie clearAnon = new Cookie("cart", "");
            clearAnon.setPath("/");
            clearAnon.setMaxAge(0);
            response.addCookie(clearAnon);

            return mergedValue;
        }
        return personalValue;
    }

    private String findCookie(HttpServletRequest request, String name) {
        return Arrays.stream(request.getCookies())
                .filter(c -> name.equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }

    private String mergeCookieValues(String personal, String anon) {
        if (personal == null || personal.isBlank()){
            return anon;
        }

        if (anon == null || anon.isBlank()){
            return personal;
        }

        Map<Integer, Integer> pMap = readCartFromCookie(personal);
        Map<Integer, Integer> aMap = readCartFromCookie(anon);
        aMap.forEach((k, v) -> pMap.merge(k, v, Integer::sum));

        return writeCartToCookie(pMap);
    }


    @Override
    public void saveCartCookie(HttpServletResponse response, String name, Map<Integer, Integer> cart) {
        Cookie cookie = new Cookie(name, writeCartToCookie(cart));
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);
    }

    @Override
    public void mergeCartsOnLogin(HttpServletRequest request, HttpServletResponse response) {
        String personalName = getCardOwnerForCookie();
        if (personalName.startsWith("cart_")) {
            mergeAndClearAnonymousCart(request, response, personalName);
        }
    }
}
