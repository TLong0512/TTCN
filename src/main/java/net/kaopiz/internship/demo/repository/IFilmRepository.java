package net.kaopiz.internship.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.kaopiz.internship.demo.entity.Film;

public interface IFilmRepository extends JpaRepository<Film, Long> {

	Page<Film> findAll(Pageable pageable);

	Page<Film> findByActiveFlagAndNameContainingOrderByCreatedAtDesc(boolean status, String keyword, Pageable pageable);

	Page<Film> findByActiveFlagOrderByCreatedAtDesc(boolean status, Pageable pageable);

	Optional<Film> findByName(String cinema_RoomName);

	Optional<Film> findById(Film updatedCategory_Room);

	Page<Film> findByNameContaining(String keyword, Pageable pageable);

	Page<Film> findAllByCategoryId(Pageable pageable, Long cinemaId);

	Page<Film> findByCategoryIdAndNameContaining(Long cinemaId, String keyword, Pageable page);

	Page<Film> findByCategoryIdAndActiveFlagAndNameContainingOrderByCreatedAtDesc(Long cinemaId, boolean b,
			String keyword, Pageable page);

}
