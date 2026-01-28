package com.retotecnico.msproductosfinancieros.persistence.repository;

import com.retotecnico.msproductosfinancieros.persistence.entity.CuentaAhorroEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface CuentaAhorroR2dbcRepository extends ReactiveCrudRepository<CuentaAhorroEntity, UUID> {
}
