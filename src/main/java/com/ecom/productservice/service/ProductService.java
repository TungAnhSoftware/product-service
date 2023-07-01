package com.ecom.productservice.service;

import com.ecom.productservice.dto.ProductRequest;
import com.ecom.productservice.dto.ProductResponse;
import com.ecom.productservice.exception.SmartPhoneException;
import com.ecom.productservice.mappers.ProductMapper;
import com.ecom.productservice.model.Product;
import com.ecom.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public List<ProductResponse> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::ModelToResp).toList();
    }

    public void createProduct(ProductRequest productRequest) {
        Optional<Product> storedModel = productRepository.findByName(productRequest.getName());

        if (storedModel.isPresent()) {
            throw new SmartPhoneException("This Product already exists", HttpStatus.BAD_REQUEST);
        }

        Product product = productMapper.ReqToModel(productRequest);
        productRepository.save(product);
        log.info("Product " + product.getName() + " is added to Database!");
    }
}
