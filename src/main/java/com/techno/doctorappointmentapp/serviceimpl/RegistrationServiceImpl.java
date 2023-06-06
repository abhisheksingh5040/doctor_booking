package com.techno.doctorappointmentapp.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.techno.doctorappointmentapp.entity.Roles;
import com.techno.doctorappointmentapp.entity.User;
import com.techno.doctorappointmentapp.pojo.UserPojo;
import com.techno.doctorappointmentapp.repository.RolesRepository;
import com.techno.doctorappointmentapp.service.RegistrationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

	private final ModelMapper modelMapper;
	private final RolesRepository rolesRepository;

	@Override
	@Transactional
	public UserPojo registerUser(UserPojo userPojo) {
		return Optional.ofNullable(userPojo).map(usrPojo -> {
			List<Roles> roles = rolesRepository.findAllById(usrPojo.getRoleId());
			User user = modelMapper.map(userPojo, User.class);
			// Optional.ofNullable(userPOJO.getDoctor()).map(do);
			if (userPojo.getDoctor() != null)
				user.getDoctor().setUser(user);
			roles.forEach(role -> {
				Set<User> users = role.getUsers();
				users.add(user);
				role.setUsers(users);
				rolesRepository.save(role);
			});

			return modelMapper.map(user, UserPojo.class);
		}).orElseGet(UserPojo::new);
	}
}
