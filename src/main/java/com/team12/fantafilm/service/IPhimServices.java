package com.team12.fantafilm.service;

import com.team12.fantafilm.model.Phim;

import java.util.List;

public interface IPhimServices {
    List<Phim> getAll();
    Boolean create(Phim phim);
    Phim findById(Long id);
    Boolean update(Phim phim);
    Boolean delete(Long id);
}
