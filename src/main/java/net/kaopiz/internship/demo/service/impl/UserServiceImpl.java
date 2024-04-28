package net.kaopiz.internship.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.dto.UserDTO;
import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.Role;
import net.kaopiz.internship.demo.entity.User;
import net.kaopiz.internship.demo.repository.IRoleRepository;
import net.kaopiz.internship.demo.repository.IUserRepository;
import net.kaopiz.internship.demo.service.IUserService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

	private final IUserRepository userRepository;
	private final IRoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public void addUser(User user, MultipartFile avatar) {
		// TODO Auto-generated method stub
		Role userRole = roleRepository.findByName("ROLE_USER");
		if (userRole == null) {
			userRole = new Role();
			userRole.setName("ROLE_USER");
			roleRepository.save(userRole);
		}

		Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
		Role roleManager = roleRepository.findByName("ROLE_MANAGER");
		Role roleEmplyee = roleRepository.findByName("ROLE_EMPLOYEE");
		Role roleUser = roleRepository.findByName("ROLE_USER");

		// Admin là quyền cao nhất, người dùng khi được thêm với quyền admin thì sẽ được
		// thêm cả quyền employee và manager
		user.getRoles().add(userRole);
		if (user.getRoles().contains(roleAdmin)) {
			if (!user.getRoles().contains(roleManager)) {
				user.getRoles().add(roleManager);
				if (!user.getRoles().contains(roleEmplyee)) {
					user.getRoles().add(roleEmplyee);
					if (!user.getRoles().contains(roleUser)) {
						user.getRoles().add(roleUser);
					}
				}
			}
		} else {
			if (user.getRoles().contains(roleManager)) {
				if (!user.getRoles().contains(roleEmplyee)) {
					user.getRoles().add(roleEmplyee);
				}
			}

		}

		// Mã hóa mật khẩu
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepository.save(user);
		user.setCode(generateUserCode(user));
		user.setLogInCount((long) 0);
		user.setTotalSpent(BigDecimal.ZERO);
		savedUser = userRepository.save(user);
		try {
			saveAvatar(savedUser, avatar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(User user, MultipartFile avatar) {
		User existingUser = userRepository.findById(user.getId()).orElse(null);
		user.setCreatedAt(existingUser.getCreatedAt());
		user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCode(generateUserCode(existingUser));
		User savedUser = userRepository.save(user);
		if (avatar != null) {
			try {
				saveAvatar(savedUser, avatar);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
	}

	@Override
	public void registerUser(User user, MultipartFile avatar) {

		Role userRole = roleRepository.findByName("ROLE_USER");
		if (userRole == null) {
			userRole = new Role();
			userRole.setName("ROLE_USER");
			roleRepository.save(userRole);
		}

		user.getRoles().add(userRole);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepository.save(user);
		user.setCode(generateUserCode(user));
		user.setLogInCount((long) 0);
		user.setTotalSpent(BigDecimal.ZERO);
		savedUser = userRepository.save(user);
		try {
			saveAvatar(savedUser, avatar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Long countUser() {
		return userRepository.count();
	}

	@Override
	public Page<User> getAllUsers(Pageable pageable) {
		// TODO Auto-generated method stub
		return userRepository.findAll(pageable);
	}

	// Hàm lưu ảnh avatar người dùng vào static/avatar và lưu tên ảnh vào database
	private void saveAvatar(User user, MultipartFile avatar) throws IOException {

		if (avatar == null || avatar.isEmpty()) {
			return;
		}

		String originalFileName = avatar.getOriginalFilename();
		String uniqueFileName = generateUniqueFileName(originalFileName);

		Path avatarImagePath = Paths.get("src/main/resources/static/avatar/" + uniqueFileName);

		if (!Files.exists(avatarImagePath)) {
			Files.createDirectories(avatarImagePath);
		}

		try {
			Files.copy(avatar.getInputStream(), avatarImagePath, StandardCopyOption.REPLACE_EXISTING);
			user.setAvatar(uniqueFileName);
			userRepository.save(user);
		} catch (IOException e) {
			throw new RuntimeException("Failed to save avatar for user: " + user.getId(), e);
		}

	}

	// Tạo ra một tên file độc nhất cho ảnh người dùng để tránh bị trùng tên file
	private String generateUniqueFileName(String originalFileName) {
		String timestamp = String.valueOf(System.currentTimeMillis());
		return timestamp + "_" + originalFileName;
	}

	@Override
	public User findUserById(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public User findUserByEmail(String userEmail) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(userEmail).orElse(null);
	}

	@Override
	public void setUserActiveFlag(Long userId, boolean flag) {
		// TODO Auto-generated method stub
		User user = findUserById(userId);
		if (user != null) {
			user.setActiveFlag(flag);
		}
		userRepository.save(user);
	}

	@Override
	public Page<User> searchUsers(UserSearchDTO search, Pageable pageable) {

		String status = search.getStatus();
		String keyword = search.getKeyword();

		if (keyword != null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return userRepository.findByActiveFlagAndFullNameContainingOrderByCreatedAtDesc(true, keyword,
						pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return userRepository.findByActiveFlagAndFullNameContainingOrderByCreatedAtDesc(false, keyword,
						pageable);
			} else {
				return userRepository.findByFullNameContaining(keyword, pageable);
			}
		}

		else if (keyword == null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return userRepository.findByActiveFlagOrderByCreatedAtDesc(true, pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return userRepository.findByActiveFlagOrderByCreatedAtDesc(false, pageable);
			} else {
				return userRepository.findByFullNameContaining(keyword, pageable);
			}
		} else {
			return getAllUsers(pageable);
		}

	}

	private String generateUserCode(User user) {
		int currentIndex = 0;
		int desireIndex = 0;
		Role userRole = null;
		for (Role value : user.getRoles()) {
			if (currentIndex == desireIndex) {
				userRole = value;
				break;
			}
		}
		String[] yearAndMonth = user.getCreatedAt().toString().split("-");

		if (userRole.getName().equals("ROLE_ADMIN")) {
			return yearAndMonth[0].substring(2, 4) + yearAndMonth[1] + String.valueOf('A') + user.getId().toString();
		}
		if (userRole.getName().equals("ROLE_MANAGER")) {
			return yearAndMonth[0].substring(2, 4) + yearAndMonth[1] + String.valueOf('M') + user.getId().toString();
		}
		if (userRole.getName().equals("ROLE_EMPLOYEE")) {
			return yearAndMonth[0].substring(2, 4) + yearAndMonth[1] + String.valueOf('E') + user.getId().toString();
		} else {
			return yearAndMonth[0].substring(2, 4) + yearAndMonth[1] + String.valueOf('U') + user.getId().toString();
		}
	}

	@Override
	public void exportReport(String reportFomat, User currentUser) throws FileNotFoundException, JRException {

		String path = "C:\\Users\\User\\Desktop\\Reports";

		List<User> users = getAllUsers();

		List<UserDTO> userDTOes = new ArrayList();

		for (User user : users) {
			UserDTO value = new UserDTO();
			value.setDtoCode(user.getCode());
			value.setDtoTotalSpent(user.getTotalSpent());
			userDTOes.add(value);
		}

		// tải file và giải nén nó
		File file = ResourceUtils.getFile("classpath:users.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		JRBeanCollectionDataSource userDataSource = new JRBeanCollectionDataSource(users);
		JRBeanCollectionDataSource userDtoDataSource = new JRBeanCollectionDataSource(userDTOes);
		Map<String, Object> userMap = new HashMap<>();
		userMap.put("Parameter1", currentUser.getCode());
		userMap.put("Parameter2", currentUser.getEmail());
		userMap.put("Parameter3", currentUser.getFullName());
		userMap.put("UserList", userDataSource);
		userMap.put("userDTODataSet", userDtoDataSource);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, userMap, userDataSource);
		if (reportFomat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\users.html");
		}
		if (reportFomat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\users.pdf");
		}
		if (reportFomat.equalsIgnoreCase("csv")) {
			FileOutputStream fileExcel = new FileOutputStream(path + "\\users.xls");
			try {
				generateExcel(fileExcel);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void generateExcel(FileOutputStream file) throws IOException {
		List<User> users = getAllUsers();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Boleto's User Info");
		HSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("Code");
		row.createCell(1).setCellValue("Email");
		row.createCell(2).setCellValue("Full Name");
		row.createCell(3).setCellValue("Gender");
		row.createCell(4).setCellValue("Address");
		row.createCell(5).setCellValue("Phone number");
		row.createCell(6).setCellValue("Total spent");

		int dataRowIndex = 1;
		for (User user : users) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(user.getCode());
			dataRow.createCell(1).setCellValue(user.getEmail());
			dataRow.createCell(2).setCellValue(user.getFullName());
			dataRow.createCell(3).setCellValue(user.getGender());
			dataRow.createCell(4).setCellValue(user.getAddress());
			dataRow.createCell(5).setCellValue(user.getPhoneNumber());
			dataRow.createCell(6).setCellValue(user.getTotalSpent().toString());
			dataRowIndex++;
		}

		workbook.write(file);
		workbook.close();

	}

}
