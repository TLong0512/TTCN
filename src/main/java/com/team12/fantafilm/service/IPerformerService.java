package com.team12.fantafilm.service;

import com.team12.fantafilm.model.Cinema;
import com.team12.fantafilm.model.Performer;

import java.util.List;

public interface IPerformerService {
    List<Performer> findAll();
    Performer findById(Long id);
    Boolean addPerformer(Performer performer);
    Boolean update(Performer performer);
    Boolean delete(Long id);
}
