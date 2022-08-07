package com.ted.micro.product.service;

import com.ted.micro.api.core.product.ProductService;
import com.ted.micro.api.core.product.dto.Product;
import com.ted.micro.util.exception.InvalidInputException;
import com.ted.micro.util.exception.NotFoundException;
import com.ted.micro.util.http.ServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ServiceUtil serviceUtil;

    @Override
    public Product getProduct(int productId) {
        log.debug("/product return the found product for productId={}", productId);
        if (productId < 1)
            throw new InvalidInputException("Invalid productId: " + productId);
        if (productId == 13)
            throw new NotFoundException("No product found for productId: " + productId);
        return new Product(productId, "name-" + productId, 123, serviceUtil.getServiceAddress());
    }
}
