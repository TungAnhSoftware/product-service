package com.ecom.productservice.controller;

import com.ecom.productservice.dto.*;
import com.ecom.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @PostMapping(path = "/createProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody CreateProductArgs createProductArgs) {
        logger.info("Received Request createProduct");
        productService.createProduct(createProductArgs);
    }

    @PostMapping(path = "/updateProduct")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody UpdateProductArgs updateProductArgs) {
        logger.info("Received Request updateProduct");
        productService.updateProduct(updateProductArgs);
    }

    @GetMapping(path = "/getProductDetail")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductDetail(@RequestBody GetProductDetailArgs getProductDetailArgs) {
        logger.info("Received Request getProductDetail");
        return productService.getProductDetail(getProductDetailArgs);
    }

    @GetMapping(path = "/getProductList")
    @ResponseStatus(HttpStatus.OK)
    public GetProductListResponse getProductList(@RequestBody GetProductListArgs getProductListArgs) {
        logger.info("Received Request getProductList");
        return productService.getProductList(getProductListArgs);
    }
}
