package com.team12.fantafilm.controller.admin;


import com.team12.fantafilm.model.ServiceType;
import com.team12.fantafilm.service.IServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/serviceType")
public class ServiceTypeController {
    @Autowired
    IServiceTypeService serviceTypeService;
    @GetMapping("/view-all")
    public String findAll(Model model)
    {
        List<ServiceType> serviceTypes = serviceTypeService.findAll();
        model.addAttribute("serviceTypes",serviceTypes);
        model.addAttribute("serviceType",new ServiceType());
        return  "admin/serviceType";
    }
    @PostMapping("/add-serviceType")
    public String addServiceType(@ModelAttribute("serviceType") ServiceType serviceType)
    {
        if(serviceTypeService.addServiceType(serviceType))
        {
            return  "redirect:/admin/serviceType/view-all";
        }
        else {
            return "admin/serviceType";
        }
    }

    @PostMapping("/edit-serviceType")
    public  String editServiceType(@ModelAttribute("serviceType") ServiceType serviceType)
    {
        serviceTypeService.addServiceType(serviceType);
        return  "redirect:/admin/serviceType/view-all";
    }
    @GetMapping("/delete-serviceType/{id}")
    public  String deleteServiceType(@PathVariable("id") Long id)
    {
        if(serviceTypeService.delete(id))
        {
            return "redirect:/admin/serviceType/view-all";
        }
        else
        {
            return "redirect:/admin/serviceType/view-all";
        }
    }
}
