package com.ted.micro.recommend.service;


import com.ted.micro.api.core.recommend.dto.Recommend;
import com.ted.micro.recommend.persistence.RecommendEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecommendMapper {

    @Mappings({
            @Mapping(target = "rate", source = "entity.rating"),
            @Mapping(target = "serviceAddress", ignore = true)
    })
    Recommend entityToApi(RecommendEntity entity);

    @Mappings({
            @Mapping(target = "rating", source = "api.rate"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    RecommendEntity apiToEntity(Recommend api);

    List<Recommend> entityListToApiList(List<RecommendEntity> entity);

    List<RecommendEntity> apiListToEntityList(List<Recommend> api);
}