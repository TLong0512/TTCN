package net.kaopiz.internship.demo.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.CinemaRoom;
import net.kaopiz.internship.demo.entity.Room_Seat;
import net.kaopiz.internship.demo.repository.ICinemaRoomRepository;
import net.kaopiz.internship.demo.repository.IRoomSeatRepository;
import net.kaopiz.internship.demo.service.IRoomSeatService;

@AllArgsConstructor
@Service
public class RoomSeatServiceImpl implements IRoomSeatService {

	private IRoomSeatRepository roomSeatRepository;
	private ICinemaRoomRepository cinemaRoomRepository;

	@Override
	public Page<Room_Seat> getAllRoomSeats(Pageable pageable) {
		// TODO Auto-generated method stub
		return roomSeatRepository.findAll(pageable);
	}

	@Override
	public List<Room_Seat> getAllRoomSeats() {
		// TODO Auto-generated method stub
		return roomSeatRepository.findAll();
	}

	@Override
	public Room_Seat getRoomSeatById(Long roomSeatId) {
		// TODO Auto-generated method stub
		return roomSeatRepository.findById(roomSeatId).orElse(null);
	}

	@Override
	public Room_Seat getRoomSeatByPosition(String roomSeatPosition) {
		// TODO Auto-generated method stub
		return roomSeatRepository.findByPosition(roomSeatPosition).orElse(null);
	}

	@Override
	public void addRoomSeat(Room_Seat newRoomSeat) {
		// TODO Auto-generated method stub
		newRoomSeat.setPosition(newRoomSeat.getRow().toString() + newRoomSeat.getColumn().toString());
		newRoomSeat.setCode(
				newRoomSeat.getCinemaRoom().getId().toString() + newRoomSeat.getType() + newRoomSeat.getPosition());
		roomSeatRepository.save(newRoomSeat);

	}

	@Override
	public void updateRoomSeat(Room_Seat updatedRoomSeat) {
		// TODO Auto-generated method stub
		Room_Seat existedRoomSeat = getRoomSeatById(updatedRoomSeat.getId());
		CinemaRoom savedCinemaRoom = existedRoomSeat.getCinemaRoom();
		if (existedRoomSeat != null && !savedCinemaRoom.getRoomSeats().contains(existedRoomSeat)) {
			updatedRoomSeat.setPosition(updatedRoomSeat.getRow().toString() + updatedRoomSeat.getColumn().toString());
			updatedRoomSeat.setCode(
					updatedRoomSeat.getCinemaRoom().getId().toString() + "SEAT" + updatedRoomSeat.getPosition());
			updatedRoomSeat.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			roomSeatRepository.save(updatedRoomSeat);
		}
	}

	@Override
	public void deleteRoomSeat(Long roomSeatId) {
		// TODO Auto-generated method stub
		roomSeatRepository.deleteById(roomSeatId);
	}

	@Override
	public void setRoomSeatActiveFlag(Long roomSeatId, boolean activeFlag) {
		// TODO Auto-generated method stub
		Room_Seat existedRoom_Seat = getRoomSeatById(roomSeatId);
		if (existedRoom_Seat != null) {
			existedRoom_Seat.setActiveFlag(activeFlag);
			roomSeatRepository.save(existedRoom_Seat);
		}
	}

	@Override
	public Page<Room_Seat> searchRoomSeats(UserSearchDTO userSearchDTO, Pageable pageable) {
		String keyword = userSearchDTO.getKeyword();
		String status = userSearchDTO.getStatus();

		if (keyword != null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return roomSeatRepository.findByActiveFlagAndCodeContainingOrderByCreatedAtDesc(true, keyword,
						pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return roomSeatRepository.findByActiveFlagAndCodeContainingOrderByCreatedAtDesc(false, keyword,
						pageable);
			} else {
				return roomSeatRepository.findByCodeContaining(keyword, pageable);
			}
		}

		else if (keyword == null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return roomSeatRepository.findByActiveFlagOrderByCreatedAtDesc(true, pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return roomSeatRepository.findByActiveFlagOrderByCreatedAtDesc(false, pageable);
			} else {
				return roomSeatRepository.findByCodeContaining(keyword, pageable);
			}
		} else {
			return getAllRoomSeats(pageable);
		}
	}

	@Override
	public Page<Room_Seat> getAllRoomSeatsByCinemaRoomId(Long cinemaRoomId, UserSearchDTO userSearchDTO,
			Pageable page) {
		String keyword = userSearchDTO.getKeyword();
		String status = userSearchDTO.getStatus();

		if (keyword != null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return roomSeatRepository.findByCinemaRoomIdAndActiveFlagAndCodeContainingOrderByCreatedAtDesc(
						cinemaRoomId, true, keyword, page);
			} else if (status.toLowerCase().equals("inactive")) {
				return roomSeatRepository.findByCinemaRoomIdAndActiveFlagAndCodeContainingOrderByCreatedAtDesc(
						cinemaRoomId, false, keyword, page);
			} else {
				return roomSeatRepository.findByCinemaRoomIdAndCodeContaining(cinemaRoomId, keyword, page);
			}
		}

		else if (keyword == null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return roomSeatRepository.findByActiveFlagOrderByCreatedAtDesc(true, page);
			} else if (status.toLowerCase().equals("inactive")) {
				return roomSeatRepository.findByActiveFlagOrderByCreatedAtDesc(false, page);
			} else {
				return roomSeatRepository.findByCodeContaining(keyword, page);
			}
		} else {
			return roomSeatRepository.findAllByCinemaRoomId(page, cinemaRoomId);
		}
	}

	@Override
	public Room_Seat getRoomSeatByCode(String code) {
		// TODO Auto-generated method stub
		return roomSeatRepository.findByCode(code);
	}

}
