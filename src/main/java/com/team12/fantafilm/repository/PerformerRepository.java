package com.team12.fantafilm.repository;

import com.team12.fantafilm.model.Performer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformerRepository extends JpaRepository<Performer,Long> {
}
