package net.kaopiz.internship.demo.controller.manager;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.controller.common.BaseController;
import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.Film;
import net.kaopiz.internship.demo.entity.User;
import net.kaopiz.internship.demo.service.IFilmService;
import net.kaopiz.internship.demo.service.ICategoryService;

@Controller
@AllArgsConstructor
@RequestMapping("/mn/8/f-mn")
public class FilmManagementController extends BaseController {

	private final IFilmService filmService;
	private final ICategoryService categoryService;

	@GetMapping()
	public String getAllFilms(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "16") int size, @ModelAttribute("search") UserSearchDTO search,
			Model model) {

		Page<Film> filmPage = filmService.searchFilms(search, PageRequest.of(page - 1, size));

		model.addAttribute("filmPage", filmPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", filmPage.getTotalPages());

		model.addAttribute("film", new Film());
		model.addAttribute("categories", categoryService.getAllCities());

		model.addAttribute("filmEdit", new Film());

		return "admin/film_admin";

	}

	@GetMapping("/getOne/{filmId}")
	@ResponseBody
	public Optional<Film> getOneFilm(@PathVariable Long filmId) {
		return Optional.ofNullable(filmService.findFilmById(filmId));
	}

	@PostMapping("/add")
	public String addNewFilm(@ModelAttribute("film") @Valid Film film,
			@RequestParam("addThumbnail") MultipartFile thumbnail, @RequestParam("addTrailer") MultipartFile trailer,
			Model model, BindingResult bindingResult) throws IOException {

		try {
			if (bindingResult.hasErrors()) {
				return "admin/film_admin";
			}

			Film existingFilmName = filmService.findFilmByName(film.getName());
			if (existingFilmName == null) {
				filmService.addFilm(film, thumbnail, trailer);
			} else {
				model.addAttribute("error", "This email has been used, please try again");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/mn/8/f-mn";
		}

		return "redirect:/mn/8/f-mn";

	}

	@PostMapping("/edit")
	public String updateFilm(@ModelAttribute("filmEdit") @Valid Film filmEdit,
			@RequestParam("thumbnailEdit") MultipartFile thumbnailEdit,
			@RequestParam("trailerEdit") MultipartFile trailerEdit, Model model) {

		if (filmEdit.getId() != null) {
			Film existedFilm = filmService.findFilmById(filmEdit.getId());

			if (!thumbnailEdit.isEmpty() && !trailerEdit.isEmpty()) {
				filmEdit.setThumbnail(existedFilm.getThumbnail());
				filmEdit.setTrailer(existedFilm.getTrailer());
				filmService.updateFilm(filmEdit, thumbnailEdit, trailerEdit);
			}

		}

		return "redirect:/mn/8/f-mn";
	}

	@GetMapping("/delete/{filmId}")
	public String deletefilm(@PathVariable Long filmId) {

		filmService.deleteFilm(filmId);

		return "redirect:/mn/8/f-mn";

	}

	@GetMapping("/disable/{filmId}")
	public String disablefilm(@PathVariable Long filmId) {
		filmService.setFilmActiveFlag(filmId, false);
		return "redirect:/mn/8/f-mn";
	}

	@GetMapping("/enable/{filmId}")
	public String enablefilm(@PathVariable Long filmId) {
		filmService.setFilmActiveFlag(filmId, true);
		return "redirect:/mn/8/f-mn";
	}

}
