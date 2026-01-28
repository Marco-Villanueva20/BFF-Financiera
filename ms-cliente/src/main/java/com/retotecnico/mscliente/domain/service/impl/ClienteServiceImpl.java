package com.retotecnico.mscliente.domain.service.impl;

import com.retotecnico.mscliente.domain.dto.ClienteRequest;
import com.retotecnico.mscliente.domain.dto.ClienteResponse;
import com.retotecnico.mscliente.domain.service.ClienteService;
import com.retotecnico.mscliente.persistence.entity.ClienteEntity;
import com.retotecnico.mscliente.persistence.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;


    @Override
    public Flux<ClienteResponse> findAll() {
        return clienteRepository.findAll().
                map(clienteEntity -> new ClienteResponse(
                        clienteEntity.getId(),
                        clienteEntity.getUserId(),
                        clienteEntity.getNombres(),
                        clienteEntity.getApellidos(),
                        clienteEntity.getTipoDocumento(),
                        clienteEntity.getNumeroDocumento()
                ));
    }


    @Override
    public Mono<ClienteResponse> create(ClienteRequest clienteRequest) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setUserId(clienteRequest.userId());
        clienteEntity.setNombres(clienteRequest.nombres());
        clienteEntity.setApellidos(clienteRequest.apellidos());
        clienteEntity.setTipoDocumento(clienteRequest.tipoDocumento());
        clienteEntity.setNumeroDocumento(clienteRequest.numeroDocumento());
        return clienteRepository.save(clienteEntity).map(cliente -> new ClienteResponse(
                cliente.getId(),
                cliente.getUserId(),
                cliente.getNombres(),
                cliente.getApellidos(),
                cliente.getTipoDocumento(),
                cliente.getNumeroDocumento()
        ));
    }

    @Override
    public Mono<ClienteResponse> findByUserId(UUID userId) {
        return clienteRepository.findByUserId(userId).
                switchIfEmpty(
                        Mono.error(() -> new RuntimeException("No existe cliente registrado")))
                .map(cliente ->
                                new ClienteResponse(
                                        cliente.getId(),
                                        cliente.getUserId(),
                                        cliente.getNombres(),
                                        cliente.getApellidos(),
                                        cliente.getTipoDocumento(),
                                        cliente.getNumeroDocumento()
                                )
                        );

    }


}
