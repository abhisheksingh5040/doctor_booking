package com.techno.doctorappointmentapp.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.techno.doctorappointmentapp.entity.Roles;
import com.techno.doctorappointmentapp.pojo.UserPOJO;
import com.techno.doctorappointmentapp.repository.RolesRepository;
import com.techno.doctorappointmentapp.repository.UserRepository;
import com.techno.doctorappointmentapp.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final ModelMapper modelMapper;
	private final RolesRepository rolesRepository;
	private final UserRepository userRepository;

	@Override
	public Long addRoles(String role) {
		return rolesRepository.save(Roles.builder().roleName(role).build()).getRoleId();
	}

	@Override
	public UserPOJO getUserById(Long userId) {
		return userRepository.findByUserIdAndIsDeleteFalseAndDoctorIsNull(userId)
				.map(user -> modelMapper.map(user, UserPOJO.class)).orElseThrow(null);
	}

	@Override
	public List<UserPOJO> getAllUsers() {
		return userRepository.findByIsDeleteFalseAndDoctorNull().orElseGet(() -> new ArrayList<>()).stream()
				.map(user -> UserPOJO.builder().userName(user.getUserName()).userEmailId(user.getUserEmailId())
						.userSex(user.getUserSex()).userAge(user.getUserAge())
						.userPhoneNumber(user.getUserPhoneNumber()).build())
				.collect(Collectors.toList());
	}

	@Override
	public UserPOJO deletedUser(Long userId) {
		return userRepository.findByUserIdAndIsDeleteTrue(userId).map(user -> modelMapper.map(user, UserPOJO.class))
				.orElseThrow(null);
	}

}
