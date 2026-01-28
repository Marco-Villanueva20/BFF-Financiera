package com.retotecnico.msproductosfinancieros.domain.repository;

import java.util.UUID;

import com.retotecnico.model.CuentaAhorroRequest;
import com.retotecnico.model.CuentaAhorroResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CuentaAhorroRepository {

	Mono<CuentaAhorroResponse> createCuentaAhorro(CuentaAhorroRequest request);
	Flux<CuentaAhorroResponse> findCuentaAhorroByClienteId(UUID clienteId);
	Flux<CuentaAhorroResponse> findAllCuentas();
}
