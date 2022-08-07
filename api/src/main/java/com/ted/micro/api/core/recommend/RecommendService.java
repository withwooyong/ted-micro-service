package com.ted.micro.api.core.recommend;

import com.ted.micro.api.core.recommend.dto.Recommend;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RecommendService {

    /**
     * curl $HOST:$PORT/recommendation?productId=1
     * curl http://localhost:7002/recommends?productId=1
     */
    @GetMapping(value = "/recommends", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Recommend> getRecommends(@RequestParam(value = "productId", required = true) int productId);
}
