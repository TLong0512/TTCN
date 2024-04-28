package net.kaopiz.internship.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.controller.common.BaseController;
import net.kaopiz.internship.demo.entity.User;
import net.kaopiz.internship.demo.service.IUserService;

@AllArgsConstructor
@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {

	@Autowired
	private final IUserService userService;

	@GetMapping
	private String showRegistrationForm(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof User) {
			return "redirect:/home";
		}
		model.addAttribute("user", new User());
		return "user/register";
	}

	@PostMapping
	private String registerUser(@ModelAttribute("user") User user, @RequestParam("registerAvatar") MultipartFile avatar,
			Model model) {

		User existingUserName = userService.findUserByEmail(user.getEmail());

		if (existingUserName == null) {
			model.addAttribute("user", new User());
			userService.registerUser(user, avatar);
			model.addAttribute("success", true);
		} else {
			model.addAttribute("error", true);
		}

		return "user/register";

	}

}
