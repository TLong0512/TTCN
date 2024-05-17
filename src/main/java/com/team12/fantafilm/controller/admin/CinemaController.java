package com.team12.fantafilm.controller.admin;

import com.team12.fantafilm.model.Cinema;
import com.team12.fantafilm.service.ICinemaService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/admin/cinema")
@SessionAttributes("soldTicketsCount")
public class CinemaController {
    @Autowired
    ICinemaService cinemaService;
    @GetMapping("/view-all")
    public String findAll(Model model)
    {
        List<Cinema> cinemas = cinemaService.findAll();
        model.addAttribute("cinemas",cinemas);
        model.addAttribute("cinema",new Cinema());
        return  "admin/cinema";
    }
    @PostMapping("/add-cinema")
    public String addCinema(@ModelAttribute("cinema") Cinema cinema)
    {
        if(cinemaService.addCinema(cinema))
        {
            return  "redirect:/admin/cinema/view-all";
        }
        else {
            return "admin/cinema/add-cinema";
        }
    }

    @PostMapping("/edit-cinema")
    public  String editCinema(@ModelAttribute("cinema") Cinema cinema)
    {
        cinemaService.addCinema(cinema);
    return  "redirect:/admin/cinema/view-all";
    }
    @GetMapping("/delete-cinema/{id}")
    public  String deleteCinema(@PathVariable("id") Long id)
    {
        if(cinemaService.delete(id))
        {
            return "redirect:/admin/cinema/view-all";
        }
        else
        {
            return "redirect:/admin/cinema/view-all";
        }
    }
}
