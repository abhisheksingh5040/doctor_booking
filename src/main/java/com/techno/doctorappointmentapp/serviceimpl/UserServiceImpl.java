package com.techno.doctorappointmentapp.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.techno.doctorappointmentapp.entity.DoctorRating;
import com.techno.doctorappointmentapp.repository.DoctorRepository;
import com.techno.doctorappointmentapp.repository.RatingRepository;
import com.techno.doctorappointmentapp.repository.UserRepository;
import com.techno.doctorappointmentapp.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final RatingRepository ratingRepository;
	private final DoctorRepository doctorRepository;

	@Override
	public String addRating(Long doctorId, Long userId, Double rating) {
		List<DoctorRating> orElseThrow = ratingRepository.findByDoctorDoctorIdAndDoctorIsDeleteFalse(doctorId)
				.orElseGet(null);
		return null;
	}

}
