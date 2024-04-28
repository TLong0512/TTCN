package net.kaopiz.internship.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.kaopiz.internship.demo.entity.Booking;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {
	
	

}
