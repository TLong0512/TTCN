package net.kaopiz.internship.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.kaopiz.internship.demo.entity.Show;

public interface IShowRepository extends JpaRepository<Show, Long> {

	Page<Show> findAll(Pageable pageable);

	Page<Show> findByActiveFlagAndCodeContainingOrderByCreatedAtDesc(boolean status, String keyword, Pageable pageable);

	Page<Show> findByActiveFlagOrderByCreatedAtDesc(boolean status, Pageable pageable);

	Optional<Show> findByCode(String code);

	List<Show> findByCinemaRoomId(Long cinemaRoomId);

	List<Show> findByFilmId(Long filmId);

	Page<Show> findByCodeContaining(String keyword, Pageable pageable);

	Page<Show> findAllByCinemaRoomId(Pageable pageable, Long cinemaId);

	Page<Show> findByCinemaRoomIdAndCodeContaining(Long cinemaId, String keyword, Pageable page);

	Page<Show> findByCinemaRoomIdAndActiveFlagAndCodeContainingOrderByCreatedAtDesc(Long cinemaId, boolean b,
			String keyword, Pageable page);

}
