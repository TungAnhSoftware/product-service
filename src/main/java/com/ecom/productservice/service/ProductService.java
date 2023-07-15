package com.ecom.productservice.service;

import com.ecom.productservice.dto.*;
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
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public void createProduct(CreateProductArgs createProductArgs) {
        log.info("Process create " + createProductArgs.getName());

        Optional<Product> storedModel =
                productRepository.findByName(createProductArgs.getName());

        if (storedModel.isPresent()) {
            throw new SmartPhoneException("This Product already exists",
                    HttpStatus.BAD_REQUEST);
        }

        Product product =
                productMapper.CreateProductArgsToModel(createProductArgs);
        saveProduct(product);
        log.info("Product " + product.getName() + " is added!");
    }

    public void updateProduct(UpdateProductArgs updateProductArgs) {
        log.info("Process update " + updateProductArgs);

        Optional<Product> storedModel =
                productRepository.findById(UUID.fromString(updateProductArgs.getId()));

        if (storedModel.isEmpty()) {
            throw new SmartPhoneException("This Product is not existed",
                    HttpStatus.BAD_REQUEST);
        }

        Product product =
                productMapper.UpdateProductArgsToModel(updateProductArgs);
        saveProduct(product);
        log.info("Product " + updateProductArgs + " is updated to " + product);
    }

    public ProductResponse getProductDetail(GetProductDetailArgs getProductDetailArgs) {
        log.info("Process get product " + getProductDetailArgs.getId());

        Optional<Product> storedModel =
                productRepository.findById(UUID.fromString(getProductDetailArgs.getId()));

        if (storedModel.isEmpty()) {
            throw new SmartPhoneException("This Product is not existed",
                    HttpStatus.BAD_REQUEST);
        }

        return productMapper.ModelToResp(storedModel.get());
    }

    public GetProductListResponse getProductList(GetProductListArgs getProductListArgs) {
        log.info("Process get product list " + getProductListArgs);

        int page = getProductListArgs.getPage();
        int size = getProductListArgs.getSize();

        List<Product> filteredProduct = productRepository.findAll();
        int fromIndex = getFromIndex(page, size);
        int toIndex = getToIndex(filteredProduct.size(), fromIndex, size);
        log.info("fromIndex " + fromIndex + ", toIndex " + toIndex);

        filteredProduct = filteredProduct.subList(fromIndex, toIndex);

        List<ProductResponse> products =
                filteredProduct.stream().map(productMapper::ModelToResp).toList();
        return new GetProductListResponse(products.size(),
                getProductListArgs.getSize(), products);
    }

    private void saveProduct(Product product) {
        productRepository.save(product);
    }

    private int getFromIndex(int page, int size) {
        return page * size;
    }

    private int getToIndex(int total, int startIndex, int size) {
        return startIndex + (total - startIndex) % size;
    }
}
