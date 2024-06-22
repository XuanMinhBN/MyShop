package org.xumin.myshop.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xumin.myshop.entity.AuthUser;
import org.xumin.myshop.entity.Product;
import org.xumin.myshop.service.ProductService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("main")
public class MainController {
    private ProductService productService;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    //Test
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
    //

    //Filter by category controller
    @GetMapping("/path/{categoryId}")
    public String path(@PathVariable(name = "categoryId") Long id, Model model){
        List<Product> productList = productService.findByCategoryId(id);
        model.addAttribute("productList", productList);
        return "/pages/homeproducts";
    }

    //Sort by price controller
    @GetMapping("/sort/{type}")
    public String sort(@PathVariable(name = "type") String type, Model model){
        List<Product> productList = productService.sortByPrice(type);
        model.addAttribute("productList", productList);
        return "/pages/homeproducts";
    }

    //Product's detail controller
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        Product product = productService.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "/detail";
    }

    //Homepage controller with pagination
    @GetMapping("/home")
    public String home(@RequestParam(name = "page",defaultValue = "1") Integer page, Model model){
        model.addAttribute("title", "MyShop");
        Page<Product> productList = productService.findAll(page);
        model.addAttribute("totalPage",productList.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("productList", productList);
        return "/pages/homeproducts";
    }

    //Search function controller
    @GetMapping("/search")
    public String searchProduct(@RequestParam(name = "search_bar") String name, Model model){
        List<Product> productList = productService.findByNameContaining(name);
        model.addAttribute("productList", productList);
        return "/pages/homeproducts";
    }

    //User page controller
//    @GetMapping("/user")
//    public String account(Model model){
//        return "/pages/user";
//    }

    //Address page controller
    @GetMapping("/address")
    public String address(Model model){
        return "/pages/address";
    }

    //Orders page controller
    @GetMapping("/orders")
    public String order(Model model){
        return "/pages/orders";
    }

    //Payment page controller
//    @GetMapping("/payment")
//    public String payment(Model model){
//        return "/pages/payment";
//    }

    //Login page controller
    @GetMapping("login")
    public String login(){
        return "login";
    }

    //Logout page controller
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    //Register page controller
    @GetMapping("register")
    public String register(){
        return "register";
    }

    @GetMapping("/auth")
    @Transactional(propagation = Propagation.REQUIRED)
    public String auth(Model model, Principal principal, Authentication authentication) {
        String username = principal.getName();
        model.addAttribute("username", username);
        model.addAttribute("userRole", authentication.getAuthorities());

        AuthUser customUser = (AuthUser) authentication.getPrincipal();
        model.addAttribute("customUser", customUser);
        return "auth";
    }
}
