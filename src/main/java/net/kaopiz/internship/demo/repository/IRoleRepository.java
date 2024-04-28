package net.kaopiz.internship.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kaopiz.internship.demo.entity.Role;
import java.util.List;

public interface IRoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String roleName);

}
