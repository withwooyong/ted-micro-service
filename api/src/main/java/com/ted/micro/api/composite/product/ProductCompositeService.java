package com.ted.micro.api.composite.product;

import com.ted.micro.api.composite.product.dto.ProductAggregate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Api(tags = {"REST API for aggregate product information."})
public interface ProductCompositeService {
    /**
     * curl $HOST:$PORT/product-aggregate/1
     * curl http://localhost:7000/product-aggregate/1
     */
    @ApiOperation(
            value = "${api.product-aggregate.get-aggregate-product.description}",
            notes = "${api.product-aggregate.get-aggregate-product.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request, invalid format of the request. See response message for more information."),
            @ApiResponse(code = 404, message = "Not found, the specified id does not exist."),
            @ApiResponse(code = 422, message = "Unprocessable entity, input parameters caused the processing to fails. See response message for more information.")
    })
    @GetMapping(value = "/product-aggregate/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProductAggregate getProductAggregate(@PathVariable int productId);
}
