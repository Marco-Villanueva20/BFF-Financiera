package com.retotecnico.msbff.config;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    @Primary
    public WebClient.Builder webClientBuilder(ObservationRegistry observationRegistry) {
        return WebClient.builder()
                .observationRegistry(observationRegistry); // Esto activa el tracing
    }
    @Bean
    public WebClient clienteWebClient(WebClient.Builder trazabilidadBuilder) {
        return trazabilidadBuilder
                .baseUrl("http://localhost:8082")
                .build();
    }

    @Bean
    public WebClient productosWebClient(WebClient.Builder trazabilidadBuilder) {
        return trazabilidadBuilder
                .baseUrl("http://localhost:8083")
                .build();
    }
}
