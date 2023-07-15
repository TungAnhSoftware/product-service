package com.ecom.productservice.mappers;

import com.ecom.productservice.dto.CreateProductArgs;
import com.ecom.productservice.dto.ProductResponse;
import com.ecom.productservice.dto.UpdateProductArgs;
import com.ecom.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse ModelToResp(Product product);

    Product CreateProductArgsToModel(CreateProductArgs createProductArgs);

    @Mapping(source = "id", target = "id", qualifiedByName = "stringToUUID")
    Product UpdateProductArgsToModel(UpdateProductArgs updateProductArgs);

    @Named("stringToUUID")
    static UUID stringToUUID(String stringId) {
        return UUID.fromString(stringId);
    }
}
