package net.kaopiz.internship.demo.controller.admin;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.controller.common.BaseController;
import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.User;
import net.kaopiz.internship.demo.service.IRoleService;
import net.kaopiz.internship.demo.service.IUserService;
import net.sf.jasperreports.engine.JRException;

@Controller
@AllArgsConstructor
@RequestMapping("/ad/2/um")
public class UserManagementController extends BaseController {

	private final IUserService userService;
	private final IRoleService roleService;

	@GetMapping()
	private String getAllUsers(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, @ModelAttribute("search") UserSearchDTO search,
			Model model) {

		Page<User> userPage = userService.searchUsers(search, PageRequest.of(page - 1, size));

		model.addAttribute("userPage", userPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", userPage.getTotalPages());

		model.addAttribute("roles", roleService.getAllRoles());

		model.addAttribute("user", new User());

		model.addAttribute("userEdit", new User());

		return "admin/user_admin";
	}

	@GetMapping("/getOne/{userId}")
	@ResponseBody
	public Optional<User> getOne(@PathVariable Long userId, Model model) {
		return Optional.ofNullable(userService.findUserById(userId));
	}

	@GetMapping("/getAll")
	@ResponseBody
	public List<User> getAll() {
		return userService.getAllUsers();
	}

	@PostMapping("/add")
	public String addNewUser(@ModelAttribute("user") @Valid User user, @RequestParam("addAvatar") MultipartFile avatar,
			Model model, BindingResult bindingResult) throws IOException {

		try {
			if (bindingResult.hasErrors()) {
				return "admin/user_admin";
			}

			User existingUserName = userService.findUserByEmail(user.getEmail());
			if (existingUserName == null) {
				userService.addUser(user, avatar);
			} else {
				model.addAttribute("error", "This email has been used, please try again");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/ad/2/um";
		}
		return "redirect:/ad/2/um";
	}

	@PostMapping("/edit")
	public String editUser(@ModelAttribute("userEdit") @Valid User userEdit,
			@RequestParam("editAvatar") MultipartFile avatar, Model model, BindingResult bindingResult)
			throws IOException {

		if (bindingResult.hasErrors()) {
			return "admin/user_admin";
		}

		if (userEdit.getId() != null) {
			User existedUser = userService.findUserById(userEdit.getId());

			if (!avatar.isEmpty()) {
				userEdit.setAvatar(existedUser.getAvatar());
				userService.updateUser(userEdit, avatar);
			}
		}
		return "redirect:/ad/2/um";
	}

	@GetMapping("/delete/{userId}")
	public String deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return "redirect:/ad/2/um";
	}

	@GetMapping("/disable/{userId}")
	public String disableUser(@PathVariable Long userId) {
		userService.setUserActiveFlag(userId, false);
		return "redirect:/ad/2/um";
	}

	@GetMapping("/enable/{userId}")
	public String enableUser(@PathVariable Long userId) {
		userService.setUserActiveFlag(userId, true);
		return "redirect:/ad/2/um";
	}

	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		userService.exportReport(format, this.getCurrentUser());
		return "redirect:/ad/2/um";
	}
}
