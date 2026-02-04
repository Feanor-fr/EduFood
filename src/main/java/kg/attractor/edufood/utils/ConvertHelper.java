package kg.attractor.edufood.utils;

import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.model.Restaurant;

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
}
