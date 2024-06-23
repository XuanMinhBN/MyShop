//package org.xumin.myshop.controller;
//
//import jakarta.mail.MessagingException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.xumin.myshop.service.EmailService;
//
//import java.io.UnsupportedEncodingException;
//
//@Controller
//@RequestMapping("/email")
//public class EmailController {
//    private final EmailService emailService;
//
//    @Autowired
//    public EmailController(EmailService emailService) {
//        this.emailService = emailService;
//    }
//
//    @GetMapping("/send")
//    public String email(){
//        try{
//            emailService.sendMessage(
//                    "Xumin",
//                    "[TEST] Subject",
//                    "[TEST] content",
//                    "linqan7@gmail.com"
//            );
//        }catch (MessagingException | UnsupportedEncodingException exception){
//            throw new RuntimeException(exception);
//        }
//        return "redirect:/admin/view";
//    }
//}
