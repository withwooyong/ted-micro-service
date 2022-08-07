package com.ted.micro.api.core.product;

import com.ted.micro.api.core.product.dto.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductService {
    /**
     * curl $HOST:$PORT/product/1
     * curl http://localhost:7001/product/1
     */
    @GetMapping(value = "/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Product getProduct(@PathVariable int productId);
}
