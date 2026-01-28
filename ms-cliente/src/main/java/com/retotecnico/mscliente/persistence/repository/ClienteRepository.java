package com.retotecnico.mscliente.persistence.repository;

import com.retotecnico.mscliente.persistence.entity.ClienteEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ClienteRepository extends ReactiveCrudRepository<ClienteEntity, UUID> {
    Mono<ClienteEntity> findByUserId(UUID userId);
}
