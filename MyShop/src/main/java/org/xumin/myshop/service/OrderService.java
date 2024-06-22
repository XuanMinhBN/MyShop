package org.xumin.myshop.service;

import org.xumin.myshop.entity.Order;
import org.xumin.myshop.entity.OrderDetail;
import org.xumin.myshop.entity.OrderStatus;

import java.util.List;

public interface OrderService {
    Order save(Order order);

    List<Order> findAll();
}
