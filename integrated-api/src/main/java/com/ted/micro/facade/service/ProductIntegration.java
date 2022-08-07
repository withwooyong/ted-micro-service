package com.ted.micro.facade.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ted.micro.api.core.product.ProductService;
import com.ted.micro.api.core.product.dto.Product;
import com.ted.micro.api.core.recommend.RecommendService;
import com.ted.micro.api.core.recommend.dto.Recommend;
import com.ted.micro.api.core.review.ReviewService;
import com.ted.micro.api.core.review.dto.Review;
import com.ted.micro.util.exception.InvalidInputException;
import com.ted.micro.util.exception.NotFoundException;
import com.ted.micro.util.http.HttpErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Component
public class ProductIntegration implements ProductService, RecommendService, ReviewService {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String productServiceUrl;
    private final String recommendServiceUrl;
    private final String reviewServiceUrl;

    @Autowired
    public ProductIntegration(
            RestTemplate restTemplate,
            ObjectMapper mapper,

            @Value("${app.product-server.host}") String productServiceHost,
            @Value("${app.product-server.port}") int productServicePort,

            @Value("${app.recommend-server.host}") String recommendServiceHost,
            @Value("${app.recommend-server.port}") int recommendServicePort,

            @Value("${app.review-server.host}") String reviewServiceHost,
            @Value("${app.review-server.port}") int reviewServicePort
    ) {

        this.restTemplate = restTemplate;
        this.mapper = mapper;

        productServiceUrl = "http://" + productServiceHost + ":" + productServicePort + "/product/";
        recommendServiceUrl = "http://" + recommendServiceHost + ":" + recommendServicePort + "/recommends?productId=";
        reviewServiceUrl = "http://" + reviewServiceHost + ":" + reviewServicePort + "/review?productId=";
    }

    public Product getProduct(int productId) {
        try {
            String url = productServiceUrl + productId;
            log.debug("Will call getProduct API on URL: {}", url);
            Product product = restTemplate.getForObject(url, Product.class);
            log.debug("Found a product with id: {}", product.getProductId());
            return product;
        } catch (HttpClientErrorException ex) {
            switch (ex.getStatusCode()) {
                case NOT_FOUND:
                    throw new NotFoundException(getErrorMessage(ex));
                case UNPROCESSABLE_ENTITY:
                    throw new InvalidInputException(getErrorMessage(ex));
                default:
                    log.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
                    log.warn("Error body: {}", ex.getResponseBodyAsString());
                    throw ex;
            }
        }
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch (IOException ioex) {
            return ex.getMessage();
        }
    }

    public List<Recommend> getRecommends(int productId) {
        try {
            String url = recommendServiceUrl + productId;
            log.debug("Will call getRecommend API on URL: {}", url);
            List<Recommend> recommendList = restTemplate.exchange(url, GET, null, new ParameterizedTypeReference<List<Recommend>>() {
            }).getBody();
            log.debug("Found {} recommend for a product with id: {}", recommendList.size(), productId);
            return recommendList;
        } catch (Exception ex) {
            log.warn("Got an exception while requesting recommend, return zero recommend: {}", ex.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Review> getReviews(int productId) {
        try {
            String url = reviewServiceUrl + productId;
            log.debug("Will call getReviews API on URL: {}", url);
            List<Review> reviews = restTemplate.exchange(url, GET, null, new ParameterizedTypeReference<List<Review>>() {
            }).getBody();
            log.debug("Found {} reviews for a product with id: {}", reviews.size(), productId);
            return reviews;
        } catch (Exception ex) {
            log.warn("Got an exception while requesting reviews, return zero reviews: {}", ex.getMessage());
            return new ArrayList<>();
        }
    }
}
