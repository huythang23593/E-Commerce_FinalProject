package com.mycompany.spring_mvc_project_final.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management")
public class SellerController {

    @RequestMapping("/home")
    public String viewHome(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }

        model.addAttribute("message", "Hello Seller: " + username);
        return "management/home";
    }
    @RequestMapping("/products") 
    public String showProducts(Model model) {
        return "management/products";
    }
}
