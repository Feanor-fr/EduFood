package kg.attractor.edufood.service.impl;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.repository.DishRepository;
import kg.attractor.edufood.service.DishService;
import kg.attractor.edufood.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepository repository;

    @Override
    public Page<DishDto> getDishByRestaurantProfile(Integer restaurantId, Integer page, Integer size) {

        Pageable pageable = PageRequest.of(
                page,
                size
        );

        return repository.findDishesByRestaurant_Id(restaurantId, pageable)
                .map(ConvertHelper::convertToDishDto);
    }
}
