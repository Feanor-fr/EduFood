package kg.attractor.edufood.service.impl;

import kg.attractor.edufood.dto.UserDto;
import kg.attractor.edufood.exceptions.UserAlreadyExistsException;
import kg.attractor.edufood.model.User;
import kg.attractor.edufood.repository.UserRepository;
import kg.attractor.edufood.service.RoleService;
import kg.attractor.edufood.service.UserService;
import kg.attractor.edufood.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final RoleService roleService;

    @Override
    public void create(UserDto userDto){

        if (repository.existsById(userDto.getEmail())) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует!");
        }

        User user = ConvertHelper.convertTouser(userDto);
        user.setRole(roleService.findRoleById(1));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        repository.saveAndFlush(user);
    }
}
