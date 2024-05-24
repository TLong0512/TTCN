package com.team12.fantafilm.repository;

import com.team12.fantafilm.model.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PerformerRepository extends JpaRepository<Performer,Long> {
    List<Performer> findByNameContains(@Param("name") String keyCode);
    Performer findByNameLike(@Param("name") String name);
}
