package com.team12.fantafilm.repository;

import com.team12.fantafilm.model.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieTypeRepository extends JpaRepository<MovieType,Long> {
    List<MovieType> findByNameContains(@Param("name") String keyCode);

    MovieType findByNameLike(String name);
}
