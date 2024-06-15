package org.xumin.myshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xumin.myshop.entity.AuthUser;
import org.xumin.myshop.entity.User;
import org.xumin.myshop.entity.UserAddress;
import org.xumin.myshop.entity.UserDetail;
import org.xumin.myshop.reponsitory.UserReponsitory;
import org.xumin.myshop.service.UserAddressService;
import org.xumin.myshop.service.UserDetailService;
import org.xumin.myshop.service.UserService;

import java.util.List;

@Controller
@RequestMapping("main")
public class PaymentController {

    private final UserService userService;
    private final UserAddressService userAddressService;
    private final UserDetailService userDetailService;

    @Autowired
    public PaymentController(UserService userService, UserAddressService userAddressService, UserDetailService userDetailService) {
        this.userService = userService;
        this.userAddressService = userAddressService;
        this.userDetailService = userDetailService;
    }

    @GetMapping("/payment")
    public String payment(Authentication authentication, Model model) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User user = userService.findByUsername(authUser.getUsername());
        UserAddress defaultAddress = userAddressService.getUserAddressDefault(user.getUserAddresses());
        UserDetail userDetail = userDetailService.getUserDetailInfo(user.getUserDetail());

        model.addAttribute("user", user);
        model.addAttribute("defaultAddress", defaultAddress);
        model.addAttribute("userDetail", userDetail);
        return "pages/payment";
    }

}
