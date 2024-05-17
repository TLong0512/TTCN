package com.team12.fantafilm.controller.admin;


import com.team12.fantafilm.model.Director;
import com.team12.fantafilm.service.IDirectorService;
import com.team12.fantafilm.util.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin/director")
public class DirectorController {
    @Autowired
    IDirectorService directorService;
    @Autowired
    UploadImage uploadImage;
    @GetMapping("/view-all")
    public String findAll(Model model)
    {
        List<Director> directors = directorService.findAll();
        model.addAttribute("directors",directors);
        model.addAttribute("director",new Director());
        return  "admin/director";
    }
    @PostMapping("/add-director")
    public String addDirector(Model model,
                               @RequestParam(name="name") String name,
                               @RequestParam(name="image") MultipartFile multipartFile
                               )
    {
        uploadImage.handerUpLoadFile(multipartFile);
        try
        {
            Director director = Director.builder()
                    .name(name)
                    .image(multipartFile.getOriginalFilename())
                    .build();
            directorService.addDirector(director);
            return  "redirect:/admin/director/view-all";

        }
        catch (Exception e)
        {
            return null;
        }
    }

    @PostMapping("/edit-director")
    public  String editDirector(@ModelAttribute("director") Director director)
    {
        directorService.addDirector(director);
        return  "redirect:/admin/director/view-all";
    }
    @GetMapping("/delete-director/{id}")
    public  String deleteDirector(@PathVariable("id") Long id)
    {
        if(directorService.delete(id))
        {
            return "redirect:/admin/director/view-all";
        }
        else
        {
            return "redirect:/admin/director/view-all";
        }
    }
}
