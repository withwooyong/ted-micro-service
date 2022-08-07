package com.ted.micro.api.composite.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductAggregate {

    private int productId;
    private String name;
    private int weight;
    private List<RecommendSummary> recommendations;
    private List<ReviewSummary> reviews;
    private ServiceAddresses serviceAddresses;
}
