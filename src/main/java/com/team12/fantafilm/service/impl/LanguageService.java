package com.team12.fantafilm.service.impl;


import com.team12.fantafilm.model.Language;
import com.team12.fantafilm.model.Movie;
import com.team12.fantafilm.repository.LanguageRepository;
import com.team12.fantafilm.repository.MovieRepository;
import com.team12.fantafilm.service.ILanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class LanguageService implements ILanguageService {
    @Autowired
    LanguageRepository languageRepository;
    @Autowired
    MovieRepository movieRepository;
    @Override
    public List<Language> findAll() {
        return languageRepository.findAll();
    }

    @Override
    public Language findById(Long id) {
        return languageRepository.findById(id).get();
    }

    @Override
    public Boolean addLanguage(Language director) {
        try {
            if(director.getCode()==null)
            {
                Random generate = new Random();
                int val = generate.nextInt((10000-1)+1)+1;
                director.setCode("LAN"+val);
            }
            languageRepository.save(director);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }

    @Override
    public Boolean update(Language director) {

        try {
            languageRepository.save(director);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            languageRepository.deleteById(id);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }

    @Override
    public List<Language> searchNameLanguage(String keycode) {
        return languageRepository.findByNameContains(keycode);
    }

    @Override
    public List<Language> findNameByMovieId(Long id) {
        Movie movie = movieRepository.findById(id).get();
        List<Language> listLanguage = movie.getLanguages();
        return listLanguage;
    }

    @Override
    public Language findByNameLike(String name) {
        return languageRepository.findByNameLike(name);
    }

}
