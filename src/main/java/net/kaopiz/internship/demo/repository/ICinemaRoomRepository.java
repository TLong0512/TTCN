package net.kaopiz.internship.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import net.kaopiz.internship.demo.entity.CinemaRoom;

public interface ICinemaRoomRepository extends JpaRepository<CinemaRoom, Long> {

	Page<CinemaRoom> findAll(Pageable pageable);

	Page<CinemaRoom> findByActiveFlagAndNameContainingOrderByCreatedAtDesc(boolean status, String keyword,
			Pageable pageable);

	Page<CinemaRoom> findByActiveFlagOrderByCreatedAtDesc(boolean status, Pageable pageable);

	Optional<CinemaRoom> findByName(String cinema_RoomName);

	Optional<CinemaRoom> findById(CinemaRoom updatedCinema_Room);

	Page<CinemaRoom> findByNameContaining(String keyword, Pageable pageable);

	Page<CinemaRoom> findAllByCinemaId(Pageable pageable, Long cinemaId);

	Page<CinemaRoom> findByCinemaIdAndNameContaining(Long cinemaId, String keyword, Pageable page);

	Page<CinemaRoom> findByCinemaIdAndActiveFlagAndNameContainingOrderByCreatedAtDesc(Long cinemaId, boolean b,
			String keyword, Pageable page);

}
