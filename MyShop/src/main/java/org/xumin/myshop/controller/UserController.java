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
import org.xumin.myshop.service.UserService;

@Controller
@RequestMapping("/account")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/purchase")
    public String profile(Model model, Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User user = userService.findByUsername(authUser.getUsername());
        model.addAttribute("user", user);
        return "pages/user";
    }

    @GetMapping("/profile")
    public String order(Model model, Authentication authentication) {
//        AuthUser authUser = (AuthUser) authentication.getPrincipal();
//        User user = userService.findByUsername(authUser.getUsername());
        model.addAttribute("user", userService.findAll());
        return "pages/profile";
    }
}
