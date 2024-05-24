package com.team12.fantafilm.repository;

import com.team12.fantafilm.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language,Long> {
    List<Language> findByNameContains(@Param("name") String keyCode);
    Language findByNameLike(@Param("name") String name);
}
