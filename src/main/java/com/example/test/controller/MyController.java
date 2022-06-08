package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class MyController {
//Principal principal, Model model

    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/user")
    public String user(Principal principal, Model model){
        model.addAttribute("username", principal.getName());
        return "user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
