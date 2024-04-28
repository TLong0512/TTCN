package net.kaopiz.internship.demo.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.Cinema;
import net.kaopiz.internship.demo.repository.ICinemaRepository;
import net.kaopiz.internship.demo.service.ICinemaService;

@AllArgsConstructor
@Service
public class CinemaServiceImpl implements ICinemaService {

	private final ICinemaRepository CinemaRepository;

	@Override
	public Page<Cinema> getAllCinemas(Pageable pageable) {
		return CinemaRepository.findAll(pageable);
	}

	@Override
	public List<Cinema> getAllCinemas() {
		return CinemaRepository.findAll();
	}

	@Override
	public void addCinema(Cinema newCinema) {
		Cinema savedCinema = CinemaRepository.save(newCinema);
		savedCinema.setCode(generateCode(savedCinema));
		CinemaRepository.save(savedCinema);
	}

	@Override
	public void updateCinema(Cinema updatedCinema) {
		Cinema existingCinema = CinemaRepository.findById(updatedCinema.getId()).orElse(null);
		updatedCinema.setCreatedAt(existingCinema.getCreatedAt());
		existingCinema.setCity(updatedCinema.getCity());
		updatedCinema.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
		updatedCinema.setCode(generateCode(existingCinema));
		CinemaRepository.save(updatedCinema);
	}

	@Override
	public void deleteCinema(Long cinemaId) {
		CinemaRepository.deleteById(cinemaId);
	}

	@Override
	public Cinema getCinemaById(Long cinemaId) {
		return CinemaRepository.findById(cinemaId).orElse(null);
	}

	@Override
	public void setCinemaActiveFlag(Long CinemaId, boolean activeFlag) {
		Cinema Cinema = getCinemaById(CinemaId);
		if (Cinema != null) {
			Cinema.setActiveFlag(activeFlag);
		}
		CinemaRepository.save(Cinema);
	}

	@Override
	public Page<Cinema> searchCinemas(UserSearchDTO userSearchDTO, Pageable pageable) {

		String keyword = userSearchDTO.getKeyword();
		String status = userSearchDTO.getStatus();

		if (keyword != null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return CinemaRepository.findByActiveFlagAndCinemaNameContainingOrderByCreatedAtDesc(true, keyword,
						pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return CinemaRepository.findByActiveFlagAndCinemaNameContainingOrderByCreatedAtDesc(false, keyword,
						pageable);
			} else {
				return CinemaRepository.findByCinemaNameContaining(keyword, pageable);
			}
		}

		else if (keyword == null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return CinemaRepository.findByActiveFlagOrderByCreatedAtDesc(true, pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return CinemaRepository.findByActiveFlagOrderByCreatedAtDesc(false, pageable);
			} else {
				return CinemaRepository.findByCinemaNameContaining(keyword, pageable);
			}
		} else {
			return getAllCinemas(pageable);
		}
	}

	@Override
	public Cinema getCinemaByName(String cinemaName) {
		return CinemaRepository.findByCinemaName(cinemaName).orElse(null);
	}

	private String generateCode(Cinema cinema) {
		return cinema.getCity().getId() + "CINE" + cinema.getId();
	}

}
