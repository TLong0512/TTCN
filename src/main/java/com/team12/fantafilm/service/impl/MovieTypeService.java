package com.team12.fantafilm.service.impl;


import com.team12.fantafilm.model.Movie;
import com.team12.fantafilm.model.MovieType;
import com.team12.fantafilm.repository.MovieRepository;
import com.team12.fantafilm.repository.MovieTypeRepository;
import com.team12.fantafilm.service.IMovieTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MovieTypeService implements IMovieTypeService {
    @Autowired
    MovieTypeRepository movieTypeRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public List<MovieType> findAll() {
        return movieTypeRepository.findAll();
    }

    @Override
    public MovieType findById(Long id) {
        return movieTypeRepository.findById(id).get();
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
            movieTypeRepository.save(MovieType);
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
            movieTypeRepository.save(MovieType);
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
            movieTypeRepository.deleteById(id);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }

    @Override
    public List<MovieType> searchNameMovieType(String keycode) {
        return movieTypeRepository.findByNameContains(keycode);
    }

    @Override
    public List<MovieType> findMovieTyprbyMovieId(Long movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        List<MovieType> movieTypes = movie.getMovieTypes();
        return movieTypes;
    }

    @Override
    public MovieType findByNameLike(String name) {
        return movieTypeRepository.findByNameLike(name);
    }

}
