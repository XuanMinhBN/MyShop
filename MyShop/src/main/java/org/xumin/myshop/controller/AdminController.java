package org.xumin.myshop.controller;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xumin.myshop.entity.AuthUser;
import org.xumin.myshop.entity.Order;
import org.xumin.myshop.entity.Product;
import org.xumin.myshop.entity.User;
import org.xumin.myshop.service.EmailService;
import org.xumin.myshop.service.OrderService;
import org.xumin.myshop.service.ProductService;
import org.xumin.myshop.service.UserService;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final OrderService orderService;
    private final ProductService productService;
    private final EmailService emailService;

    @Autowired
    public AdminController(OrderService orderService, ProductService productService, EmailService emailService) {
        this.orderService = orderService;
        this.productService = productService;
        this.emailService = emailService;
    }

    //View Admin Home Page
    @GetMapping("/view")
    public String admin(Model model) {
        Integer orderQuantity = orderService.findAll().size();
        Integer productQuantity = productService.findAll().size();
        List<Order> orders = orderService.findAll().reversed();

        model.addAttribute("orders", orderQuantity);
        model.addAttribute("products", productQuantity);
        model.addAttribute("orderList", orders);
        return "admin/pages/homeAdmin";
    }

    //Product Management
    @GetMapping("/product")
    public String adminProduct(@RequestParam(name = "page",defaultValue = "1") Integer page, Model model) {
        model.addAttribute("title", "MyShop");
        Page<Product> productList = productService.findAll(page,8);
        model.addAttribute("totalPage",productList.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("productList", productList);
        return "admin/pages/product-admin";
    }

    //Email Sending
    @GetMapping("/email")
    public String email(){
        return "admin/pages/sendEmail";
    }
    @GetMapping("/email/send")
    public String emailSending(
            @RequestParam(name = "from") String sender,
            @RequestParam(name = "subject") String subject,
            @RequestParam(name = "content") String content,
            @RequestParam(name = "to") String receiver
    ){
        try{
            emailService.sendMessage(sender, subject, content, receiver);
        }catch (MessagingException | UnsupportedEncodingException exception){
            throw new RuntimeException(exception);
        }
        return "redirect:/admin/email";
    }
}
