package com.team12.fantafilm.controller.user;

import com.team12.fantafilm.model.Phim;
import com.team12.fantafilm.service.impl.PhimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/all")
public class PhimController {
    @Autowired
    private PhimService phimService;
    @GetMapping
    public String all(Model model){
        List<Phim> list = this.phimService.getAll();
        model.addAttribute("list",list);
        return ("user/all");
    }
}
