package com.team12.fantafilm.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home()
    {
        return "user/index";
    }

    @GetMapping("contact")
    public  String contact(){return  "user/contact";}

    @GetMapping("about")
    public  String about(){return "user/about";}

    @GetMapping("blog")
    public  String blog()
    {
        return "user/blog";
    }
}
