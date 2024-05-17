package com.team12.fantafilm.service.impl;


import com.team12.fantafilm.model.Director;
import com.team12.fantafilm.repository.DirectorRepository;
import com.team12.fantafilm.service.IDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DirectorService implements IDirectorService {
    @Autowired
    DirectorRepository directorRepository;
    @Override
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    public Director findById(Long id) {
        return directorRepository.findById(id).get();
    }

    @Override
    public Boolean addDirector(Director director) {
        try {
               if(director.getCode()==null)
               {
                   Random generate = new Random();
                   int val = generate.nextInt((10000-1)+1)+1;
                   director.setCode("DIR"+val);
               }
            directorRepository.save(director);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }

    @Override
    public Boolean update(Director director) {

        try {
            directorRepository.save(director);
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
            directorRepository.deleteById(id);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }
}
