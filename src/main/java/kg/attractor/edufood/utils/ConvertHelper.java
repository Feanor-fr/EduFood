package kg.attractor.edufood.utils;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.OrderDto;
import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.dto.UserDto;
import kg.attractor.edufood.model.Dish;
import kg.attractor.edufood.model.Order;
import kg.attractor.edufood.model.Restaurant;
import kg.attractor.edufood.model.User;

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
}
