package org.xumin.myshop.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xumin.myshop.entity.Item;
import org.xumin.myshop.entity.Product;
import org.xumin.myshop.exception.ProductNotFoundException;
import org.xumin.myshop.service.ItemService;
import org.xumin.myshop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("cart")
public class CartController {
    private ProductService productService;
    private ItemService itemService;

    @Autowired
    public CartController(ProductService productService, ItemService itemService) {
        this.productService = productService;
        this.itemService = itemService;
    }

    @GetMapping()
    public String goToCart() {
        return "/pages/orders";
    }

    @GetMapping("/item/delete/{id}")
    public String deleteItemInCart(@PathVariable Long id, HttpSession session){
        List<Item> itemList = (List<Item>) session.getAttribute("cart");
        itemList.removeIf(i -> i.getId().equals(id));
        session.setAttribute("cart", itemList);
        session.setAttribute("totalProductQuantity",itemService.countTotalItemQuantity(itemList));
        session.setAttribute("totalProductPrice", itemService.totalProductPrice(itemList));
        return "/pages/orders";
    }

    @GetMapping("/delete")
    public String deleteCart(HttpSession session){
        session.removeAttribute("cart");
        session.removeAttribute("totalProductQuantity");
        session.removeAttribute("totalProductPrice");
        return "/pages/orders";
    }
}
