package com.retotecnico.msproductosfinancieros.domain.service;

import java.util.UUID;

import com.retotecnico.model.TarjetaCreditoRequest;
import com.retotecnico.model.TarjetaCreditoResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TarjetaCreditoService {

	Mono<TarjetaCreditoResponse> crearTarjetaCredito(TarjetaCreditoRequest request);
    Flux<TarjetaCreditoResponse> findTarjetaCreditoByClienteId(UUID clienteId);
    Flux<TarjetaCreditoResponse> encontrarTarjetasCredito();
}
