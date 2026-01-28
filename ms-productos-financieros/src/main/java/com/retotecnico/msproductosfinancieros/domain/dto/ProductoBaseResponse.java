package com.retotecnico.msproductosfinancieros.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

import com.retotecnico.model.TipoCuenta;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoBaseResponse{
    private UUID id;
    private UUID clienteId;
    private TipoCuenta tipoProducto;
    private String nombre;
    private BigDecimal saldo;
}
