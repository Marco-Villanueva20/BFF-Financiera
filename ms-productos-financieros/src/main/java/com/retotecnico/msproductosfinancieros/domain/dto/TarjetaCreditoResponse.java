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
public class TarjetaCreditoResponse extends ProductoBaseResponse {
    private UUID productoId;
    private String numeroTarjeta;
    private LocalDate fechaExpiracion;
    private String cvv;
    private BigDecimal limiteCredito;
}
