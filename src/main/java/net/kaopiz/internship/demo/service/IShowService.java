package net.kaopiz.internship.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.Show;

public interface IShowService {

	Page<Show> getAllShows(Pageable pageable);

	List<Show> getAllShows();

	Show getShowById(Long showId);

	void addShow(Show newShow);

	void updateShow(Show updatedShow);

	void deleteShow(Long showId);

	void setShowActiveFlag(Long showId, boolean activeFlag);

	Page<Show> searchShows(UserSearchDTO userSearchDTO, Pageable pageable);

	Page<Show> getAllShowsByCinemaRoomId(Long cinemaRoomId, UserSearchDTO userSearchDTO, Pageable page);

	Show getShowByCode(String code);

}
