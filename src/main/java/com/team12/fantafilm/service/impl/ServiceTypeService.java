package com.team12.fantafilm.service.impl;


import com.team12.fantafilm.model.ServiceType;
import com.team12.fantafilm.repository.ServiceTypeRepository;
import com.team12.fantafilm.service.IServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ServiceTypeService implements IServiceTypeService {
    @Autowired
    ServiceTypeRepository ServiceTypeRepository;
    @Override
    public List<ServiceType> findAll() {
        return ServiceTypeRepository.findAll();
    }

    @Override
    public ServiceType findById(Long id) {
        return ServiceTypeRepository.findById(id).get();
    }

    @Override
    public Boolean addServiceType(ServiceType ServiceType) {
        try {
            if(ServiceType.getCode()==null)
            {
                Random generate = new Random();
                int val = generate.nextInt((10000-1)+1)+1;
                ServiceType.setCode("SE"+val);
            }
            ServiceTypeRepository.save(ServiceType);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }

    @Override
    public Boolean update(ServiceType ServiceType) {
        try {
            ServiceTypeRepository.save(ServiceType);
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
            ServiceTypeRepository.deleteById(id);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }
}
