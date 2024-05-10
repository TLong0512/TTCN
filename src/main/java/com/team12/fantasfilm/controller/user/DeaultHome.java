package com.team12.fantasfilm.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DeaultHome {
    @GetMapping
    public String defaultHome()
    {
        return  "redirect:home";
    }
}
