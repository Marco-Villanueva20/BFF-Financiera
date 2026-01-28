package com.retotecnico.msproductosfinancieros.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

import com.retotecnico.model.TipoCuenta;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductoBaseRequest {
        @NotNull(message = "El cliente es obligatorio")
        private UUID clienteId;

        @NotBlank(message = "El nombre es requerido")
        private String nombre;
        @NotNull(message = "El tipo de producto es requerido")
        TipoCuenta tipoProducto;
        @PositiveOrZero(message = "El saldo no puede ser negativo")
        private BigDecimal saldo;
}
