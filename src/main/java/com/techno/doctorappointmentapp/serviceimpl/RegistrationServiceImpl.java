package com.techno.doctorappointmentapp.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.techno.doctorappointmentapp.entity.Doctor;
import com.techno.doctorappointmentapp.entity.Roles;
import com.techno.doctorappointmentapp.entity.User;
import com.techno.doctorappointmentapp.pojo.UserPojo;
import com.techno.doctorappointmentapp.repository.RolesRepository;
import com.techno.doctorappointmentapp.repository.UserRepository;
import com.techno.doctorappointmentapp.service.RegistrationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

	private final ModelMapper modelMapper;
	private final RolesRepository rolesRepository;
	private final UserRepository userRepository;

	@Override
	@Transactional
	public UserPojo registerUser(UserPojo userPojo) {
		return Optional.ofNullable(userPojo).map(usrPojo -> {
			List<Roles> roles = rolesRepository.findAllById(usrPojo.getRoleId());
			User user = modelMapper.map(userPojo, User.class);
			if (userPojo.getDoctor() != null) {
				user.setDoctor(modelMapper.map(usrPojo.getDoctor(), Doctor.class));
				user.getDoctor().setUser(user);
			}
			roles.forEach(role -> {
				Set<User> users = role.getUsers();
				users.add(user);
				role.setUsers(users);
			});
			userRepository.save(user);
			return modelMapper.map(user, UserPojo.class);
		}).orElseGet(UserPojo::new);
	}
}
