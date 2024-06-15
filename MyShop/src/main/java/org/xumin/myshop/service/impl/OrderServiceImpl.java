package org.xumin.myshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xumin.myshop.entity.Order;
import org.xumin.myshop.entity.OrderStatus;
import org.xumin.myshop.reponsitory.OrderReponsitory;
import org.xumin.myshop.service.OrderService;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderReponsitory orderReponsitory;

    @Autowired
    public OrderServiceImpl(OrderReponsitory orderReponsitory) {
        this.orderReponsitory = orderReponsitory;
    }

    @Override
    public Order save(Order order) {
        return orderReponsitory.save(order);
    }
}
