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
import net.kaopiz.internship.demo.entity.Room_Seat;
import net.kaopiz.internship.demo.service.ICinemaRoomService;
import net.kaopiz.internship.demo.service.IRoomSeatService;

@AllArgsConstructor
@Controller
@RequestMapping("/mn/6/r-seat-mn")
public class RoomSeatManagementController extends BaseController {

	private IRoomSeatService roomSeatService;
	private ICinemaRoomService cinemaRoomService;

	@GetMapping()
	public String getAllRoom_SeatRooms(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, @ModelAttribute("search") UserSearchDTO search,
			Model model) {

		Page<Room_Seat> roomSeatPage = roomSeatService.searchRoomSeats(search, PageRequest.of(page - 1, size));

		model.addAttribute("roomSeatPage", roomSeatPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", roomSeatPage.getTotalPages());

		model.addAttribute("roomSeat", new Room_Seat());
		model.addAttribute("roomSeats", roomSeatService.getAllRoomSeats());

		model.addAttribute("cinemaRooms", cinemaRoomService.getAllCinemaRooms());

		model.addAttribute("roomSeatEdit", new Room_Seat());

		return "admin/room_seat_admin";

	}

	@GetMapping("/view/{cinemaRoomId}")
	public String viewRoomSeatByCinemaRoomId(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, @PathVariable Long cinemaRoomId,
			@ModelAttribute("search") UserSearchDTO search, Model model) {

		Page<Room_Seat> roomSeatPage = roomSeatService.getAllRoomSeatsByCinemaRoomId(cinemaRoomId, search,
				PageRequest.of(page - 1, size));

		model.addAttribute("roomSeatPage", roomSeatPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", roomSeatPage.getTotalPages());

		model.addAttribute("roomSeat", new Room_Seat());
		model.addAttribute("roomSeats", roomSeatService.getAllRoomSeats());

		model.addAttribute("cinemas", cinemaRoomService.getAllCinemaRooms());

		model.addAttribute("roomSeatEdit", new Room_Seat());
		return "admin/room_seat_admin";
	}

	@GetMapping("/getOne/{roomSeatId}")
	@ResponseBody
	public Optional<Room_Seat> getOneRoomSeat(@PathVariable Long roomSeatId) {
		return Optional.ofNullable(roomSeatService.getRoomSeatById(roomSeatId));
	}

	@PostMapping("/add")
	public String addNewRoomSeat(@ModelAttribute("roomSeat") @Valid Room_Seat roomSeat, Model model) {

		roomSeatService.addRoomSeat(roomSeat);
		return "redirect:/mn/6/r-seat-mn";

	}

	@PostMapping("/edit")
	public String updateRoomSeat(@ModelAttribute("roomSeatEdit") @Valid Room_Seat roomSeatEdit, Model model) {

		if (roomSeatEdit.getId() != null) {
			roomSeatService.updateRoomSeat(roomSeatEdit);

		}

		return "redirect:/mn/6/r-seat-mn";
	}

	@GetMapping("/delete/{roomSeatId}")
	public String deleteRoomSeat(@PathVariable Long roomSeatId) {

		roomSeatService.deleteRoomSeat(roomSeatId);

		return "redirect:/mn/6/r-seat-mn";

	}

	@GetMapping("/disable/{roomSeatId}")
	public String disableRoomSeat(@PathVariable Long roomSeatId) {
		roomSeatService.setRoomSeatActiveFlag(roomSeatId, false);
		return "redirect:/mn/6/r-seat-mn";
	}

	@GetMapping("/enable/{roomSeatId}")
	public String enableRoomSeat(@PathVariable Long roomSeatId) {
		roomSeatService.setRoomSeatActiveFlag(roomSeatId, true);
		return "redirect:/mn/6/r-seat-mn";
	}

}
