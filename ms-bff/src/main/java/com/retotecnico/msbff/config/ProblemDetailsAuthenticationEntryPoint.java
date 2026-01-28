package com.retotecnico.msbff.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.server.resource.web.server.BearerTokenServerAuthenticationEntryPoint;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class ProblemDetailsAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    private final ServerAuthenticationEntryPoint delegate = new BearerTokenServerAuthenticationEntryPoint();
    private final ObjectMapper mapper;

    public ProblemDetailsAuthenticationEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {

        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        this.delegate.commence(exchange, ex).subscribe();

        if (ex.getCause() instanceof JwtValidationException validation){

            ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
            detail.setType(URI.create("https://tools.ietf.org/html/rfc6750#section-3.1"));
            detail.setTitle("Invalid Token");
            detail.setDetail("JWT validation failed");
            detail.setProperty("errors", validation.getErrors());
            try {
                byte[] bytes = mapper.writeValueAsBytes(detail);
                DataBuffer buffer = exchange.getResponse()
                        .bufferFactory()
                        .wrap(bytes);

                return exchange.getResponse().writeWith(Mono.just(buffer));
            } catch (Exception e) {
                return Mono.error(e);
            }
        }

        return exchange.getResponse().setComplete();
    }
}
