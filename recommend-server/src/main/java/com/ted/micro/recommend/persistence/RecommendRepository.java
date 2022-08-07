package com.ted.micro.recommend.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecommendRepository extends CrudRepository<RecommendEntity, String> {
    List<RecommendEntity> findByProductId(int productId);
}
