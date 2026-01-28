package com.retotecnico.mscliente.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity {

    @Id
    private UUID id;
    @Column("user_id")
    private UUID userId;

    private String nombres;
    private String apellidos;

    @Column("tipo_documento")
    private TipoDocumento tipoDocumento;
    @Column("numero_documento")
    private String numeroDocumento;

}
