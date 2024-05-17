package com.team12.fantafilm.service.impl;


import com.team12.fantafilm.model.RankCustomer;
import com.team12.fantafilm.repository.RankCustomerRepository;
import com.team12.fantafilm.service.IRankCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RankCustomerService implements IRankCustomerService {
    @Autowired
    RankCustomerRepository rankCustomerRepository;
    @Override
    public List<RankCustomer> findAll() {
        return rankCustomerRepository.findAll();
    }

    @Override
    public RankCustomer findById(Long id) {
        return rankCustomerRepository.findById(id).get();
    }

    @Override
    public Boolean addRankCustomer(RankCustomer rankCustomer) {
        try {
            if (rankCustomer.getCode()==null)
            {
                Random generate = new Random();
                int val = generate.nextInt((10000-1)+1)+1;
                rankCustomer.setCode("RANK"+val);
            }
            rankCustomerRepository.save(rankCustomer);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }

    @Override
    public Boolean update(RankCustomer rankCustomer) {
        try {
            rankCustomerRepository.save(rankCustomer);
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
            rankCustomerRepository.deleteById(id);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }
}
