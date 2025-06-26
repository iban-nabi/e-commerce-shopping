package com.ecommerce.store.mappers;


import com.ecommerce.store.dtos.CreateProductRequest;
import com.ecommerce.store.dtos.ProductDto;
import com.ecommerce.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);
    Product toEntity(CreateProductRequest productDto);
    @Mapping(target = "id", ignore = true)
    void updateProduct(ProductDto productDto, @MappingTarget Product product);
}
