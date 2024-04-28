package net.kaopiz.internship.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.service.ICinemaService;

@AllArgsConstructor
@Controller
@RequestMapping("/no-auth/ci/{cinemaId}")
public class ViewCinemaPageController {

	private ICinemaService cinemaService;

	public String viewCinemaDetails(@PathVariable Long cinemaId, Model model) {

		model.addAttribute("cinema", cinemaService.getCinemaById(cinemaId));

		return "user/cinema-details";
	}

}
