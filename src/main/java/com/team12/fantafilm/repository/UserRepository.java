package com.team12.fantafilm.repository;

import com.team12.fantafilm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
     User findByUserName(String userName);
}
