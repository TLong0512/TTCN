package net.kaopiz.internship.demo.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.Film;
import net.kaopiz.internship.demo.entity.User;
import net.sf.jasperreports.engine.JRException;

public interface IFilmService {

	Page<Film> getAllFilms(Pageable pageable);

	List<Film> getAllFilms();

	Film findFilmById(Long filmId);

	Film findFilmByName(String filmName);

	void addFilm(Film film, MultipartFile thumbnail, MultipartFile trailer);

	void updateFilm(Film film, MultipartFile thumbnail, MultipartFile trailer);

	void deleteFilm(Long filmId);

	void setFilmActiveFlag(Long filmId, boolean flag);

	Long countFilm();

	Page<Film> searchFilms(UserSearchDTO search, Pageable pageable);

	void exportReport(String reportFomat, User currentUser) throws FileNotFoundException, JRException;

}
