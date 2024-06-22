package org.xumin.myshop.service;

import org.springframework.data.domain.Page;
import org.xumin.myshop.entity.Category;
import org.xumin.myshop.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    List<Product> findByCategoryId(Long id);

    List<Product> findByNameContaining(String name);

    List<Product> sortByPrice(String type);

    Page<Product> findAll(Integer page);
}
