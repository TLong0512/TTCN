package net.kaopiz.internship.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.kaopiz.internship.demo.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {

	Page<User> findByFullNameContaining(String keyword, Pageable pageable);

	Page<User> findByActiveFlagAndFullNameContainingOrderByCreatedAtDesc(boolean status, String keyword,
			Pageable pageable);

	Page<User> findByActiveFlagOrderByCreatedAtDesc(boolean status, Pageable pageable);

	Optional<User> findByEmail(String email);

}
