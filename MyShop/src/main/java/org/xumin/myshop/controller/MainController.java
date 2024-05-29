package org.xumin.myshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xumin.myshop.entity.Product;
import org.xumin.myshop.service.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("main")
public class MainController {
    private ProductService productService;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/layout")
    public String index(Model model){
        model.addAttribute("title", "MyShop");
        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);
        return "/pages/productAlt";
    }

    @GetMapping("/test")
    public String test(){
        List<Product> productList = productService.findByCategoryId(1L);
        productList.forEach(System.out::println);
        return "detail";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam(name = "categoryId") Long id, Model model){
        List<Product> productList = productService.findByCategoryId(id);
        model.addAttribute("productList", productList);
        return "/pages/productAlt";
    }


    @GetMapping("/path/{categoryId}")
    public String path(@PathVariable(name = "categoryId") Long id, Model model){
        List<Product> productList = productService.findByCategoryId(id);
        model.addAttribute("productList", productList);
        return "/pages/homeproducts";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        Product product = productService.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "/detail";
    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("title", "MyShop");
        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);
        return "/pages/homeproducts";
    }

    @GetMapping("/search")
    public String homeAlt(@RequestParam(name = "search_bar") String name, Model model){
        List<Product> productList = productService.findByNameContaining(name);
        model.addAttribute("productList", productList);
        return "home";
    }

    @GetMapping("/user")
    public String account(Model model){
        return "/pages/user";
    }

    @GetMapping("/address")
    public String address(Model model){
        return "/pages/address";
    }

    @GetMapping("/orders")
    public String order(Model model){
        return "/pages/orders";
    }

    @GetMapping("/payment")
    public String payment(Model model){
        return "/pages/payment";
    }

    @GetMapping("login")
    public String login(@RequestParam("email_phone") String user, @RequestParam("pass") String pass, Model model){
        model.addAttribute("user", user);
        model.addAttribute("pass", pass);
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
