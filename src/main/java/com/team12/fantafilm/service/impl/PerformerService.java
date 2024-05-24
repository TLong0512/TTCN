package com.team12.fantafilm.service.impl;


import com.team12.fantafilm.model.Movie;
import com.team12.fantafilm.model.Performer;
import com.team12.fantafilm.repository.MovieRepository;
import com.team12.fantafilm.repository.PerformerRepository;
import com.team12.fantafilm.service.IPerformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PerformerService implements IPerformerService {
    @Autowired
    PerformerRepository performerRepository;
    @Autowired
    MovieRepository movieRepository;
    @Override
    public List<Performer> findAll() {
        return performerRepository.findAll();
    }

    @Override
    public Performer findById(Long id) {
        return performerRepository.findById(id).get();
    }

    @Override
    public Boolean addPerformer(Performer performer) {
        try {
            if(performer.getCode()==null)
            {
                Random generate = new Random();
                int val = generate.nextInt((10000-1)+1)+1;
                performer.setCode("ACT"+val);
            }
            performerRepository.save(performer);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }

    @Override
    public Boolean update(Performer performer) {
        try {
            performerRepository.save(performer);
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
            performerRepository.deleteById(id);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }
    @Override
    public List<Performer> searchNamePerformer(String keycode) {
        return performerRepository.findByNameContains(keycode);
    }

    @Override
    public List<Performer> findPerformerByMovieId(Long id) {
        Movie movie = movieRepository.findById(id).get();
        List<Performer> listPerformer = movie.getPerformers();
        return listPerformer;
    }

    @Override
    public Performer findByNameLike(String name) {
        return performerRepository.findByNameLike(name);
    }

}
