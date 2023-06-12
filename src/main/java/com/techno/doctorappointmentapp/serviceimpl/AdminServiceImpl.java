package com.techno.doctorappointmentapp.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.techno.doctorappointmentapp.appconstant.ApplicationConstant.*;
import com.techno.doctorappointmentapp.entity.Roles;
import com.techno.doctorappointmentapp.exception.UserNotFoundException;
import com.techno.doctorappointmentapp.pojo.UserPojo;
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
	public UserPojo getUserById(Long userId) {
		return userRepository.findByUserIdAndIsDeleteFalseAndDoctorIsNull(userId)
				.map(user -> modelMapper.map(user, UserPojo.class))
				.orElseThrow(() -> new UserNotFoundException(INVALID_USER_ID));
	}

	@Override
	public List<UserPojo> getAllUsers() {
		return userRepository.findByIsDeleteFalseAndDoctorNull().orElseGet(ArrayList::new).stream()
				.map(user -> UserPojo.builder().userName(user.getUserName()).userEmailId(user.getUserEmailId())
						.userSex(user.getUserSex()).userAge(user.getUserAge())
						.userPhoneNumber(user.getUserPhoneNumber()).build())
				.collect(Collectors.toList());
	}

	@Override
	public UserPojo deletedUser(Long userId) {
		return userRepository.findByUserIdAndIsDeleteTrue(userId).map(user -> modelMapper.map(user, UserPojo.class))
				.orElseThrow(() -> new UserNotFoundException(INVALID_USER_ID));
	}

}
