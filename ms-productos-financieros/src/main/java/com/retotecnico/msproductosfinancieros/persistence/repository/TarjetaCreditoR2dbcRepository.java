package com.retotecnico.msproductosfinancieros.persistence.repository;

import com.retotecnico.msproductosfinancieros.persistence.entity.TarjetaCreditoEntity;

import reactor.core.publisher.Flux;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface TarjetaCreditoR2dbcRepository extends ReactiveCrudRepository<TarjetaCreditoEntity, UUID> {
}
