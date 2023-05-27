package com.techno.doctorappointmentapp.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.techno.doctorappointmentapp.entity.DoctorRating;
import com.techno.doctorappointmentapp.entity.User;
import com.techno.doctorappointmentapp.pojo.DoctorPOJO;
import com.techno.doctorappointmentapp.pojo.UserPOJO;
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

	@Transactional
	@Override
	public String addRating(Long doctorId, Long userId, Double rating) {
		return doctorRepository.findByDoctorIdAndIsDeleteFalse(doctorId).map(doctor -> {
			long count = doctor.getDoctorRating().size();
			Double averageRating = doctor.getDoctorRating().stream()
					.collect(Collectors.averagingDouble(DoctorRating::getRating));

			doctor.setDoctorAverageRating((averageRating * count + rating) / (count + 1));
			User user = userRepository.findByUserIdAndIsDeleteFalse(userId).orElseThrow(null);
			ratingRepository.save(DoctorRating.builder().rating(rating).user(user).build());

			return "rating is added!!!!";

		}).orElseThrow(null);

	}

	@Override
	public List<DoctorPOJO> searchDoctors(String search) {
//		return doctorRepository
//				.findByDoctorNameOrdoctorSpecialityOrDoctorEducationOrUserUserEmailIdOrUserUserPhoneNumberOrderByDoctorAverageRatingDesc(
//						search)
//				.orElseGet(ArrayList::new).stream().map(doctor->modelMapper.map(doctor,DoctorPOJO.class)).collect(Collectors.toList());

		return null;
	}

	@Transactional
	@Override
	public UserPOJO deleteUserById(Long userId) {
		return userRepository.findByUserIdAndIsDeleteFalse(userId).map(user -> {
			user.setIsDelete(Boolean.TRUE);
			return modelMapper.map(user, UserPOJO.class);
		}).orElseThrow(null);
	}

}
