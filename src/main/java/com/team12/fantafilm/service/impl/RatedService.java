package com.team12.fantafilm.service.impl;

import com.team12.fantafilm.model.Rated;
import com.team12.fantafilm.repository.RatedRepository;
import com.team12.fantafilm.service.IRatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
public class RatedService implements IRatedService{
    @Autowired
    RatedRepository ratedRepository;
    @Override
    public List<Rated> findAll() {
        return ratedRepository.findAll();
    }

    @Override
    public Rated findById(Long id) {
        return ratedRepository.findById(id).get();
    }

    @Override
    public Boolean addRated(Rated rated) {
        try
        {
           if(rated.getCode()==null)
           {
               Random generate = new Random();
               int val = generate.nextInt((10000-1)+1)+1;
               rated.setCode("RATE"+val);
           }
            ratedRepository.save(rated);
            return  true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }




    @Override
    public Boolean update(Rated rated) {
        try
        {
            ratedRepository.save(rated);
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
            ratedRepository.deleteById(id);
            return  true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
