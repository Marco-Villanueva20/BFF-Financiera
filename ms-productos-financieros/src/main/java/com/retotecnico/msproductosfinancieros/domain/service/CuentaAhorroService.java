package com.retotecnico.msproductosfinancieros.domain.service;

import java.util.UUID;

import com.retotecnico.model.CuentaAhorroRequest;
import com.retotecnico.model.CuentaAhorroResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CuentaAhorroService {

	 Mono<CuentaAhorroResponse> crearCuentaAhorro(CuentaAhorroRequest request);
	 
	 Flux<CuentaAhorroResponse> findCuentaAhorroByClienteId(UUID clienteId);
	 Flux<CuentaAhorroResponse> findAllCuentas();

}
