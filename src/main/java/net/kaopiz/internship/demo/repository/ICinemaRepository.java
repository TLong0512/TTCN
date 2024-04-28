package net.kaopiz.internship.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.kaopiz.internship.demo.entity.Cinema;

@Repository
public interface ICinemaRepository extends JpaRepository<Cinema, Long> {

	Page<Cinema> findAll(Pageable pageable);

	Page<Cinema> findByActiveFlagAndCinemaNameContainingOrderByCreatedAtDesc(boolean status, String keyword,
			Pageable pageable);

	Page<Cinema> findByActiveFlagOrderByCreatedAtDesc(boolean status, Pageable pageable);

	Optional<Cinema> findByCinemaName(String CinemaName);

	Optional<Cinema> findById(Cinema updatedCinema);

	Page<Cinema> findByCinemaNameContaining(String keyword, Pageable pageable);

}
