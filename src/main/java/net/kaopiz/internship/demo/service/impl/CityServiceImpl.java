package net.kaopiz.internship.demo.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.City;
import net.kaopiz.internship.demo.repository.ICityRepository;
import net.kaopiz.internship.demo.service.ICityService;

@AllArgsConstructor
@Service
public class CityServiceImpl implements ICityService {

	private final ICityRepository cityRepository;

	@Override
	public Page<City> getAllCities(Pageable pageable) {
		return cityRepository.findAll(pageable);
	}

	@Override
	public List<City> getAllCities() {
		return cityRepository.findAll();
	}

	@Override
	public void addCity(City newCity) {
		cityRepository.save(newCity);
	}

	@Override
	public void updateCity(City updatedCity) {
		City existingCity = cityRepository.findById(updatedCity.getId()).orElse(null);
		if (existingCity != null) {
			updatedCity.setCreatedAt(existingCity.getCreatedAt());
			updatedCity.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			cityRepository.save(updatedCity);
		}
	}

	@Override
	public void deleteCity(Long cityId) {
		cityRepository.deleteById(cityId);
	}

	@Override
	public City getCityById(Long cityId) {
		return cityRepository.findById(cityId).orElse(null);
	}

	@Override
	public void setCityActiveFlag(Long cityId, boolean activeFlag) {
		City city = getCityById(cityId);
		if (city != null) {
			city.setActiveFlag(activeFlag);
			cityRepository.save(city);
		}
	}

	@Override
	public Page<City> searchCities(UserSearchDTO userSearchDTO, Pageable pageable) {

		String keyword = userSearchDTO.getKeyword();
		String status = userSearchDTO.getStatus();

		if (keyword != null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return cityRepository.findByActiveFlagAndCityNameContainingOrderByCreatedAtDesc(true, keyword,
						pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return cityRepository.findByActiveFlagAndCityNameContainingOrderByCreatedAtDesc(false, keyword,
						pageable);
			} else {
				return cityRepository.findByCityNameContaining(keyword, pageable);
			}
		}

		else if (keyword == null && status != null) {
			if (status.toLowerCase().equals("active")) {
				return cityRepository.findByActiveFlagOrderByCreatedAtDesc(true, pageable);
			} else if (status.toLowerCase().equals("inactive")) {
				return cityRepository.findByActiveFlagOrderByCreatedAtDesc(false, pageable);
			} else {
				return cityRepository.findByCityNameContaining(keyword, pageable);
			}
		} else {
			return getAllCities(pageable);
		}
	}

	@Override
	public City getCityByName(String cityName) {
		return cityRepository.findByCityName(cityName).orElse(null);
	}

}
