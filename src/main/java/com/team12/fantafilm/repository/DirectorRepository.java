package com.team12.fantafilm.repository;

import com.team12.fantafilm.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DirectorRepository extends JpaRepository<Director,Long> {
    List<Director> findByNameContains(@Param("name") String keyCode);
    Director findByNameLike(@Param("name") String name);
}
