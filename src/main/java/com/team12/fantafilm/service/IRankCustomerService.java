package com.team12.fantafilm.service;

import com.team12.fantafilm.model.RankCustomer;

import java.util.List;

public interface IRankCustomerService {
    List<RankCustomer> findAll();
    RankCustomer findById(Long id);
    Boolean addRankCustomer(RankCustomer rankCustomer);
    Boolean update(RankCustomer rankCustomer);
    Boolean delete(Long id);
}
