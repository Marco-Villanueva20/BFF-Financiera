package com.retotecnico.msproductosfinancieros.persistence;

import java.util.UUID;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.retotecnico.model.TarjetaCreditoRequest;
import com.retotecnico.model.TarjetaCreditoResponse;
import com.retotecnico.model.TipoCuenta;
import com.retotecnico.msproductosfinancieros.domain.exception.ResourceNotFoundException;
import com.retotecnico.msproductosfinancieros.domain.repository.CuentaRepository;
import com.retotecnico.msproductosfinancieros.domain.repository.TarjetaCreditoRepository;
import com.retotecnico.msproductosfinancieros.persistence.entity.CuentaEntity;
import com.retotecnico.msproductosfinancieros.persistence.entity.TarjetaCreditoEntity;
import com.retotecnico.msproductosfinancieros.persistence.mapper.CuentaMapper;
import com.retotecnico.msproductosfinancieros.persistence.mapper.TarjetaCreditoMapper;
import com.retotecnico.msproductosfinancieros.persistence.repository.TarjetaCreditoR2dbcRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class TarjetaCreditoRepositoryImpl implements TarjetaCreditoRepository {

	private final CuentaRepository cuentaRepository;
	private final TarjetaCreditoR2dbcRepository tarjetaCreditoR2dbcRepository;
	private final TarjetaCreditoMapper tarjetaCreditoMapper;
	private final CuentaMapper cuentaMapper;
	private final R2dbcEntityTemplate entityTemplate;

	@Override
	@Transactional
	public Mono<TarjetaCreditoResponse> crearTarjetaCredito(TarjetaCreditoRequest request) {
		return cuentaRepository.crearCuenta(request).flatMap(productoGuardado -> {
			TarjetaCreditoEntity detalleEntity = tarjetaCreditoMapper.toTarjetaCreditoEntity(request);
			detalleEntity.setCuentaId(productoGuardado.getId());
			return entityTemplate.insert(detalleEntity).map(detalleGuardado -> tarjetaCreditoMapper
					.toTarjetaCreditoResponse(productoGuardado, detalleGuardado));

		});
	}

	@Override
	public Flux<TarjetaCreditoResponse> encontrarTarjetaCreditoPorClienteId(UUID clienteId) {
		return cuentaRepository.buscarProductoBasePorClienteId(clienteId)
				.filter(producto -> producto.getTipoCuenta() == TipoCuenta.TARJETA_CREDITO)
				.switchIfEmpty(Flux.error(new ResourceNotFoundException(
						"El cliente tiene productos, pero ninguna Tarjeta de Crédito activa")))
				.flatMap(productoBase -> tarjetaCreditoR2dbcRepository.findById(productoBase.getId())
						.map(tarjetaCreditoEntity -> tarjetaCreditoMapper.toTarjetaCreditoResponse(productoBase,
								tarjetaCreditoEntity)));
	}

	@Override
	public Flux<TarjetaCreditoResponse> encontrarTarjetasCredito() {
		return cuentaRepository.findAll().filter(producto -> producto.getTipoCuenta() == TipoCuenta.TARJETA_CREDITO)
				.flatMap(cuentaResponse -> {
					CuentaEntity cuentaEntity = cuentaMapper.toEntityFromResponse(cuentaResponse);

					return tarjetaCreditoR2dbcRepository.findById(cuentaEntity.getId())
							.map(tarjetaCreditoEntity -> tarjetaCreditoMapper.toTarjetaCreditoResponse(cuentaEntity,
									tarjetaCreditoEntity));

				});
	}

}
