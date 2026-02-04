package kg.attractor.edufood.service;

import kg.attractor.edufood.dto.RestaurantDto;
import org.springframework.data.domain.Page;

public interface RestaurantService {
    RestaurantDto getRestaurantById(Integer id);

    Page<RestaurantDto> showMainPage(Integer page, Integer size, String name);
}
