package com.ecommerce.store.repositories;

import com.ecommerce.store.entities.Category;
import com.ecommerce.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
}