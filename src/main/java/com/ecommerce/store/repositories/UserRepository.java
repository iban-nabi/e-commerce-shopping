package com.ecommerce.store.repositories;

import com.ecommerce.store.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
