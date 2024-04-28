package net.kaopiz.internship.demo.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.Film;
import net.kaopiz.internship.demo.entity.User;
import net.kaopiz.internship.demo.repository.IFilmRepository;
import net.kaopiz.internship.demo.service.IFilmService;
import net.sf.jasperreports.engine.JRException;

@AllArgsConstructor
@Service
public class FilmServiceImpl implements IFilmService {

	private IFilmRepository filmRepository;

	@Override
	public Page<Film> getAllFilms(Pageable pageable) {
		return filmRepository.findAll(pageable);
	}

	@Override
	public List<Film> getAllFilms() {
		return filmRepository.findAll();
	}

	@Override
	public Film findFilmById(Long filmId) {
		return filmRepository.findById(filmId).orElse(null);
	}

	@Override
	public Film findFilmByName(String filmName) {
		return filmRepository.findByName(filmName).orElse(null);
	}

	@Override
	public void addFilm(Film film, MultipartFile thumbnail, MultipartFile trailer) {
		// TODO Auto-generated method stub
		Film savedFilm = filmRepository.save(film);
//		film.setCode(generateFilmCode(film));
		film.setViews((long) 0);
		savedFilm = filmRepository.save(film);
		if (thumbnail != null && trailer != null) {
			try {
				saveThumbnail(savedFilm, thumbnail);
				saveTrailer(film, trailer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void updateFilm(Film film, MultipartFile thumbnail, MultipartFile trailer) {
		Film existingFilm = filmRepository.findById(film.getId()).orElse(null);
		film.setCreatedAt(existingFilm.getCreatedAt());
		film.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
		film.setCode(generateFilmCode(existingFilm));
		Film savedFilm = filmRepository.save(film);
		if (thumbnail != null && trailer != null) {
			try {
				saveThumbnail(savedFilm, thumbnail);
				saveTrailer(film, trailer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private String generateFilmCode(Film existingFilm) {
		String[] yearAndMonth = existingFilm.getStartedDate().toString().split("-");
		return yearAndMonth[0].substring(2, 4) + yearAndMonth[1] + existingFilm.getCategory().getId()
				+ String.valueOf('F') + existingFilm.getId().toString();
	}

	@Override
	public void deleteFilm(Long filmId) {
		// TODO Auto-generated method stub
		filmRepository.deleteById(filmId);
	}

	@Override
	public void setFilmActiveFlag(Long filmId, boolean flag) {

		Film film = findFilmById(filmId);
		if (film != null) {
			film.setActiveFlag(flag);
		}
		filmRepository.save(film);

	}

	@Override
	public Long countFilm() {
		// TODO Auto-generated method stub
		return filmRepository.count();
	}

	@Override
	public Page<Film> searchFilms(UserSearchDTO search, Pageable pageable) {
		String status = search.getStatus();
		String keyword = search.getKeyword();

		if (keyword != null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return filmRepository.findByActiveFlagAndNameContainingOrderByCreatedAtDesc(true, keyword, pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return filmRepository.findByActiveFlagAndNameContainingOrderByCreatedAtDesc(false, keyword, pageable);
			} else {
				return filmRepository.findByNameContaining(keyword, pageable);
			}
		}

		else if (keyword == null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return filmRepository.findByActiveFlagOrderByCreatedAtDesc(true, pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return filmRepository.findByActiveFlagOrderByCreatedAtDesc(false, pageable);
			} else {
				return filmRepository.findByNameContaining(keyword, pageable);
			}
		} else {
			return getAllFilms(pageable);
		}
	}

	// Hàm lưu ảnh thumbnail vào static/thumbnail và lưu tên ảnh vào database
	private void saveThumbnail(Film film, MultipartFile thumbnail) throws IOException {

		if (thumbnail == null || thumbnail.isEmpty()) {
			return;
		}

		String originalFileName = thumbnail.getOriginalFilename();
		String uniqueFileName = generateUniqueFileName(originalFileName);

		Path thumbnailImagePath = Paths.get("src/main/resources/static/thumbnail/" + uniqueFileName);

		if (!Files.exists(thumbnailImagePath)) {
			Files.createDirectories(thumbnailImagePath);
		}

		try {
			Files.copy(thumbnail.getInputStream(), thumbnailImagePath, StandardCopyOption.REPLACE_EXISTING);
			film.setThumbnail(uniqueFileName);
			filmRepository.save(film);
		} catch (IOException e) {
			throw new RuntimeException("Failed to save thumbnail for film: " + film.getId(), e);
		}

	}

	// Hàm lưu ảnh thumbnail vào static/thumbnail và lưu tên ảnh vào database
	private void saveTrailer(Film film, MultipartFile trailer) throws IOException {

		if (trailer == null || trailer.isEmpty()) {
			return;
		}

		String originalFileName = trailer.getOriginalFilename();
		String uniqueFileName = generateUniqueFileName(originalFileName);

		Path trailerImagePath = Paths.get("src/main/resources/static/trailer/" + uniqueFileName);

		if (!Files.exists(trailerImagePath)) {
			Files.createDirectories(trailerImagePath);
		}

		try {
			Files.copy(trailer.getInputStream(), trailerImagePath, StandardCopyOption.REPLACE_EXISTING);
			film.setTrailer(uniqueFileName);
			filmRepository.save(film);
		} catch (IOException e) {
			throw new RuntimeException("Failed to save trailer for film: " + film.getId(), e);
		}

	}

	// Tạo ra một tên file độc nhất cho ảnh người dùng để tránh bị trùng tên file
	private String generateUniqueFileName(String originalFileName) {
		String timestamp = String.valueOf(System.currentTimeMillis());
		return timestamp + "_" + originalFileName;
	}

	@Override
	public void exportReport(String reportFomat, User currentUser) throws FileNotFoundException, JRException {
		// TODO Auto-generated method stub

	}

}
