package net.kaopiz.internship.demo.controller.manager;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.controller.common.BaseController;
import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.City;
import net.kaopiz.internship.demo.repository.ICityRepository;
import net.kaopiz.internship.demo.service.ICityService;

@Controller
@AllArgsConstructor
@RequestMapping("/mn/3/cm")
public class CityManagementController extends BaseController {

	private final ICityService cityService;

	@GetMapping()
	public String getAllCities(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, @ModelAttribute("search") UserSearchDTO search,
			Model model) {

		Page<City> cityPage = cityService.searchCities(search, PageRequest.of(page - 1, size));

		model.addAttribute("cityPage", cityPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", cityPage.getTotalPages());

		model.addAttribute("city", new City());

		model.addAttribute("cityEdit", new City());

		return "admin/city_admin";

	}

	@GetMapping("/getOne/{cityId}")
	@ResponseBody
	public Optional<City> getOne(@PathVariable Long cityId, Model model) {
		return Optional.ofNullable(cityService.getCityById(cityId));
	}

	@PostMapping("/add")
	public String addNewCity(@ModelAttribute("city") @Valid City city, Model model, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "admin/city_admin";
		}

		City existedCityName = cityService.getCityByName(city.getCityName());
		if (existedCityName == null) {
			cityService.addCity(city);
		} else {
			model.addAttribute("error", "This city name has been taken, please try again");
		}

		return "redirect:/mn/3/cm";

	}

	@PostMapping("/edit")
	public String updateCity(@ModelAttribute("cityEdit") @Valid City cityEdit, Model model) {

		if (cityEdit.getId() != null) {
			cityService.updateCity(cityEdit);

		}

		return "redirect:/mn/3/cm";
	}

	@GetMapping("/delete/{cityId}")
	public String deleteCity(@PathVariable Long cityId) {

		cityService.deleteCity(cityId);

		return "redirect:/mn/3/cm";

	}

	@GetMapping("/disable/{cityId}")
	public String disableCity(@PathVariable Long cityId) {
		cityService.setCityActiveFlag(cityId, false);
		return "redirect:/mn/3/cm";
	}

	@GetMapping("/enable/{cityId}")
	public String enableCity(@PathVariable Long cityId) {
		cityService.setCityActiveFlag(cityId, true);
		return "redirect:/mn/3/cm";
	}

}
