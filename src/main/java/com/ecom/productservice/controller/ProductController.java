package com.ecom.productservice.controller;

import com.ecom.productservice.dto.SmartPhoneReq;
import com.ecom.productservice.dto.SmartPhoneResp;
import com.ecom.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @GetMapping(path = "/allSmartPhone")
    public ResponseEntity<?> getAllSmartPhones() {
        logger.info("Received Request getAllSmartPhones");
        List<SmartPhoneResp> smartphones = productService.getAllSmartphones();
        return ResponseEntity.ok(smartphones);
    }

    @PostMapping(path = "/createSmartPhone")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSmartPhone(@RequestBody SmartPhoneReq smartPhoneReq) {
        logger.info("Received Request createSmartPhone");
        productService.createSmartPhone(smartPhoneReq);
    }
}
