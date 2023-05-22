package com.techno.doctorappointmentapp.serviceimpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.techno.doctorappointmentapp.entity.Doctor;
import com.techno.doctorappointmentapp.entity.User;
import com.techno.doctorappointmentapp.pojo.DoctorPOJO;
import com.techno.doctorappointmentapp.pojo.UserPOJO;
import com.techno.doctorappointmentapp.repository.DoctorRepository;
import com.techno.doctorappointmentapp.repository.UserRepository;
import com.techno.doctorappointmentapp.service.RegistrationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final DoctorRepository doctorRepository;

	@Override
	@Transactional
	public UserPOJO registerUser(UserPOJO userPOJO) {
		return Optional.ofNullable(userPOJO).map(userPojo -> {
			userRepository.save(modelMapper.map(userPojo, User.class));
			return userPojo;
		}).orElseGet(UserPOJO::new);
	}

	@Override
	@Transactional
	public DoctorPOJO registerDoctor(DoctorPOJO doctorPOJO) {
		return Optional.ofNullable(doctorPOJO).map(doctorPojo -> {
			doctorRepository.save(modelMapper.map(doctorPojo, Doctor.class));
			return doctorPojo;
		}).orElseGet(DoctorPOJO::new);

	}

}
