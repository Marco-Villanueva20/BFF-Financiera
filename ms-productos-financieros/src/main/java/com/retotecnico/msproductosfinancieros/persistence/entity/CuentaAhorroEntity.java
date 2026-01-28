package com.retotecnico.msproductosfinancieros.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter
@Table("cuentas_ahorro")
public class CuentaAhorroEntity {

    @Id
    UUID cuentaId;
    Integer plazoMeses;
    BigDecimal tasaInteres;
    LocalDate fechaApertura;

}
