package com.retotecnico.msproductosfinancieros.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.retotecnico.model.TipoCuenta;

import java.math.BigDecimal;
import java.util.UUID;

@Table("cuentas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaEntity {
    @Id
    private UUID id;

    @Column("cliente_id")
    private UUID clienteId;
    @Column("tipo_cuenta")
    private TipoCuenta tipoCuenta;
    private String nombre;
    private BigDecimal saldo;


}
