package net.kaopiz.internship.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.controller.common.BaseController;

@Controller
@RequestMapping("/ad/1/home")
@AllArgsConstructor
public class ViewHomePageController extends BaseController {

	@GetMapping()
	public String viewHomePage(Model model) {
		return "admin/index";
	}

}
