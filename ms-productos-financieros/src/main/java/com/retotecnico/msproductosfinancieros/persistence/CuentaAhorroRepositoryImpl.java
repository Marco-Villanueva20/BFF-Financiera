package com.retotecnico.msproductosfinancieros.persistence;

import java.util.UUID;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.retotecnico.model.CuentaAhorroRequest;
import com.retotecnico.model.CuentaAhorroResponse;
import com.retotecnico.model.TipoCuenta;
import com.retotecnico.msproductosfinancieros.domain.exception.ResourceNotFoundException;
import com.retotecnico.msproductosfinancieros.domain.repository.CuentaAhorroRepository;
import com.retotecnico.msproductosfinancieros.domain.repository.CuentaRepository;
import com.retotecnico.msproductosfinancieros.persistence.entity.CuentaAhorroEntity;
import com.retotecnico.msproductosfinancieros.persistence.mapper.CuentaAhorroMapper;
import com.retotecnico.msproductosfinancieros.persistence.repository.CuentaAhorroR2dbcRepository;
import com.retotecnico.msproductosfinancieros.persistence.repository.CuentaR2dbcRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CuentaAhorroRepositoryImpl implements CuentaAhorroRepository{

    private final CuentaR2dbcRepository cuentaR2dbcRepository;

	private final CuentaAhorroR2dbcRepository cuentaAhorroR2dbcRepository;
	private final CuentaRepository cuentaRepository;
	private final CuentaAhorroMapper cuentaAhorroMapper;
	private final R2dbcEntityTemplate entityTemplate;

	
	@Override
	@Transactional
	public Mono<CuentaAhorroResponse> createCuentaAhorro(CuentaAhorroRequest request) {
		return cuentaRepository.crearCuenta(request).flatMap(cuentaGuardada -> {
			
			CuentaAhorroEntity cuentaAhorroEntity = cuentaAhorroMapper.toCuentaAhorroEntity(request);
			cuentaAhorroEntity.setCuentaId(cuentaGuardada.getId());
			
			log.info("Enviando este archivo {}",
					cuentaAhorroMapper.toCuentaAhorroResponse(cuentaGuardada, cuentaAhorroEntity));
			
			return entityTemplate.insert(cuentaAhorroEntity)
					.map(cuentaAhorroGuardada -> cuentaAhorroMapper.toCuentaAhorroResponse(cuentaGuardada, cuentaAhorroGuardada));
		});
	}

	@Override
	public Flux<CuentaAhorroResponse> findCuentaAhorroByClienteId(UUID clienteId) {
		return cuentaRepository.buscarProductoBasePorClienteId(clienteId)
				.filter(producto -> producto.getTipoCuenta() == TipoCuenta.CUENTA_AHORRO)
				.switchIfEmpty(Flux.error(new ResourceNotFoundException(
						"El cliente tiene productos, pero ninguna Cuenta de Ahorro activa")))
				.flatMap(productoEntity -> cuentaAhorroR2dbcRepository.findById(productoEntity.getId())
						.map(detalleCuentaAhorro -> cuentaAhorroMapper.toCuentaAhorroResponse(productoEntity,
								detalleCuentaAhorro)));
	}

	@Override
	public Flux<CuentaAhorroResponse> findAllCuentas() {
		// TODO Auto-generated method stub
		return cuentaR2dbcRepository.findAll()
				.filter(producto -> producto.getTipoCuenta() == TipoCuenta.CUENTA_AHORRO)
				.flatMap(productoEntity -> cuentaAhorroR2dbcRepository.findById(productoEntity.getId())
						.map(detalleCuentaAhorro -> cuentaAhorroMapper.toCuentaAhorroResponse(productoEntity,
								detalleCuentaAhorro)));
	}
	

}
