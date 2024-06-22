package org.xumin.myshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xumin.myshop.entity.Order;
import org.xumin.myshop.entity.Product;
import org.xumin.myshop.service.AdminService;
import org.xumin.myshop.service.OrderService;
import org.xumin.myshop.service.ProductService;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public AdminServiceImpl(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public Integer orderCount(Integer orders) {
        orders = orderService.findAll().size();
        return orders;
    }

    @Override
    public Integer productCount(Integer products) {
        products = productService.findAll().size();
        return products;
    }
}
