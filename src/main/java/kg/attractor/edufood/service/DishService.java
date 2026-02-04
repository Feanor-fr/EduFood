package kg.attractor.edufood.service;

import kg.attractor.edufood.dto.DishDto;
import org.springframework.data.domain.Page;

public interface DishService {
    Page<DishDto> getDishByRestaurantProfile(Integer restaurantId, Integer page, Integer size);
}
