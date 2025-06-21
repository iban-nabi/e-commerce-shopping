package com.ecommerce.store.controllers;

import com.ecommerce.store.dtos.ProductDto;
import com.ecommerce.store.mappers.ProductMapper;
import com.ecommerce.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> getProducts(
            @RequestParam(required=false, name="categoryId") Byte categoryId
    ) {
        if(categoryId == null) {
            return productRepository.findAll()
                    .stream()
                    .map(productMapper::toDto)
                    .toList();
        }else{
            return productRepository.findByCategoryId(categoryId)
                    .stream()
                    .map(productMapper::toDto)
                    .toList();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        ProductDto productDto = productMapper.toDto(productRepository.findById(id).orElse(null));
        if(productDto == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(productDto);
        }
    }

}
