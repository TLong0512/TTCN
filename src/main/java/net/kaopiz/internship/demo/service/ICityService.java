package net.kaopiz.internship.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.kaopiz.internship.demo.dto.UserSearchDTO;
import net.kaopiz.internship.demo.entity.City;

public interface ICityService {

	Page<City> getAllCities(Pageable pageable);

	List<City> getAllCities();

	City getCityById(Long cityId);

	City getCityByName(String cityName);

	void addCity(City newCity);

	void updateCity(City updatedCity);

	void deleteCity(Long cityId);

	void setCityActiveFlag(Long cityId, boolean activeFlag);

	Page<City> searchCities(UserSearchDTO userSearchDTO, Pageable pageable);

}
