package net.kaopiz.internship.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.CinemaRoom;

public interface ICinemaRoomService {

	Page<CinemaRoom> getAllCinemaRooms(Pageable pageable);

	List<CinemaRoom> getAllCinemaRooms();

	CinemaRoom getCinemaRoomById(Long cinemaRoomId);

	CinemaRoom getCinemaRoomByName(String cinemaRoomName);

	void addCinemaRoom(CinemaRoom newCinemaRoom);

	void updateCinemaRoom(CinemaRoom updatedCinema);

	void deleteCinemaRoom(Long cinemaRoomId);

	void setCinemaRoomActiveFlag(Long cinemaRoomId, boolean activeFlag);

	Page<CinemaRoom> searchCinemaRooms(UserSearchDTO userSearchDTO, Pageable pageable);

	Page<CinemaRoom> getAllCinemaRoomsByCinemaId(Long cinemaId, UserSearchDTO userSearchDTO, Pageable page);

}
