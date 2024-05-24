package com.team12.fantafilm.service;

import com.team12.fantafilm.model.MovieType;

import java.util.List;

public interface IMovieTypeService {
    List<MovieType> findAll();
    MovieType findById(Long id);
    Boolean addMovieType(MovieType movieType);
    Boolean update(MovieType movieType);
    Boolean delete(Long id);

    List<MovieType> searchNameMovieType(String keycode);

    List<MovieType> findMovieTyprbyMovieId(Long movieId);

    MovieType findByNameLike(String name);
}
