package com.ted.micro.api.core.recommend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Recommend {

    private int productId;
    private int recommendId;
    private String author;
    private int rate;
    private String content;
    private String serviceAddress;
}
