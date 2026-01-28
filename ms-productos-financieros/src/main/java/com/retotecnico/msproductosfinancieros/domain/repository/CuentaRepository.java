package com.retotecnico.msproductosfinancieros.domain.repository;

import com.retotecnico.model.*;
import com.retotecnico.msproductosfinancieros.persistence.entity.CuentaEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CuentaRepository {

    Flux<CuentaResponse> findAll();
    Flux<CuentaResponse> findByClienteId(UUID clienteId);
    Mono<CuentaEntity> crearCuenta(CuentaRequest request);
    Flux<CuentaEntity> buscarProductoBasePorClienteId(UUID clienteId);

}
