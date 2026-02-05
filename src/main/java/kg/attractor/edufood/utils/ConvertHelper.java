package kg.attractor.edufood.utils;

import kg.attractor.edufood.dto.*;
import kg.attractor.edufood.model.Dish;
import kg.attractor.edufood.model.Order;
import kg.attractor.edufood.model.Restaurant;
import kg.attractor.edufood.model.User;

import java.time.LocalDate;

public class ConvertHelper {

    private ConvertHelper(){}

    public static RestaurantDto convertToRestaurantDto(Restaurant restaurant){

        return
                RestaurantDto
                        .builder()
                        .id(restaurant.getId())
                        .name(restaurant.getName())
                        .description(restaurant.getDescription())
                        .build();
    }

    public static User convertTouser(UserDto userDto){

        return
                User.builder()
                        .email(userDto.getEmail())
                        .password(userDto.getPassword())
                        .enabled(true)
                        .build();
    }

    public static UserDto convertToUserDto(User user){

        return
                UserDto.builder()
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .build();
    }

    public static DishDto convertToDishDto(Dish dish){

        return
                DishDto.builder()
                        .id(dish.getId())
                        .name(dish.getName())
                        .price(dish.getPrice())
                        .restaurantName(dish.getRestaurant().getName())
                        .build();
    }

    public static OrderDto convertToOrderDto(Order order){

        return
                OrderDto.builder()
                        .id(order.getId())
                        .totalSum(order.getTotalSum())
                        .createdDate(order.getCreatedDate())
                        .build();
    }

    public static Order convertToOrder(User user, Double totalSum){

        return
                Order.builder()
                        .customer(user)
                        .totalSum(totalSum)
                        .createdDate(LocalDate.now())
                        .build();
    }

    public static CartItemDto convertToCartItemDto(DishDto dish, Integer quantity){
        return
                CartItemDto.builder()
                        .dish(dish)
                        .quantity(quantity)
                        .build();
    }
}
