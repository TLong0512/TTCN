package net.kaopiz.internship.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.kaopiz.internship.demo.entity.Show_Seat;

@Repository
public interface IShowSeatRepository extends JpaRepository<Show_Seat, Long> {

}
