package com.team12.fantafilm.controller.admin;


import com.team12.fantafilm.model.MovieType;
import com.team12.fantafilm.service.IMovieTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/movieType")
public class MovieTypeController {
    @Autowired
    IMovieTypeService movieTypeService;
    @GetMapping("/view-all")
    public String findAll(Model model)
    {
        List<MovieType> movieTypes = movieTypeService.findAll();
        model.addAttribute("movieTypes",movieTypes);
        model.addAttribute("movieType",new MovieType());
        return  "admin/movieType";
    }
    @PostMapping("/add-movieType")
    public String addMovieType(@ModelAttribute("movieType") MovieType movieType)
    {
        if(movieTypeService.addMovieType(movieType))
        {
            return  "redirect:/admin/movieType/view-all";
        }
        else {
            return "admin/movieType";
        }
    }

    @PostMapping("/edit-movieType")
    public  String editMovieType(@ModelAttribute("movieType") MovieType movieType)
    {
        movieTypeService.addMovieType(movieType);
        return  "redirect:/admin/movieType/view-all";
    }
    @GetMapping("/delete-movieType/{id}")
    public  String deleteMovieType(@PathVariable("id") Long id)
    {
        if(movieTypeService.delete(id))
        {
            return "redirect:/admin/movieType/view-all";
        }
        else
        {
            return "redirect:/admin/movieType/view-all";
        }
    }
}
