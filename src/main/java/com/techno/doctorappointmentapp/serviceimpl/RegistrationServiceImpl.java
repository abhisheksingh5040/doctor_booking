package com.techno.doctorappointmentapp.serviceimpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.techno.doctorappointmentapp.entity.User;
import com.techno.doctorappointmentapp.pojo.UserPOJO;
import com.techno.doctorappointmentapp.repository.DoctorRepository;
import com.techno.doctorappointmentapp.repository.RolesRepository;
import com.techno.doctorappointmentapp.repository.UserRepository;
import com.techno.doctorappointmentapp.service.RegistrationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final RolesRepository rolesRepository;
	private final DoctorRepository doctorRepository;

	@Override
	@Transactional
	public UserPOJO registerUser(UserPOJO userPOJO) {
		return Optional.ofNullable(userPOJO).map(userPojo -> {
			User user = modelMapper.map(userPojo, User.class);
			if (userPOJO.getDoctor() != null)
				user.getDoctor().setUser(user);
			rolesRepository.findAllById(userPojo.getRoleId()).forEach(role -> {
				List<User> users = Arrays.asList(user);
				role.setUsers(new HashSet<>(users));
			});
			return modelMapper.map(userRepository.save(user), UserPOJO.class);
		}).orElseGet(UserPOJO::new);
	}
}
