package com.team12.fantafilm.service.impl;

import com.team12.fantafilm.model.Movie;
import com.team12.fantafilm.repository.MovieRepository;
import com.team12.fantafilm.service.IDetailMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailMovieService implements IDetailMovieService {
    @Autowired
    private MovieRepository repository;

    public Movie getDetailMovie(Long id){
        Optional<Movie> opt = repository.findById(id);

        if(opt.isEmpty()){
            throw new RuntimeException("ID not found!");
        }
        return opt.get();
    }
}
