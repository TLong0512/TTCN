package com.team12.fantafilm.service;


import com.team12.fantafilm.model.Director;

import java.util.List;

public interface IDirectorService {
    List<Director> findAll();
    Director findById(Long id);
    Boolean addDirector(Director director);
    Boolean update(Director director);
    Boolean delete(Long id);
    List<Director> searchNameDirector(String keycode);

    List<Director> findDireactorByMovieId(Long movieId);

    Director findByNameLike(String name);
}
