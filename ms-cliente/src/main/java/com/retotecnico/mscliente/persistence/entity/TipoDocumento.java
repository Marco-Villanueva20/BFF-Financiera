package com.retotecnico.mscliente.persistence.entity;

import lombok.Getter;

@Getter
public enum TipoDocumento {
    DNI( 8),
    CE(12),
    RUC( 11);

    private final int longitud;


    TipoDocumento(int longitud) {
        this.longitud = longitud;
    }

}
