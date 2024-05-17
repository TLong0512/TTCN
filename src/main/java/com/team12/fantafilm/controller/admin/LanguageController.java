package com.team12.fantafilm.controller.admin;

import java.util.List;

import com.team12.fantafilm.service.ILanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.team12.fantafilm.model.Language;

@Controller
@RequestMapping("/admin/language")
@SessionAttributes("soldTicketsCount")
public class LanguageController {
     @Autowired
     ILanguageService languageService;
    @GetMapping("/view-all")
    public String findAll(Model model)
    {
        List<Language> languages = languageService.findAll();
        model.addAttribute("languages",languages);
        model.addAttribute("language",new Language());
        return  "admin/language";
    }
    @PostMapping("/add-language")
    public String addlanguage(@ModelAttribute("language") Language language)
    {
        if(languageService.addLanguage(language))
        {
            return  "redirect:/admin/language/view-all";
        }
        else {
            return "admin/language/language";
        }
    }

    @PostMapping("/edit-language")
    public  String editlanguage(@ModelAttribute("language") Language language)
    {
        languageService.addLanguage(language);
        return  "redirect:/admin/language/view-all";
    }
    @GetMapping("/delete-language/{id}")
    public  String deletelanguage(@PathVariable("id") Long id)
    {
        if(languageService.delete(id))
        {
            return "redirect:/admin/language/view-all";
        }
        else
        {
            return "redirect:/admin/language/view-all";
        }
    }

}
