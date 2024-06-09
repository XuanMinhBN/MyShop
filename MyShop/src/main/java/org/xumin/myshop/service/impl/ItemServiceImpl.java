package org.xumin.myshop.service.impl;

import org.springframework.stereotype.Service;
import org.xumin.myshop.entity.Item;
import org.xumin.myshop.service.ItemService;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public int countTotalItemQuantity(List<Item> itemList) {
        int total = 0;
        for(Item item : itemList) {
            total += item.getQuantity();
        }
        return total;
    }

    @Override
    public double totalProductPrice(List<Item> itemList) {
        return itemList.stream().mapToDouble(Item::getTotalPrice).sum();
    }

}
