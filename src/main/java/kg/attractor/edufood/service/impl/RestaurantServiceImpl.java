package kg.attractor.edufood.service.impl;

import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.model.Restaurant;
import kg.attractor.edufood.repository.RestaurantRepository;
import kg.attractor.edufood.service.RestaurantService;
import kg.attractor.edufood.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{
    private final RestaurantRepository repository;

    @Override
    public Page<RestaurantDto> showMainPage(Integer page, Integer size, String name){
        Pageable pageable = PageRequest.of(
                page,
                size
        );

        Page<Restaurant> restaurants;

        if(name != null && !name.isBlank()){
            restaurants = repository.findRestaurantsByNameContaining(name, pageable);
        } else {
            restaurants = repository.findAll(pageable);
        }

        return restaurants
                .map(ConvertHelper::convertToRestaurantDto);
    }
}
