package com.retotecnico.mscliente.domain.dto;

import com.retotecnico.mscliente.persistence.entity.TipoDocumento;

import java.util.UUID;

public record ClienteRequest(
        UUID userId,
        String nombres,
        String apellidos,
        TipoDocumento tipoDocumento,
        String numeroDocumento
) {
}
