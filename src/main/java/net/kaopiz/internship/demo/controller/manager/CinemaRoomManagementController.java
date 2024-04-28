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
import net.kaopiz.internship.demo.entity.CinemaRoom;
import net.kaopiz.internship.demo.service.ICinemaRoomService;
import net.kaopiz.internship.demo.service.ICinemaService;

@AllArgsConstructor
@Controller
@RequestMapping("/mn/5/ci-r-mn")
public class CinemaRoomManagementController extends BaseController {

	private final ICinemaRoomService cinemaRoomService;
	private final ICinemaService cinemaService;

	@GetMapping()
	public String getAllCinemaRoomRooms(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, @ModelAttribute("search") UserSearchDTO search,
			Model model) {

		Page<CinemaRoom> cinemaRoomPage = cinemaRoomService.searchCinemaRooms(search, PageRequest.of(page - 1, size));

		model.addAttribute("cinemaRoomPage", cinemaRoomPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", cinemaRoomPage.getTotalPages());

		model.addAttribute("cinemaRoom", new CinemaRoom());
		model.addAttribute("cinemaRooms", cinemaRoomService.getAllCinemaRooms());

		model.addAttribute("cinemas", cinemaService.getAllCinemas());

		model.addAttribute("cinemaRoomEdit", new CinemaRoom());

		return "admin/cinema_room_admin";

	}

	@GetMapping("/view/{cinemaId}")
	public String viewCinemaRoomByCinemaId(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, @PathVariable Long cinemaId,
			@ModelAttribute("search") UserSearchDTO search, Model model) {

		Page<CinemaRoom> cinemaRoomPage = cinemaRoomService.getAllCinemaRoomsByCinemaId(cinemaId,
				search, PageRequest.of(page - 1, size));

		model.addAttribute("cinemaRoomPage", cinemaRoomPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", cinemaRoomPage.getTotalPages());

		model.addAttribute("cinemaRoom", new CinemaRoom());
		model.addAttribute("cinemaRooms", cinemaRoomService.getAllCinemaRooms());

		model.addAttribute("cinemas", cinemaService.getAllCinemas());

		model.addAttribute("cinemaRoomEdit", new CinemaRoom());
		return "admin/cinema_room_admin";
	}

	@GetMapping("/getOne/{cinemaRoomId}")
	@ResponseBody
	public Optional<CinemaRoom> getOneCinemaRoomRoom(@PathVariable Long cinemaRoomId) {
		return Optional.ofNullable(cinemaRoomService.getCinemaRoomById(cinemaRoomId));
	}

	@PostMapping("/add")
	public String addNewCinemaRoomRoom(@ModelAttribute("cinemaRoom") @Valid CinemaRoom cinemaRoom, Model model) {

		CinemaRoom existedCinemaRoomName = cinemaRoomService.getCinemaRoomByName(cinemaRoom.getName());
		if (existedCinemaRoomName == null) {
			cinemaRoomService.addCinemaRoom(cinemaRoom);
		} else {
			model.addAttribute("error", "This cinemaRoom name has been taken, please try again");
		}

		return "redirect:/mn/5/ci-r-mn";

	}

	@PostMapping("/edit")
	public String updateCinema(@ModelAttribute("cinemaRoomEdit") @Valid CinemaRoom cinemaRoomEdit, Model model) {

		if (cinemaRoomEdit.getId() != null) {
			cinemaRoomService.updateCinemaRoom(cinemaRoomEdit);

		}

		return "redirect:/mn/5/ci-r-mn";
	}

	@GetMapping("/delete/{cinemaRoomId}")
	public String deletecinemaRoom(@PathVariable Long cinemaRoomId) {

		cinemaRoomService.deleteCinemaRoom(cinemaRoomId);

		return "redirect:/mn/5/ci-r-mn";

	}

	@GetMapping("/disable/{cinemaRoomId}")
	public String disablecinemaRoom(@PathVariable Long cinemaRoomId) {
		cinemaRoomService.setCinemaRoomActiveFlag(cinemaRoomId, false);
		return "redirect:/mn/5/ci-r-mn";
	}

	@GetMapping("/enable/{cinemaRoomId}")
	public String enablecinemaRoom(@PathVariable Long cinemaRoomId) {
		cinemaRoomService.setCinemaRoomActiveFlag(cinemaRoomId, true);
		return "redirect:/mn/5/ci-r-mn";
	}
}
