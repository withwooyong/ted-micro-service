package com.ted.micro.recommend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecommendApplicationTest {

    @Autowired
    private WebTestClient client;

    @Test
    public void getRecommendsByProductId() {
        int productId = 1;

        client.get()
                .uri("/recommends?productId=" + productId)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.length()").isEqualTo(3)
                .jsonPath("$[0].productId").isEqualTo(productId);
    }

    @Test
    public void getRecommendsMissingParameter() {

        client.get()
                .uri("/recommends")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(BAD_REQUEST)
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.path").isEqualTo("/recommends")
                .jsonPath("$.message").isEqualTo("Required int parameter 'productId' is not present");
    }

    @Test
    public void getRecommendsInvalidParameter() {

        client.get()
                .uri("/recommends?productId=no-integer")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(BAD_REQUEST)
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.path").isEqualTo("/recommends")
                .jsonPath("$.message").isEqualTo("Type mismatch.");
    }

    @Test
    public void getRecommendsNotFound() {

        int productIdNotFound = 113;

        client.get()
                .uri("/recommends?productId=" + productIdNotFound)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.length()").isEqualTo(0);
    }

    @Test
    public void getRecommendsInvalidParameterNegativeValue() {

        int productIdInvalid = -1;

        client.get()
                .uri("/recommends?productId=" + productIdInvalid)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(UNPROCESSABLE_ENTITY)
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.path").isEqualTo("/recommends")
                .jsonPath("$.message").isEqualTo("Invalid productId: " + productIdInvalid);
    }

}