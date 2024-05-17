package com.team12.fantafilm.service;

import com.team12.fantafilm.model.Director;
import com.team12.fantafilm.model.Language;

import java.util.List;

public interface ILanguageService {
    List<Language> findAll();
    Language findById(Long id);
    Boolean addLanguage(Language language);
    Boolean update(Language language);
    Boolean delete(Long id);
}
