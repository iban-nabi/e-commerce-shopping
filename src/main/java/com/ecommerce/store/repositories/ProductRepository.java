package com.ecommerce.store.repositories;

import com.ecommerce.store.entities.Category;
import com.ecommerce.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query("")
    List<Product> findByCategoryId(Byte categoryId);
}