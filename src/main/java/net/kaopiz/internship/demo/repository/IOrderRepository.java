package net.kaopiz.internship.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.kaopiz.internship.demo.entity.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

}
