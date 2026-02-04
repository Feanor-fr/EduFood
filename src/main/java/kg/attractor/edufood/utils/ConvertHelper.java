package kg.attractor.edufood.utils;

import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.dto.UserDto;
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
}
