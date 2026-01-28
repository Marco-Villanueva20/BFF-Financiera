package com.retotecnico.serveroauth.domain.dto;

import java.util.List;

public record RegisterRequest(
        String nombres,
        String apellidos,
        String correo,
        String password,
        List<String> roles
) {
}
