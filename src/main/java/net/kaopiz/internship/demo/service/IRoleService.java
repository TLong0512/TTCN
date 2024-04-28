package net.kaopiz.internship.demo.service;

import java.util.List;

import net.kaopiz.internship.demo.entity.Role;

public interface IRoleService {

	Role createRole(Role role);

	Role getRoleById(Long roleId);

	List<Role> getAllRoles();

	void deleteRole(Long roleId);

}
