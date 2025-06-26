package com.ecommerce.store.controllers;

import com.ecommerce.store.dtos.CreateProductRequest;
import com.ecommerce.store.dtos.ProductDto;
import com.ecommerce.store.entities.Category;
import com.ecommerce.store.entities.Product;
import com.ecommerce.store.mappers.ProductMapper;
import com.ecommerce.store.repositories.CategoryRepository;
import com.ecommerce.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

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


    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody CreateProductRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if(category == null) {
            return ResponseEntity.badRequest().build();
        }

        Product product = productMapper.toEntity(request);
        product.setCategory(category);
        productRepository.save(product);

        ProductDto productDto = productMapper.toDto(product);

        uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId());
        return ResponseEntity.created(uriBuilder.build().toUri()).body(productDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDto request
    ){
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if(category == null) {
            return ResponseEntity.badRequest().build();
        }

        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            return ResponseEntity.notFound().build();
        }

        productMapper.updateProduct(request, product);
        product.setCategory(category);
        productRepository.save(product);

        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            return ResponseEntity.notFound().build();
        }

        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
