package org.xumin.myshop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class Item implements Serializable {
    private Long id;
    private String itemName;
    private String image;
    private double price;
    private int quantity;
    private int remainQuantity;
    private double totalPrice;
}
