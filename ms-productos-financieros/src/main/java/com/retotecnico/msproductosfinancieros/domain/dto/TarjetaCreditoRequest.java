package com.retotecnico.msproductosfinancieros.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TarjetaCreditoRequest extends ProductoBaseRequest {
    private String numeroTarjeta;
    private LocalDate fechaExpiracion;
    private String cvv;
    private BigDecimal limiteCredito;
}
