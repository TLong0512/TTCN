package com.team12.fantafilm.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    @GetMapping
    public String signup(){
        return "user/signup";
    }
//    @GetMapping("/login")
//    public String login() {
//        return "user/login";
//    }
}
