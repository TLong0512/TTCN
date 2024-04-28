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
import net.kaopiz.internship.demo.entity.Show;
import net.kaopiz.internship.demo.repository.ICinemaRoomRepository;
import net.kaopiz.internship.demo.repository.IFilmRepository;
import net.kaopiz.internship.demo.repository.IShowRepository;
import net.kaopiz.internship.demo.service.IShowService;

@AllArgsConstructor
@Service
public class ShowServiceImpl implements IShowService {

	private IShowRepository showRepository;
	private ICinemaRoomRepository cinemaRoomRepository;
	private IFilmRepository filmRepository;

	@Override
	public Page<Show> getAllShows(Pageable pageable) {
		// TODO Auto-generated method stub
		return showRepository.findAll(pageable);
	}

	@Override
	public List<Show> getAllShows() {
		// TODO Auto-generated method stub
		return showRepository.findAll();
	}

	@Override
	public Show getShowById(Long showId) {
		// TODO Auto-generated method stub
		return showRepository.findById(showId).orElse(null);
	}

	@Override
	public Show getShowByCode(String showCode) {
		// TODO Auto-generated method stub
		return showRepository.findByCode(showCode).orElse(null);
	}

	@Override
	public void addShow(Show newShow) {
		// TODO Auto-generated method stub
		newShow = showRepository.save(newShow);
		newShow.setCode('R' + newShow.getCinemaRoom().getId().toString() + 'F' + newShow.getFilm().getId() + 'S'
				+ newShow.getId());
		newShow.setIsFull(false);
		showRepository.save(newShow);
	}

	@Override
	public void updateShow(Show updatedShow) {
		// TODO Auto-generated method stub
		Show existedShow = getShowById(updatedShow.getId());
		CinemaRoom savedCinemaRoom = existedShow.getCinemaRoom();
		if (existedShow != null && !savedCinemaRoom.getShows().contains(existedShow)) {
			updatedShow.setCode(updatedShow.getCinemaRoom().getId().toString() + 'R' + updatedShow.getFilm().getId()
					+ 'F' + updatedShow.getId());
			updatedShow.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			showRepository.save(updatedShow);
		}
	}

	@Override
	public void deleteShow(Long showId) {
		// TODO Auto-generated method stub
		showRepository.deleteById(showId);
	}

	@Override
	public void setShowActiveFlag(Long showId, boolean activeFlag) {
		// TODO Auto-generated method stub
		Show existedShow = getShowById(showId);
		if (existedShow != null) {
			existedShow.setActiveFlag(activeFlag);
			showRepository.save(existedShow);
		}
	}

	@Override
	public Page<Show> searchShows(UserSearchDTO userSearchDTO, Pageable pageable) {
		String keyword = userSearchDTO.getKeyword();
		String status = userSearchDTO.getStatus();

		if (keyword != null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return showRepository.findByActiveFlagAndCodeContainingOrderByCreatedAtDesc(true, keyword, pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return showRepository.findByActiveFlagAndCodeContainingOrderByCreatedAtDesc(false, keyword, pageable);
			} else {
				return showRepository.findByCodeContaining(keyword, pageable);
			}
		}

		else if (keyword == null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return showRepository.findByActiveFlagOrderByCreatedAtDesc(true, pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return showRepository.findByActiveFlagOrderByCreatedAtDesc(false, pageable);
			} else {
				return showRepository.findByCodeContaining(keyword, pageable);
			}
		} else {
			return getAllShows(pageable);
		}
	}

	@Override
	public Page<Show> getAllShowsByCinemaRoomId(Long cinemaRoomId, UserSearchDTO userSearchDTO, Pageable page) {
		String keyword = userSearchDTO.getKeyword();
		String status = userSearchDTO.getStatus();

		if (keyword != null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return showRepository.findByCinemaRoomIdAndActiveFlagAndCodeContainingOrderByCreatedAtDesc(cinemaRoomId,
						true, keyword, page);
			} else if (status.toLowerCase().equals("inactive")) {
				return showRepository.findByCinemaRoomIdAndActiveFlagAndCodeContainingOrderByCreatedAtDesc(cinemaRoomId,
						false, keyword, page);
			} else {
				return showRepository.findByCinemaRoomIdAndCodeContaining(cinemaRoomId, keyword, page);
			}
		}

		else if (keyword == null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return showRepository.findByActiveFlagOrderByCreatedAtDesc(true, page);
			} else if (status.toLowerCase().equals("inactive")) {
				return showRepository.findByActiveFlagOrderByCreatedAtDesc(false, page);
			} else {
				return showRepository.findByCodeContaining(keyword, page);
			}
		} else {
			return showRepository.findAllByCinemaRoomId(page, cinemaRoomId);
		}
	}

}
