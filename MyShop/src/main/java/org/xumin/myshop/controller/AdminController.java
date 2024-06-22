package org.xumin.myshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xumin.myshop.entity.AuthUser;
import org.xumin.myshop.entity.Order;
import org.xumin.myshop.entity.User;
import org.xumin.myshop.service.OrderService;
import org.xumin.myshop.service.ProductService;
import org.xumin.myshop.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public AdminController(OrderService orderService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/view")
    public String admin(Model model, Authentication authentication) {
        Integer orderQuantity = orderService.findAll().size();
        Integer productQuantity = productService.findAll().size();
        List<Order> orders = orderService.findAll().reversed();

        model.addAttribute("orders", orderQuantity);
        model.addAttribute("products", productQuantity);
        model.addAttribute("orderList", orders);
        return "admin/admin";
    }
}
