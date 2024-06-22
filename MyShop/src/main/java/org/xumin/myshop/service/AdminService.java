package org.xumin.myshop.service;

import org.xumin.myshop.entity.Order;
import org.xumin.myshop.entity.Product;

import java.util.List;

public interface AdminService {
    Integer orderCount(Integer orders);
    Integer productCount(Integer products);
}
