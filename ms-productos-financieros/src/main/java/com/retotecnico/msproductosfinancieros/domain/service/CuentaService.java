package com.retotecnico.msproductosfinancieros.domain.service;

import com.retotecnico.model.*;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface CuentaService {
    Flux<CuentaResponse> findAll();
    Flux<CuentaResponse> findByClienteId(UUID cliente_Id);
    
}
