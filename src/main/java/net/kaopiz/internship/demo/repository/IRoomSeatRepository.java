package net.kaopiz.internship.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import net.kaopiz.internship.demo.entity.Room_Seat;

public interface IRoomSeatRepository extends JpaRepository<Room_Seat, Long> {

	Page<Room_Seat> findAll(Pageable pageable);

	Page<Room_Seat> findByActiveFlagAndCodeContainingOrderByCreatedAtDesc(boolean status, String keyword,
			Pageable pageable);

	Page<Room_Seat> findByActiveFlagOrderByCreatedAtDesc(boolean status, Pageable pageable);

	Optional<Room_Seat> findByPosition(String cinema_RoomName);

	Optional<Room_Seat> findById(Room_Seat updatedCinema_Room);

	Page<Room_Seat> findByCodeContaining(String keyword, Pageable pageable);

	Page<Room_Seat> findAllByCinemaRoomId(Pageable pageable, Long cinemaId);

	Page<Room_Seat> findByCinemaRoomIdAndCodeContaining(Long cinemaId, String keyword, Pageable page);

	Page<Room_Seat> findByCinemaRoomIdAndActiveFlagAndCodeContainingOrderByCreatedAtDesc(Long cinemaId, boolean b,
			String keyword, Pageable page);

	Room_Seat findByCode(String code);

}
