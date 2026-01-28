package com.retotecnico.serveroauth.domain.service;

import com.retotecnico.serveroauth.domain.dto.RoleRequest;
import com.retotecnico.serveroauth.persistence.entity.Role;
import com.retotecnico.serveroauth.persistence.enums.RoleName;
import com.retotecnico.serveroauth.persistence.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role crearRole(RoleRequest roleRequest) {
        Role role = new Role();
        role.setRole(RoleName.valueOf(roleRequest.roleName()));
        return roleRepository.save(role);
    }

}
