package com.team12.fantafilm.service;

import com.team12.fantafilm.model.Cinema;

import java.util.List;
import java.util.Optional;

public interface ICinemaService {
    List<Cinema> findAll();
    Cinema findById(Long id);
    Boolean addCinema(Cinema cinema);
    Boolean update(Cinema cinema);
    Boolean delete(Long id);
}
