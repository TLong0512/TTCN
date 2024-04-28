package net.kaopiz.internship.demo.controller.employee;

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
import net.kaopiz.internship.demo.entity.Show;
import net.kaopiz.internship.demo.service.ICinemaRoomService;
import net.kaopiz.internship.demo.service.IFilmService;
import net.kaopiz.internship.demo.service.IShowService;

@AllArgsConstructor
@Controller
@RequestMapping("/emp/9/show-mn")
public class ShowManagementController extends BaseController {

	private IShowService showService;
	private ICinemaRoomService cinemaRoomService;
	private IFilmService filmService;

	@GetMapping()
	public String getAllShows(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, @ModelAttribute("search") UserSearchDTO search,
			Model model) {

		Page<Show> showPage = showService.searchShows(search, PageRequest.of(page - 1, size));

		model.addAttribute("showPage", showPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", showPage.getTotalPages());

		model.addAttribute("show", new Show());
		model.addAttribute("shows", showService.getAllShows());

		model.addAttribute("cinemaRooms", cinemaRoomService.getAllCinemaRooms());
		model.addAttribute("films", filmService.getAllFilms());

		model.addAttribute("showEdit", new Show());

		return "admin/show_admin";

	}

	@GetMapping("/view/{cinemaRoomId}")
	public String viewShowByCinemaRoomId(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, @PathVariable Long cinemaRoomId,
			@ModelAttribute("search") UserSearchDTO search, Model model) {

		Page<Show> showPage = showService.getAllShowsByCinemaRoomId(cinemaRoomId, search,
				PageRequest.of(page - 1, size));

		model.addAttribute("showPage", showPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", showPage.getTotalPages());

		model.addAttribute("show", new Show());
		model.addAttribute("shows", showService.getAllShows());

		model.addAttribute("cinemas", cinemaRoomService.getAllCinemaRooms());

		model.addAttribute("showEdit", new Show());
		return "admin/show_admin";
	}

	@GetMapping("/getOne/{showId}")
	@ResponseBody
	public Optional<Show> getOneShowRoom(@PathVariable Long showId) {
		return Optional.ofNullable(showService.getShowById(showId));
	}

	@PostMapping("/add")
	public String addNewShow(@ModelAttribute("show") @Valid Show show, Model model) {

		showService.addShow(show);
		return "redirect:/emp/9/show-mn";

	}

	@PostMapping("/edit")
	public String updateShow(@ModelAttribute("showEdit") @Valid Show showEdit, Model model) {

		if (showEdit.getId() != null) {
			showService.updateShow(showEdit);

		}

		return "redirect:/emp/9/show-mn";
	}

	@GetMapping("/delete/{showId}")
	public String deleteShow(@PathVariable Long showId) {

		showService.deleteShow(showId);

		return "redirect:/emp/9/show-mn";

	}

	@GetMapping("/disable/{showId}")
	public String disableShow(@PathVariable Long showId) {
		showService.setShowActiveFlag(showId, false);
		return "redirect:/emp/9/show-mn";
	}

	@GetMapping("/enable/{showId}")
	public String enableShow(@PathVariable Long showId) {
		showService.setShowActiveFlag(showId, true);
		return "redirect:/emp/9/show-mn";
	}
}
