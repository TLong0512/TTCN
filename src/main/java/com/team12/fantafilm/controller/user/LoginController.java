package com.team12.fantafilm.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/login")

public class LoginController {
    @GetMapping
    public String login(){
        return "user/login";
    }
//    @GetMapping("signup")
//    public String signup(){
//        return "user/signup";
//    }
}
