package net.kaopiz.internship.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.kaopiz.internship.demo.entity.Order_Combo_Detail;

@Repository
public interface IOrderDetailComboRepository extends JpaRepository<Order_Combo_Detail, Long> {

}
