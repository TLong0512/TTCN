package com.team12.fantafilm.controller.admin;

import com.team12.fantafilm.model.Rated;
import com.team12.fantafilm.service.IRatedService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/admin/rated")
@SessionAttributes("soldTicketsCount")
public class RatedController {
    @Autowired
    IRatedService ratedService;
    @GetMapping("/view-all")
    public String findAll(Model model)
    {
        List<Rated> rateds = ratedService.findAll();
        model.addAttribute("rateds",rateds);
        model.addAttribute("rated",new Rated());
        return  "admin/rated";
    }
    @PostMapping("/add-rated")
    public String addRated(@ModelAttribute("rated") Rated rated)
    {
        if(ratedService.addRated(rated))
        {
            return  "redirect:/admin/rated/view-all";
        }
        else {
            return "admin/rated/add-rated";
        }
    }

    @PostMapping("/edit-rated")
    public  String editRated(@ModelAttribute("rated") Rated rated)
    {
        ratedService.addRated(rated);
    return  "redirect:/admin/rated/view-all";
    }
    @GetMapping("/delete-rated/{id}")
    public  String deleteRated(@PathVariable("id") Long id)
    {
        if(ratedService.delete(id))
        {
            return "redirect:/admin/rated/view-all";
        }
        else
        {
            return "redirect:/admin/rated/view-all";
        }
    }
}
