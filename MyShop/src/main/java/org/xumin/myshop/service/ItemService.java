package org.xumin.myshop.service;

import org.xumin.myshop.entity.Item;

import java.util.List;

public interface ItemService {
    int countTotalItemQuantity(List<Item> itemList);

    double totalProductPrice(List<Item> itemList);

}
