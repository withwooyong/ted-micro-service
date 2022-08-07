package com.ted.micro.recommend.service;

import com.ted.micro.api.core.recommend.RecommendService;
import com.ted.micro.api.core.recommend.dto.Recommend;
import com.ted.micro.util.exception.InvalidInputException;
import com.ted.micro.util.http.ServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService {

    private final ServiceUtil serviceUtil;

    @Override
    public List<Recommend> getRecommends(int productId) {
        if (productId < 1)
            throw new InvalidInputException("Invalid productId: " + productId);
        if (productId == 113) {
            log.debug("No recommend found for productId: {}", productId);
            return new ArrayList<>();
        }
        List<Recommend> list = new ArrayList<>();
        list.add(new Recommend(productId, 1, "Author 1", 1, "Content 1", serviceUtil.getServiceAddress()));
        list.add(new Recommend(productId, 2, "Author 2", 2, "Content 2", serviceUtil.getServiceAddress()));
        list.add(new Recommend(productId, 3, "Author 3", 3, "Content 3", serviceUtil.getServiceAddress()));
        log.debug("/recommend response size: {}", list.size());
        return list;
    }
}
