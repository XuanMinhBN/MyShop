package org.xumin.myshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xumin.myshop.entity.Item;

@Controller
@RequestMapping("cart")
public class CartController {

    @GetMapping("/test")
    public ResponseEntity<Item> addItem() {
        Item item = Item.builder()
                .id(22L)
                .itemName("")
                .image("")
                .price(0)
                .quantity(111)
                .totalPrice(123)
                .build();
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
