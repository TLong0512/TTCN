package net.kaopiz.internship.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.controller.common.BaseController;
import net.kaopiz.internship.demo.service.ICityService;
import net.kaopiz.internship.demo.service.IFilmService;

@AllArgsConstructor
@Controller
public class HomeController extends BaseController {

	private final ICityService cityService;
	private final IFilmService filmService;

	@GetMapping("/home")
	public String viewHome1(Model model) {
		model.addAttribute("cities", cityService.getAllCities());
		model.addAttribute("films", filmService.getAllFilms());
		return "user/index";
	}

	@GetMapping("/index2")
	public String viewHome2() {
		return "user/index-2";
	}

}
