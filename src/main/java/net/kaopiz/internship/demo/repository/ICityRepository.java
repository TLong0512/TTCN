package net.kaopiz.internship.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.kaopiz.internship.demo.entity.City;

public interface ICityRepository extends JpaRepository<City, Long> {

	Page<City> findAll(Pageable pageable);

	Page<City> findByActiveFlagAndCityNameContainingOrderByCreatedAtDesc(boolean status, String keyword,
			Pageable pageable);

	Page<City> findByActiveFlagOrderByCreatedAtDesc(boolean status, Pageable pageable);

	Optional<City> findByCityName(String cityName);

	Optional<City> findById(City updatedCity);

	Page<City> findByCityNameContaining(String keyword, Pageable pageable);

}
