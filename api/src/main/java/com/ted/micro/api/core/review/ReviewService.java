package com.ted.micro.api.core.review;

import com.ted.micro.api.core.review.dto.Review;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewService {
    /**
     * curl $HOST:$PORT/review?productId=1
     * curl http://localhost:7003/review?productId=1
     */
    @GetMapping(value = "/review", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Review> getReviews(@RequestParam(value = "productId", required = true) int productId);
}
