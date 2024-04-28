package net.kaopiz.internship.demo.controller.manager;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import net.kaopiz.internship.demo.entity.Cinema;
import net.kaopiz.internship.demo.service.ICinemaService;
import net.kaopiz.internship.demo.service.ICityService;

@Controller
@AllArgsConstructor
@RequestMapping("/mn/4/ci-mn")
public class CinemaManagementController extends BaseController {

	private final ICinemaService cinemaService;
	private final ICityService cityService;

	@GetMapping()
	public String getAllCinemas(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, @ModelAttribute("search") UserSearchDTO search,
			Model model) {

		Page<Cinema> cinemaPage = cinemaService.searchCinemas(search, PageRequest.of(page - 1, size));

		model.addAttribute("cinemaPage", cinemaPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", cinemaPage.getTotalPages());

		model.addAttribute("cinema", new Cinema());
		model.addAttribute("cities", cityService.getAllCities());

		model.addAttribute("cinemaEdit", new Cinema());

		return "admin/cinema_admin";

	}

	@GetMapping("/getOne/{cinemaId}")
	@ResponseBody
	public Optional<Cinema> getOneCinema(@PathVariable Long cinemaId) {
		return Optional.ofNullable(cinemaService.getCinemaById(cinemaId));
	}

	@PostMapping("/add")
	public String addNewCinema(@ModelAttribute("cinema") @Valid Cinema cinema, Model model) {

		Cinema existedCinemaName = cinemaService.getCinemaByName(cinema.getCinemaName());
		if (existedCinemaName == null) {
			cinemaService.addCinema(cinema);
		} else {
			model.addAttribute("error", "This cinema name has been taken, please try again");
		}

		return "redirect:/mn/4/ci-mn";

	}

	@PostMapping("/edit")
	public String updateCity(@ModelAttribute("cinemaEdit") @Valid Cinema cinemaEdit, Model model) {

		if (cinemaEdit.getId() != null) {
			cinemaService.updateCinema(cinemaEdit);

		}

		return "redirect:/mn/4/ci-mn";
	}

	@GetMapping("/delete/{cinemaId}")
	public String deletecinema(@PathVariable Long cinemaId) {

		cinemaService.deleteCinema(cinemaId);

		return "redirect:/mn/4/ci-mn";

	}

	@GetMapping("/disable/{cinemaId}")
	public String disablecinema(@PathVariable Long cinemaId) {
		cinemaService.setCinemaActiveFlag(cinemaId, false);
		return "redirect:/mn/4/ci-mn";
	}

	@GetMapping("/enable/{cinemaId}")
	public String enablecinema(@PathVariable Long cinemaId) {
		cinemaService.setCinemaActiveFlag(cinemaId, true);
		return "redirect:/mn/4/ci-mn";
	}

}
