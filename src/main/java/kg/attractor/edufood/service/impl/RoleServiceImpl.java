package kg.attractor.edufood.service.impl;

import kg.attractor.edufood.exceptions.RoleNotFoundException;
import kg.attractor.edufood.model.Role;
import kg.attractor.edufood.repository.RoleRepository;
import kg.attractor.edufood.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public Role findRoleById(Integer id) {

        return repository.findById(id)
                .orElseThrow(RoleNotFoundException::new);
    }
}
