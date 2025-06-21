package com.ecommerce.store.mappers;


import com.ecommerce.store.dtos.ProductDto;
import com.ecommerce.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);
}
