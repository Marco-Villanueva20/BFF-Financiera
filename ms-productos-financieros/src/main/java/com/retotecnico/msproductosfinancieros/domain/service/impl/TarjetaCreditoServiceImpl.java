package com.retotecnico.msproductosfinancieros.domain.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.retotecnico.model.TarjetaCreditoRequest;
import com.retotecnico.model.TarjetaCreditoResponse;
import com.retotecnico.msproductosfinancieros.domain.repository.TarjetaCreditoRepository;
import com.retotecnico.msproductosfinancieros.domain.service.TarjetaCreditoService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TarjetaCreditoServiceImpl implements TarjetaCreditoService {

	private final TarjetaCreditoRepository tarjetaCreditoRepository;

	@Override
	public Mono<TarjetaCreditoResponse> crearTarjetaCredito(TarjetaCreditoRequest request) {
		// TODO Auto-generated method stub
		return tarjetaCreditoRepository.crearTarjetaCredito(request);
	}

	@Override
	public Flux<TarjetaCreditoResponse> findTarjetaCreditoByClienteId(UUID clienteId) {
		// TODO Auto-generated method stub
		return tarjetaCreditoRepository.encontrarTarjetaCreditoPorClienteId(clienteId);
	}

	@Override
	public Flux<TarjetaCreditoResponse> encontrarTarjetasCredito() {
		// TODO Auto-generated method stub
		return tarjetaCreditoRepository.encontrarTarjetasCredito();
	}

}
