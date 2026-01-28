package com.retotecnico.msproductosfinancieros.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Table("tarjetas_credito")
public class TarjetaCreditoEntity {
    @Id
    private UUID cuentaId;

    private String numeroTarjeta;
    private LocalDate fechaExpiracion;
    private String cvv;
    private BigDecimal limiteCredito;
}
