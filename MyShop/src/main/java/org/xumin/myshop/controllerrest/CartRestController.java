package org.xumin.myshop.controllerrest;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xumin.myshop.entity.Item;
import org.xumin.myshop.entity.Product;
import org.xumin.myshop.service.ItemService;
import org.xumin.myshop.service.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/cart")
public class CartRestController {
    private ProductService productService;
    private ItemService itemService;

    @Autowired
    public CartRestController(ProductService productService, ItemService itemService) {
        this.productService = productService;
        this.itemService = itemService;
    }

    @GetMapping("/add")
    public ResponseEntity<Map<String, Object>> addToCart(
            @RequestParam Long productId,
            @RequestParam int quantity,
            HttpSession session
    ) {
        return addToCartProcess(productId,quantity,session);
    }

    private ResponseEntity<Map<String, Object>> addToCartProcess(Long id, int quantity, HttpSession session){
        Product product = productService.findById(id).orElse(null);
        if(product != null){
            Item item = Item.builder()
                    .id(product.getId())
                    .itemName(product.getName())
                    .image(product.getImage())
                    .price(product.getPrice())
                    .quantity(quantity)
                    .remainQuantity(product.getQuantity() - quantity)
                    .totalPrice(product.getPrice()*quantity)
                    .build();

            List<Item> cart = (List<Item>) session.getAttribute("cart");
            if(cart == null){
                cart = new ArrayList<>();
                cart.add(item);
//                session.setAttribute("cart", cart);
            }else {
                boolean isExist = false;
                for(Item i : cart){
                    if(i.getId().equals(item.getId())){
                        i.setQuantity(i.getQuantity()+quantity);
                        i.setTotalPrice(i.getQuantity()*i.getPrice());
                        isExist = true;
                    }
                }
                if(!isExist){
                    cart.add(item);
                }
            }
            session.setAttribute("cart", cart);

            int count = itemService.countTotalItemQuantity(cart);
            session.setAttribute("totalProductQuantity", count);
            session.setAttribute("totalProductPrice", itemService.totalProductPrice(cart));

            Map<String, Object> response = new HashMap<>();
            response.put("cart", cart);
            response.put("totalProductQuantity", count);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
