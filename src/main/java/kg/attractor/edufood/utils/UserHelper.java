package kg.attractor.edufood.utils;

import kg.attractor.edufood.dto.UserDto;
import kg.attractor.edufood.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHelper {
    private final UserService userService;

    public UserDto getAuthUser(){
        return userService
                .getUserByEmail(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
    }

}
