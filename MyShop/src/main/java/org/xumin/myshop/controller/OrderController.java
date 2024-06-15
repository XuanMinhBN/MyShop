package org.xumin.myshop.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xumin.myshop.entity.*;
import org.xumin.myshop.reponsitory.OrderReponsitory;
import org.xumin.myshop.service.OrderService;
import org.xumin.myshop.service.UserAddressService;
import org.xumin.myshop.service.UserDetailService;
import org.xumin.myshop.service.UserService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/main")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final UserAddressService userAddressService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService, UserAddressService userAddressService) {
        this.orderService = orderService;
        this.userService = userService;
        this.userAddressService = userAddressService;
    }

    @GetMapping("/checkout")
    public String order(Authentication authentication, HttpSession session) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User user = userService.findByUsername(authUser.getUsername());
        UserAddress defaultAddress = userAddressService.getUserAddressDefault(user.getUserAddresses());
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(1L);

        Order order = new Order();
        order.setUserId(user.getId());
        order.setOrderShipDate(Date.valueOf(LocalDate.now()));
        order.setOrderCreatedAt(Date.valueOf(LocalDate.now()));
        order.setOrderStatus(orderStatus);
        order.setOrderDiscount(0D);
        order.setOrderTotalAmount(Double.parseDouble(session.getAttribute("totalProductPrice").toString()));
        order.setOrderNote("");
        order.setReceiverName(defaultAddress.getReceiverName());
        order.setReceiverMobile(defaultAddress.getReceiverMobile());
        order.setShippingAddress(defaultAddress.getAddress());
        order.setOrderDetail(getOrderDetailList((List<Item>) session.getAttribute("cart"),order));
        orderService.save(order);
        return "thanks";
    }

    private List<OrderDetail> getOrderDetailList(List<Item> items ,Order order) {
        List<OrderDetail> orderDetailList = new ArrayList<>();

        for (Item item : items) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(item.getId());
            orderDetail.setProductName(item.getItemName());
            orderDetail.setProductImage(item.getImage());
            orderDetail.setProductPrice(item.getPrice());
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setAmount(item.getTotalPrice());
            orderDetail.setOrder(order);
            orderDetailList.add(orderDetail);
        }
        return orderDetailList;
    }
}
