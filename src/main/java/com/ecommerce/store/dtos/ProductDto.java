package com.ecommerce.store.dtos;

import com.ecommerce.store.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Byte categoryId;
}
