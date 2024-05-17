package com.team12.fantafilm.repository;

import com.team12.fantafilm.model.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieTypeRepository extends JpaRepository<MovieType,Long> {
}
