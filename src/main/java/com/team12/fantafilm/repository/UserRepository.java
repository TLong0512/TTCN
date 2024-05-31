package com.team12.fantafilm.repository;

import com.team12.fantafilm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
     User findByUserName(String userName);
     Optional<User> findUserEntityByUserName(String userName);

}
