package com.retotecnico.msbff.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ClienteResponseDTO {
    private UUID id;
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private List<ProductDTO> productos;
}
