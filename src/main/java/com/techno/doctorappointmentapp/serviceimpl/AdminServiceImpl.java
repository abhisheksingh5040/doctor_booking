package com.techno.doctorappointmentapp.serviceimpl;

import org.springframework.stereotype.Service;

import com.techno.doctorappointmentapp.entity.Roles;
import com.techno.doctorappointmentapp.repository.RolesRepository;
import com.techno.doctorappointmentapp.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final RolesRepository rolesRepository;

	@Override
	public Long addRoles(String role) {
		return rolesRepository.save(Roles.builder().roleName(role).build()).getRoleId();
	}

}
