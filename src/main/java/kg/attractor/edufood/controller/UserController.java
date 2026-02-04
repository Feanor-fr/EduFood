package kg.attractor.edufood.controller;

import kg.attractor.edufood.dto.OrderDto;
import kg.attractor.edufood.dto.UserDto;
import kg.attractor.edufood.service.OrderService;
import kg.attractor.edufood.service.UserService;
import kg.attractor.edufood.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserHelper userHelper;
    private final OrderService orderService;


    @GetMapping("profile")
    public String showProfile(Model model,
                              @RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "5") Integer size){

        UserDto userAuth = userHelper.getAuthUser();
        model.addAttribute("user", userService.getUserByEmail(userAuth.getEmail()));

            Page<OrderDto> orderPage = orderService.getOrdersByCustomer(userAuth.getEmail(), page, size);
            model.addAttribute("orders", orderPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", orderPage.getTotalPages());


        return "users/profile";
    }
}
