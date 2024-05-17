package com.team12.fantafilm.controller.admin;


import com.team12.fantafilm.model.Performer;
import com.team12.fantafilm.service.IPerformerService;
import com.team12.fantafilm.util.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin/performer")
public class PerformerController {
    @Autowired
    IPerformerService performerService;
    @Autowired
    UploadImage uploadImage;
    @GetMapping("/view-all")
    public String findAll(Model model)
    {
        List<Performer> performers = performerService.findAll();
        model.addAttribute("performers",performers);
        model.addAttribute("performer",new Performer());
        return  "admin/performer";
    }
    @PostMapping("/add-performer")
    public String addPerformer(Model model,
                               @RequestParam(name="name") String name,

                               @RequestParam(name="image") MultipartFile multipartFile
                               )
    {
        uploadImage.handerUpLoadFile(multipartFile);
        try
        {
            Performer performer = Performer.builder()
                    .name(name)

                    .image(multipartFile.getOriginalFilename())
                    .build();
            performerService.addPerformer(performer);
            return  "redirect:/admin/performer/view-all";

        }
        catch (Exception e)
        {
            return null;
        }
    }

    @PostMapping("/edit-performer")
    public  String editPerformer(@ModelAttribute("performer") Performer performer)
    {
        performerService.addPerformer(performer);
        return  "redirect:/admin/performer/view-all";
    }
    @GetMapping("/delete-performer/{id}")
    public  String deletePerformer(@PathVariable("id") Long id)
    {
        if(performerService.delete(id))
        {
            return "redirect:/admin/performer/view-all";
        }
        else
        {
            return "redirect:/admin/performer/view-all";
        }
    }
}
