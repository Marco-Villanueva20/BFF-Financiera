package com.retotecnico.msproductosfinancieros.persistence.repository;

import com.retotecnico.msproductosfinancieros.persistence.entity.CuentaEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface CuentaR2dbcRepository extends ReactiveCrudRepository<CuentaEntity, UUID> {
    Flux<CuentaEntity> findByClienteId(UUID clienteId);
}
