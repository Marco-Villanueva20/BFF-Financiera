package com.retotecnico.msproductosfinancieros.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CuentaAhorroResponse extends ProductoBaseResponse {
    UUID productoId;
    Integer plazoMeses;
    BigDecimal tasaInteres;
    LocalDate fechaApertura;
}
