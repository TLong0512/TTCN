package com.team12.fantafilm.service.impl;


import com.team12.fantafilm.model.MovieType;
import com.team12.fantafilm.repository.MovieTypeRepository;
import com.team12.fantafilm.service.IMovieTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MovieTypeService implements IMovieTypeService {
    @Autowired
    MovieTypeRepository MovieTypeRepository;
    @Override
    public List<MovieType> findAll() {
        return MovieTypeRepository.findAll();
    }

    @Override
    public MovieType findById(Long id) {
        return MovieTypeRepository.findById(id).get();
    }

    @Override
    public Boolean addMovieType(MovieType MovieType) {
        try {
            if(MovieType.getCode()==null)
            {
                Random generate = new Random();
                int val = generate.nextInt((10000-1)+1)+1;
                MovieType.setCode("TYPE"+val);
            }
            MovieTypeRepository.save(MovieType);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }

    @Override
    public Boolean update(MovieType MovieType) {
        try {
            MovieTypeRepository.save(MovieType);
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
            MovieTypeRepository.deleteById(id);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }
}
