package kg.attractor.edufood.controller;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.service.DishService;
import kg.attractor.edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final DishService dishService;

    @GetMapping
    public String showAll(Model model,
                          @RequestParam(defaultValue = "0") Integer page,
                          @RequestParam(defaultValue = "5") Integer size,
                          @RequestParam(required = false) String name){

        Page<RestaurantDto> restaurantPage = restaurantService
                .showMainPage(page, size, name);

        model.addAttribute("restaurants", restaurantPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", restaurantPage.getTotalPages());
        model.addAttribute("name", name);

        return "restaurants/home";
    }

    @GetMapping("{id}")
    public String showRestaurantProfile(@PathVariable Integer id,
                                        @RequestParam(defaultValue = "0") Integer page,
                                        @RequestParam(defaultValue = "10") Integer size,
                                        Model model){

        RestaurantDto restaurant = restaurantService.getRestaurantById(id);
        model.addAttribute("restaurant", restaurant);

        Page<DishDto> dishPage = dishService.getDishByRestaurantProfile(restaurant.getId(), page, size);
        model.addAttribute("dishes", dishPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", dishPage.getTotalPages());

        return "restaurants/restProfile";
    }
}
