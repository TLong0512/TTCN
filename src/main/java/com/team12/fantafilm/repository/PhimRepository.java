package com.team12.fantafilm.repository;

import com.team12.fantafilm.model.Phim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhimRepository extends JpaRepository<Phim, Long> {
}
