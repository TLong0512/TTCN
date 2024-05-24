package com.team12.fantafilm.service.impl;


import com.team12.fantafilm.model.Director;
import com.team12.fantafilm.model.Movie;
import com.team12.fantafilm.repository.DirectorRepository;
import com.team12.fantafilm.repository.MovieRepository;
import com.team12.fantafilm.service.IDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DirectorService implements IDirectorService {
    @Autowired
    DirectorRepository directorRepository;


    @Autowired
    private MovieRepository movieRepository;
    @Override
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    public Director findById(Long id) {
        return directorRepository.findById(id).get();
    }

    @Override
    public Boolean addDirector(Director director) {
        try {
               if(director.getCode()==null)
               {
                   Random generate = new Random();
                   int val = generate.nextInt((10000-1)+1)+1;
                   director.setCode("DIR"+val);
               }
            directorRepository.save(director);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }

    @Override
    public Boolean update(Director director) {

        try {
            directorRepository.save(director);
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
            directorRepository.deleteById(id);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }

    @Override
    public List<Director> searchNameDirector(String keycode) {
        return directorRepository.findByNameContains(keycode);
    }

    @Override
    public List<Director> findDireactorByMovieId(Long movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        List<Director> directors = movie.getDirectors();
        return directors;
    }

    @Override
    public Director findByNameLike(String name) {
        return directorRepository.findByNameLike(name);
    }
}
