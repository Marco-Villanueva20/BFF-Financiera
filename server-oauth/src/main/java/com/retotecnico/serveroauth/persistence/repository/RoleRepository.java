package com.retotecnico.serveroauth.persistence.repository;

import com.retotecnico.serveroauth.persistence.entity.Role;
import com.retotecnico.serveroauth.persistence.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRole(RoleName roleName);
}
