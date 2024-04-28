package net.kaopiz.internship.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.Room_Seat;

public interface IRoomSeatService {
	Page<Room_Seat> getAllRoomSeats(Pageable pageable);

	List<Room_Seat> getAllRoomSeats();

	Room_Seat getRoomSeatById(Long roomSeatId);

	Room_Seat getRoomSeatByPosition(String roomSeatName);

	void addRoomSeat(Room_Seat newRoomSeat);

	void updateRoomSeat(Room_Seat updatedRoomSeat);

	void deleteRoomSeat(Long roomSeatId);

	void setRoomSeatActiveFlag(Long roomSeatId, boolean activeFlag);

	Page<Room_Seat> searchRoomSeats(UserSearchDTO userSearchDTO, Pageable pageable);

	Page<Room_Seat> getAllRoomSeatsByCinemaRoomId(Long cinemaRoomId, UserSearchDTO userSearchDTO, Pageable page);

	Room_Seat getRoomSeatByCode(String code);
}
