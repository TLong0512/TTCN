package net.kaopiz.internship.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.entity.Role;
import net.kaopiz.internship.demo.repository.IRoleRepository;
import net.kaopiz.internship.demo.service.IRoleService;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements IRoleService {

	private final IRoleRepository roleRepository;

	@Override
	public Role createRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role getRoleById(Long roleId) {
		return roleRepository.findById(roleId).orElse(null);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public void deleteRole(Long roleId) {
		roleRepository.deleteById(roleId);
	}

}
