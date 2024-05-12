package com.team12.fantafilm.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {
    @GetMapping
    public  String adminHome()
    {
        return "redirect:/admin/";
    }
    @GetMapping("/")
    public  String home()
    {
        return "admin/index";
    }
}