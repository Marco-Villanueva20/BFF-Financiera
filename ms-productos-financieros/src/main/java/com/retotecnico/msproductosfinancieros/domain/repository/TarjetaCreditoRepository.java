package com.retotecnico.msproductosfinancieros.domain.repository;

import java.util.UUID;

import com.retotecnico.model.TarjetaCreditoRequest;
import com.retotecnico.model.TarjetaCreditoResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TarjetaCreditoRepository {
	Mono<TarjetaCreditoResponse> crearTarjetaCredito(TarjetaCreditoRequest request);
	Flux<TarjetaCreditoResponse> encontrarTarjetaCreditoPorClienteId(UUID clienId);
	Flux<TarjetaCreditoResponse> encontrarTarjetasCredito();
}
