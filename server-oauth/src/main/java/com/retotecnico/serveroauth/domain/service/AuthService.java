package com.retotecnico.serveroauth.domain.service;

import com.retotecnico.serveroauth.domain.dto.RegisterRequest;
import com.retotecnico.serveroauth.persistence.entity.Role;
import com.retotecnico.serveroauth.persistence.entity.User;
import com.retotecnico.serveroauth.persistence.enums.RoleName;
import com.retotecnico.serveroauth.persistence.repository.RoleRepository;
import com.retotecnico.serveroauth.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    public void register(RegisterRequest request) {
        userRepository.findByCorreo(request.correo())
                .ifPresent(u -> { throw new RuntimeException("El usuario ya existe"); });

        User user = new User();
        user.setCorreo(request.correo());
        user.setNombres(request.nombres());
        user.setApellidos(request.apellidos());
        user.setPassword(passwordEncoder.encode(request.password()));
        Set<Role> roles = new HashSet<>();
        request.roles().forEach(r -> {
            Role role = roleRepository.findByRole(RoleName.valueOf(r))
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(role);
        });

        user.setRoles(roles);
        userRepository.save(user);
    }
}
