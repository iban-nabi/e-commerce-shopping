package com.ecommerce.store.dtos;

import java.math.BigDecimal;

public class CreateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Byte categoryId;
}
