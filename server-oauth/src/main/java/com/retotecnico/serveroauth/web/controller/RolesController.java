package com.retotecnico.serveroauth.web.controller;

import com.retotecnico.serveroauth.domain.dto.RoleRequest;
import com.retotecnico.serveroauth.domain.service.RoleService;
import com.retotecnico.serveroauth.persistence.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolesController {
    private final RoleService roleService;


    @PostMapping
    public ResponseEntity<Role> crear(@RequestBody RoleRequest request, UriComponentsBuilder ucb){
        Role roleCreado = roleService.crearRole(request);
        URI uri = ucb.path("/roles/{id}")
                .buildAndExpand(roleCreado.getId()).toUri();

        return  ResponseEntity.created(uri).body(roleCreado);
    }
}
