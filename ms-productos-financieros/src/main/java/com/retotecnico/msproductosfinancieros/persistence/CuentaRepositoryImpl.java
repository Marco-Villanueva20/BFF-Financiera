package com.retotecnico.msproductosfinancieros.persistence;

import com.retotecnico.model.*;
import com.retotecnico.msproductosfinancieros.domain.exception.ResourceNotFoundException;
import com.retotecnico.msproductosfinancieros.domain.repository.CuentaRepository;
import com.retotecnico.msproductosfinancieros.persistence.entity.CuentaEntity;
import com.retotecnico.msproductosfinancieros.persistence.mapper.CuentaMapper;
import com.retotecnico.msproductosfinancieros.persistence.repository.CuentaR2dbcRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CuentaRepositoryImpl implements CuentaRepository {

	private final CuentaR2dbcRepository productoR2dbcRepository;

	private final R2dbcEntityTemplate entityTemplate;
	private final CuentaMapper productoMapper;

	@Override
	public Flux<CuentaResponse> findByClienteId(UUID id) {
		return buscarProductoBasePorClienteId(id).map(productoMapper::toResponse);
	}

	@Override
	public Flux<CuentaEntity> buscarProductoBasePorClienteId(UUID clienteId) {
		return productoR2dbcRepository.findByClienteId(clienteId).switchIfEmpty(
				Flux.error(new ResourceNotFoundException("El cliente no tiene ningún producto en el banco")));
	}

	@Override
	public Mono<CuentaEntity> crearCuenta(CuentaRequest request) {
		CuentaEntity productoEntity = productoMapper.toEntity(request);
		return productoR2dbcRepository.save(productoEntity);
	}

	@Override
	public Flux<CuentaResponse> findAll() {
		return productoR2dbcRepository.findAll().map(productoMapper::toResponse);
	}
}
