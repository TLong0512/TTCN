package com.team12.fantafilm.service.impl;

import com.team12.fantafilm.model.Cinema;
import com.team12.fantafilm.repository.CinemaRepository;
import com.team12.fantafilm.service.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CinemaService implements ICinemaService{
    @Autowired
    CinemaRepository cinemaRepository;
    @Override
    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    @Override
    public Cinema findById(Long id) {
        return cinemaRepository.findById(id).get();
    }

    @Override
    public Boolean addCinema(Cinema cinema) {
        try
        {
            if(cinema.getCode()== null)
            {
                Random generate = new Random();
                int val = generate.nextInt((10000-1)+1)+1;
                cinema.setCode("CIN"+val);
            }
            cinemaRepository.save(cinema);
            return  true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }




    @Override
    public Boolean update(Cinema cinema) {
        try
        {
            cinemaRepository.save(cinema);
            return  true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            cinemaRepository.deleteById(id);
            return  true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
