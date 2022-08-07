package com.ted.micro.api.composite.product.dto;

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
public class ServiceAddresses {

    private String cmp;
    private String pro;
    private String rev;
    private String rec;
}
