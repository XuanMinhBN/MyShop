package org.xumin.myshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xumin.myshop.entity.Category;
import org.xumin.myshop.entity.Product;
import org.xumin.myshop.reponsitory.ProductReponsitory;
import org.xumin.myshop.service.ProductService;

import java.util.*;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

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

    @Override
    public List<Product> sortByPrice(String type) {
        List<Product> sortedList = new ArrayList<>(productReponsitory.findAll());
        if(type.equalsIgnoreCase("asc")){
            sortedList.sort(Comparator.comparing(Product::getPrice));
        }else{
            sortedList.sort(Comparator.comparing(Product::getPrice).reversed());
        }
        return sortedList;
    }

    @Override
    public Page<Product> findAll(Integer page) {
        Pageable pageable = PageRequest.of(page-1,10);
        return this.productReponsitory.findAll(pageable);
    }
}
