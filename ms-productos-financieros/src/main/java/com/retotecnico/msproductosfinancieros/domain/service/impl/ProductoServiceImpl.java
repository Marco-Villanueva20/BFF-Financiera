package com.retotecnico.msproductosfinancieros.domain.service.impl;

import com.retotecnico.model.*;
import com.retotecnico.msproductosfinancieros.domain.repository.CuentaRepository;
import com.retotecnico.msproductosfinancieros.domain.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements CuentaService {
    private final CuentaRepository productoRepository;

    @Override
    public Flux<CuentaResponse> findByClienteId(UUID id) {
        return productoRepository.findByClienteId(id);
    }
    
    @Override
    public Flux<CuentaResponse> findAll() {
        return productoRepository.findAll();
    }
}
