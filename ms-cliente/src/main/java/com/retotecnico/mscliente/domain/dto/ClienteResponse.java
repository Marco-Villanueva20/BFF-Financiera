package com.retotecnico.mscliente.domain.dto;

import com.retotecnico.mscliente.persistence.entity.TipoDocumento;

import java.util.UUID;

public record ClienteResponse(
        UUID id,
        UUID userId,
        String nombres,
        String apellidos,
        TipoDocumento tipoDocumento,
        String numeroDocumento
) {
}
