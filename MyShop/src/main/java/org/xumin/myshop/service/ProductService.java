package org.xumin.myshop.service;

import org.xumin.myshop.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    List<Product> findByCategoryId(Long id);

    List<Product> findByNameContaining(String name);
}
