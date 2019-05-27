package com.example.postgresql.repo;

import com.example.postgresql.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}