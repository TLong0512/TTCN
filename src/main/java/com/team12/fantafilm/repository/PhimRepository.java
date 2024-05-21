package com.team12.fantafilm.repository;

import com.team12.fantafilm.model.Phim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface   PhimRepository extends JpaRepository<Phim, Long> {

    List<Phim> findByName(String name);
}
