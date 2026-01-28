package com.retotecnico.msproductosfinancieros.domain.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.retotecnico.model.CuentaAhorroRequest;
import com.retotecnico.model.CuentaAhorroResponse;
import com.retotecnico.msproductosfinancieros.domain.repository.CuentaAhorroRepository;
import com.retotecnico.msproductosfinancieros.domain.service.CuentaAhorroService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CuentaAhorroServiceImpl implements CuentaAhorroService {
	private final CuentaAhorroRepository cuentaAhorroRepository;
	
	@Override
	public Mono<CuentaAhorroResponse> crearCuentaAhorro(CuentaAhorroRequest request) {
		return cuentaAhorroRepository.createCuentaAhorro(request);
	}

	@Override
	public Flux<CuentaAhorroResponse> findCuentaAhorroByClienteId(UUID clienteId) {
		return cuentaAhorroRepository.findCuentaAhorroByClienteId(clienteId);
	}

	@Override
	public Flux<CuentaAhorroResponse> findAllCuentas() {
		// TODO Auto-generated method stub
		return cuentaAhorroRepository.findAllCuentas();
	}

}
