package com.retotecnico.msproductosfinancieros.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class CuentaAhorroRequest extends ProductoBaseRequest {
    Integer plazoMeses;
    BigDecimal tasaInteres;
    LocalDate fechaApertura;
}
