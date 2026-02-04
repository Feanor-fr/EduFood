package kg.attractor.edufood.service;

import kg.attractor.edufood.dto.RestaurantDto;
import org.springframework.data.domain.Page;

public interface RestaurantService {
    Page<RestaurantDto> showMainPage(Integer page, Integer size, String name);
}
