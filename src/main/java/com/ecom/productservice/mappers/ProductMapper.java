package com.ecom.productservice.mappers;

import com.ecom.productservice.dto.ProductRequest;
import com.ecom.productservice.dto.ProductResponse;
import com.ecom.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse ModelToResp(Product product);

    @Mapping(target = "name")
    Product ReqToModel(ProductRequest productRequest);

}
