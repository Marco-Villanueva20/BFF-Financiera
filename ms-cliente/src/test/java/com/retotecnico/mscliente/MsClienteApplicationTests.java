package com.retotecnico.mscliente;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@WithMockUser(username="esuez5", authorities = {"SCOPE_cashcard:read"})
class MsClienteApplicationTests {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void shouldFailWithoutToken() {
        webTestClient.get()
                .uri("/api/test")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void shouldPassWithJwt() {
        String token = "token_valido_aqui";

        webTestClient.get()
                .uri("/api/test")
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isOk();
    }

}
