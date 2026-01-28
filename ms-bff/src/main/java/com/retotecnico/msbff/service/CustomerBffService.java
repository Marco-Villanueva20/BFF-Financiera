package com.retotecnico.msbff.service;

import com.retotecnico.msbff.dto.ClienteResponseDTO;
import com.retotecnico.msbff.dto.ProductDTO;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.contextpropagation.ObservationThreadLocalAccessor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class CustomerBffService {

    private static final Logger log = LoggerFactory.getLogger(CustomerBffService.class);
    private final WebClient clienteWebClient;
    private final WebClient productosWebClient;
    private final TextEncryptor textEncryptor;

    public Mono<ClienteResponseDTO> getConsolidatedData() {
        return ReactiveSecurityContextHolder.getContext()
                .flatMap(ctx -> {
                    if (ctx.getAuthentication() instanceof JwtAuthenticationToken jwtAuth) {
                        return Mono.just(jwtAuth);
                    }
                    return Mono.error(new RuntimeException("No se encontró un token JWT válido"));
                })
                .flatMap(jwtAuth -> {
                    String tokenValue = jwtAuth.getToken().getTokenValue();
                    String codigoEncriptado = jwtAuth.getToken().getClaim("uuid");

                    String userId;
                    try {
                        userId = textEncryptor.decrypt(codigoEncriptado);
                    } catch (Exception e) {
                        return Mono.error(new RuntimeException("Código único inválido"));
                    }

                    // Log para ver el Trace ID en la consola del BFF
                    log.info("Iniciando consulta consolidada para el usuario desencriptado: {}", userId);


                    return clienteWebClient.get()
                            .uri("/api/v1/clientes/usuario/{id}", userId)
                            .headers(h -> h.setBearerAuth(tokenValue))
                            .retrieve()
                            .bodyToMono(ClienteResponseDTO.class)
                            .flatMap(cliente -> {
                                log.info("Cliente encontrado, obteniendo productos financieros...");

                                return productosWebClient.get()
                                        .uri("/api/v1/productos/cliente/{id}", cliente.getId())
                                        .headers(h -> h.setBearerAuth(tokenValue))
                                        .retrieve()
                                        .bodyToFlux(ProductDTO.class)
                                        .collectList()
                                        .map(productos -> {
                                            log.info("Se encontraron {} productos para el cliente", productos.size());
                                            cliente.setProductos(productos);
                                            return cliente;
                                        });
                            });
                });

    }
}