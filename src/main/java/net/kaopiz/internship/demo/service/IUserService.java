package net.kaopiz.internship.demo.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.User;
import net.sf.jasperreports.engine.JRException;

public interface IUserService {

	Page<User> getAllUsers(Pageable pageable);

	List<User> getAllUsers();

	User findUserById(Long userId);

	User findUserByEmail(String userEmail);

	void addUser(User user, MultipartFile avatar);

	void updateUser(User user, MultipartFile avatar);

	void deleteUser(Long userId);

	void setUserActiveFlag(Long userId, boolean flag);

	void registerUser(User user, MultipartFile avatar);

	Long countUser();

	Page<User> searchUsers(UserSearchDTO search, Pageable pageable);

	void exportReport(String reportFomat, User currentUser) throws FileNotFoundException, JRException;

}
