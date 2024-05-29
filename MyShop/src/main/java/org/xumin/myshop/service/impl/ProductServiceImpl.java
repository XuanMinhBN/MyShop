package org.xumin.myshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xumin.myshop.entity.Product;
import org.xumin.myshop.reponsitory.ProductReponsitory;
import org.xumin.myshop.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductReponsitory productReponsitory;

    @Autowired
    public ProductServiceImpl(ProductReponsitory productReponsitory) {
        this.productReponsitory = productReponsitory;
    }

    @Override
    public List<Product> findAll() {
        return productReponsitory.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productReponsitory.findById(id);
    }

    @Override
    public List<Product> findByCategoryId(Long id) {
        return productReponsitory.findByCategoryId(id);
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        return productReponsitory.findByNameContaining(name);
    }
}
