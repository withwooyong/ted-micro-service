package com.ted.micro.facade.service;

import com.ted.micro.api.composite.product.ProductCompositeService;
import com.ted.micro.api.composite.product.dto.ProductAggregate;
import com.ted.micro.api.composite.product.dto.RecommendSummary;
import com.ted.micro.api.composite.product.dto.ReviewSummary;
import com.ted.micro.api.composite.product.dto.ServiceAddresses;
import com.ted.micro.api.core.product.dto.Product;
import com.ted.micro.api.core.recommend.dto.Recommend;
import com.ted.micro.api.core.review.dto.Review;
import com.ted.micro.util.exception.NotFoundException;
import com.ted.micro.util.http.ServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductCompositeServiceImpl implements ProductCompositeService {

    private final ServiceUtil serviceUtil;

    private final ProductIntegration integration;

    @Override
    public ProductAggregate getProductAggregate(int productId) {

        Product product = integration.getProduct(productId);
        if (product == null) throw new NotFoundException("No product found for productId: " + productId);

        List<Recommend> recommends = integration.getRecommends(productId);
        List<Review> reviews = integration.getReviews(productId);
        return createProductAggregate(product, recommends, reviews, serviceUtil.getServiceAddress());
    }

    private ProductAggregate createProductAggregate(Product product, List<Recommend> recommends, List<Review> reviews, String serviceAddress) {

        // 1. Setup product info
        int productId = product.getProductId();
        String name = product.getName();
        int weight = product.getWeight();

        // 2. Copy summary recommendation info, if available
        List<RecommendSummary> recommendationSummaries = (recommends == null) ? null :
                recommends.stream()
                        .map(r -> new RecommendSummary(r.getRecommendationId(), r.getAuthor(), r.getRate()))
                        .collect(Collectors.toList());

        // 3. Copy summary review info, if available
        List<ReviewSummary> reviewSummaries = (reviews == null) ? null :
                reviews.stream()
                        .map(r -> new ReviewSummary(r.getReviewId(), r.getAuthor(), r.getSubject()))
                        .collect(Collectors.toList());
        // 4. Create info regarding the involved microservices addresses
        String productAddress = product.getServiceAddress();
        String reviewAddress = (reviews != null && reviews.size() > 0) ? reviews.get(0).getServiceAddress() : "";
        String recommendationAddress = (recommends != null && recommends.size() > 0) ? recommends.get(0).getServiceAddress() : "";
        ServiceAddresses serviceAddresses = new ServiceAddresses(serviceAddress, productAddress, reviewAddress, recommendationAddress);
        return new ProductAggregate(productId, name, weight, recommendationSummaries, reviewSummaries, serviceAddresses);
    }
}
