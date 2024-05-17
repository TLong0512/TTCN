package com.team12.fantafilm.service.impl;

import com.team12.fantafilm.model.Phim;
import com.team12.fantafilm.repository.PhimRepository;
import com.team12.fantafilm.service.IPhimServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhimService implements IPhimServices {
   @Autowired
   private PhimRepository phimRepository;
    @Override
    public List<Phim> getAll() {
        return this.phimRepository.findAll();
    }

    @Override
    public Boolean create(Phim phim) {
        try{
            this.phimRepository.save(phim);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Phim findById(Long id) {
        return null;
    }

    @Override
    public Boolean update(Phim phim) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
