package net.kaopiz.internship.demo.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.entity.Cinema;
import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.CinemaRoom;
import net.kaopiz.internship.demo.repository.ICinemaRoomRepository;
import net.kaopiz.internship.demo.service.ICinemaRoomService;
import net.kaopiz.internship.demo.service.ICinemaService;

@AllArgsConstructor
@Service
public class CinemaRoomServiceImpl implements ICinemaRoomService {

	private final ICinemaRoomRepository cinemaRoomRepository;
	private final ICinemaService cinemaService;

	@Override
	public Page<CinemaRoom> getAllCinemaRooms(Pageable pageable) {
		return cinemaRoomRepository.findAll(pageable);
	}

	@Override
	public List<CinemaRoom> getAllCinemaRooms() {
		return cinemaRoomRepository.findAll();
	}

	@Override
	public CinemaRoom getCinemaRoomById(Long cinemaRoomId) {
		return cinemaRoomRepository.findById(cinemaRoomId).orElse(null);
	}

	@Override
	public CinemaRoom getCinemaRoomByName(String cinemaRoomName) {
		return cinemaRoomRepository.findByName(cinemaRoomName).orElse(null);
	}

	@Override
	public void addCinemaRoom(CinemaRoom newCinemaRoom) {
		CinemaRoom savedCinemaRoom = cinemaRoomRepository.save(newCinemaRoom);
		Cinema savedCinema = newCinemaRoom.getCinema();
		newCinemaRoom.setCode(generateCode(savedCinemaRoom));
		savedCinemaRoom = cinemaRoomRepository.save(newCinemaRoom);
		cinemaRoomRepository.save(savedCinemaRoom);
	}

	@Override
	public void updateCinemaRoom(CinemaRoom updatedCinema) {
		// TODO Auto-generated method stub
		CinemaRoom existedCinemaRoom = cinemaRoomRepository.findById(updatedCinema.getId()).orElse(null);
		Cinema savedCinema = updatedCinema.getCinema();
		if (existedCinemaRoom != null && !savedCinema.getCinemaRooms().contains(existedCinemaRoom)) {
			updatedCinema.setCreatedAt(existedCinemaRoom.getCreatedAt());
			updatedCinema.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			cinemaRoomRepository.save(updatedCinema);
		}
	}

	@Override
	public void deleteCinemaRoom(Long cinemaRoomId) {
		// TODO Auto-generated method stub
		cinemaRoomRepository.deleteById(cinemaRoomId);
	}

	@Override
	public void setCinemaRoomActiveFlag(Long cinemaRoomId, boolean activeFlag) {
		// TODO Auto-generated method stub
		CinemaRoom existedCinemaRoom = getCinemaRoomById(cinemaRoomId);
		if (existedCinemaRoom != null) {
			existedCinemaRoom.setActiveFlag(activeFlag);
			cinemaRoomRepository.save(existedCinemaRoom);
		}
	}

	@Override
	public Page<CinemaRoom> searchCinemaRooms(UserSearchDTO userSearchDTO, Pageable pageable) {
		// TODO Auto-generated method stub
		String keyword = userSearchDTO.getKeyword();
		String status = userSearchDTO.getStatus();

		if (keyword != null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return cinemaRoomRepository.findByActiveFlagAndNameContainingOrderByCreatedAtDesc(true, keyword,
						pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return cinemaRoomRepository.findByActiveFlagAndNameContainingOrderByCreatedAtDesc(false, keyword,
						pageable);
			} else {
				return cinemaRoomRepository.findByNameContaining(keyword, pageable);
			}
		}

		else if (keyword == null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return cinemaRoomRepository.findByActiveFlagOrderByCreatedAtDesc(true, pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return cinemaRoomRepository.findByActiveFlagOrderByCreatedAtDesc(false, pageable);
			} else {
				return cinemaRoomRepository.findByNameContaining(keyword, pageable);
			}
		} else {
			return getAllCinemaRooms(pageable);
		}
	}

	@Override
	public Page<CinemaRoom> getAllCinemaRoomsByCinemaId(Long cinemaId, UserSearchDTO userSearchDTO, Pageable page) {
		String keyword = userSearchDTO.getKeyword();
		String status = userSearchDTO.getStatus();

		if (keyword != null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return cinemaRoomRepository.findByCinemaIdAndActiveFlagAndNameContainingOrderByCreatedAtDesc(cinemaId,
						true, keyword, page);
			} else if (status.toLowerCase().equals("inactive")) {
				return cinemaRoomRepository.findByCinemaIdAndActiveFlagAndNameContainingOrderByCreatedAtDesc(cinemaId,
						false, keyword, page);
			} else {
				return cinemaRoomRepository.findByCinemaIdAndNameContaining(cinemaId, keyword, page);
			}
		}

		else if (keyword == null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return cinemaRoomRepository.findByActiveFlagOrderByCreatedAtDesc(true, page);
			} else if (status.toLowerCase().equals("inactive")) {
				return cinemaRoomRepository.findByActiveFlagOrderByCreatedAtDesc(false, page);
			} else {
				return cinemaRoomRepository.findByNameContaining(keyword, page);
			}
		} else {
			return cinemaRoomRepository.findAllByCinemaId(page, cinemaId);
		}
	}

	private String generateCode(CinemaRoom cinemaRoom) {
		String[] value = cinemaRoom.getName().split(" ");
		return cinemaRoom.getCinema().getId() + "ROOM" + value[1];
	}

}
