package com.team12.fantafilm.service;

import com.team12.fantafilm.model.Rated;

import java.util.List;
import java.util.Optional;

public interface IRatedService {
    List<Rated> findAll();
    Rated findById(Long id);
    Boolean addRated(Rated rated);
    Boolean update(Rated rated);
    Boolean delete(Long id);
}
