package com.retotecnico.mscliente.domain.service;

import com.retotecnico.mscliente.domain.dto.ClienteRequest;
import com.retotecnico.mscliente.domain.dto.ClienteResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ClienteService {

    Flux<ClienteResponse> findAll();
    Mono<ClienteResponse> create(ClienteRequest clienteRequest);

    Mono<ClienteResponse> findByUserId(UUID userId);
}
