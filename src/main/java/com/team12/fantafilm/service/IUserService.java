package com.team12.fantafilm.service;

import com.team12.fantafilm.model.User;

import java.util.Optional;

public interface IUserService {
     User findByUserName(String userName);

}
