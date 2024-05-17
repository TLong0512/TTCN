package com.team12.fantafilm.controller.admin;


import com.team12.fantafilm.model.RankCustomer;
import com.team12.fantafilm.service.IRankCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/rankCustomer")
public class RankCustomerController {
    @Autowired
    IRankCustomerService rankCustomerService;
    @GetMapping("/view-all")
    public String findAll(Model model)
    {
        List<RankCustomer> rankCustomers = rankCustomerService.findAll();
        model.addAttribute("rankCustomers",rankCustomers);
        model.addAttribute("rankCustomer",new RankCustomer());
        return  "admin/rankCustomer";
    }
    @PostMapping("/add-rankCustomer")
    public String addRankCustomer(@ModelAttribute("rankCustomer") RankCustomer rankCustomer)
    {
        if(rankCustomerService.addRankCustomer(rankCustomer))
        {
            return  "redirect:/admin/rankCustomer/view-all";
        }
        else {
            return "admin/rankCustomer/rankCustomer";
        }
    }

    @PostMapping("/edit-rankCustomer")
    public  String editRankCustomer(@ModelAttribute("rankCustomer") RankCustomer rankCustomer)
    {
        rankCustomerService.addRankCustomer(rankCustomer);
        return  "redirect:/admin/rankCustomer/view-all";
    }
    @GetMapping("/delete-rankCustomer/{id}")
    public  String deleteRankCustomer(@PathVariable("id") Long id)
    {
        if(rankCustomerService.delete(id))
        {
            return "redirect:/admin/rankCustomer/view-all";
        }
        else
        {
            return "redirect:/admin/rankCustomer/view-all";
        }
    }
}
