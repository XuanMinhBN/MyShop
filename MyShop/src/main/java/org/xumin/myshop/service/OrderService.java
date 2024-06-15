package org.xumin.myshop.service;

import org.xumin.myshop.entity.Order;
import org.xumin.myshop.entity.OrderDetail;
import org.xumin.myshop.entity.OrderStatus;

public interface OrderService {
    Order save(Order order);
}
