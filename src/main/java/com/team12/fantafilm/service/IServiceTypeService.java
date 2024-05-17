package com.team12.fantafilm.service;

import com.team12.fantafilm.model.ServiceType;

import java.util.List;

public interface IServiceTypeService {
    List<ServiceType> findAll();
    ServiceType findById(Long id);
    Boolean addServiceType(ServiceType serviceType);
    Boolean update(ServiceType serviceType);
    Boolean delete(Long id);
}
