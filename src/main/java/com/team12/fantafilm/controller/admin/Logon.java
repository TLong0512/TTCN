package com.team12.fantafilm.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logon")
public class Logon {
    @GetMapping
    public  String logon()
    {
        return "admin/logon";
    }
}
