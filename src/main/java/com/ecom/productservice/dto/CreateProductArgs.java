package com.ecom.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductArgs {
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private String color;
    private BigDecimal quantity;
}
