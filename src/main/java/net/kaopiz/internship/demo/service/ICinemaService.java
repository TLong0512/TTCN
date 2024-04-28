package net.kaopiz.internship.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.Cinema;

public interface ICinemaService {

	Page<Cinema> getAllCinemas(Pageable pageable);

	List<Cinema> getAllCinemas();

	Cinema getCinemaById(Long CinemaId);

	Cinema getCinemaByName(String CinemaName);

	void addCinema(Cinema newCinema);

	void updateCinema(Cinema updatedCinema);

	void deleteCinema(Long CinemaId);

	void setCinemaActiveFlag(Long CinemaId, boolean activeFlag);

	Page<Cinema> searchCinemas(UserSearchDTO userSearchDTO, Pageable pageable);

}
