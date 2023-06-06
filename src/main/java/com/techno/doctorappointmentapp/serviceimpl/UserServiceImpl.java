package com.techno.doctorappointmentapp.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.techno.doctorappointmentapp.entity.DoctorRating;
import com.techno.doctorappointmentapp.entity.DoctorsAppointment;
import com.techno.doctorappointmentapp.entity.User;
import com.techno.doctorappointmentapp.enumeration.AppointmentEnumeration;
import com.techno.doctorappointmentapp.enumeration.DoctorAvailabilityEnumeration;
import com.techno.doctorappointmentapp.pojo.DoctorBookingDetailsPojo;
import com.techno.doctorappointmentapp.pojo.DoctorPojo;
import com.techno.doctorappointmentapp.pojo.DoctorsPojo;
import com.techno.doctorappointmentapp.pojo.RatingPojo;
import com.techno.doctorappointmentapp.pojo.UserPojo;
import com.techno.doctorappointmentapp.repository.DoctorRepository;
import com.techno.doctorappointmentapp.repository.UserRepository;
import com.techno.doctorappointmentapp.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final DoctorRepository doctorRepository;

	@Transactional
	@Override
	public String addRating(RatingPojo ratingPojo) {
		return doctorRepository.findByDoctorIdAndIsDeleteFalse(ratingPojo.getDoctorId()).map(doctor -> {
			long count = doctor.getDoctorRating().size();
			Double averageRating = doctor.getDoctorRating().stream()
					.collect(Collectors.averagingDouble(DoctorRating::getRating));

			doctor.setDoctorAverageRating((averageRating * count + ratingPojo.getRating()) / (count + 1));
			User user = userRepository.findByUserIdAndIsDeleteFalse(ratingPojo.getUserId()).orElseThrow(null);
			DoctorRating doctorRating = DoctorRating.builder().rating(ratingPojo.getRating())
					.comment(ratingPojo.getComment()).user(user).build();
			doctor.getDoctorRating().add(doctorRating);
			return "rating is added!!!!";
		}).orElseThrow(null);
	}

	@Override
	public List<DoctorPojo> searchDoctors(String search) {
		return doctorRepository.search(search).orElseGet(ArrayList::new).stream()
				.map(doctor -> modelMapper.map(doctor, DoctorPojo.class)).collect(Collectors.toList());

	}

	@Transactional
	@Override
	public UserPojo deleteUserById(Long userId) {
		return userRepository.findByUserIdAndIsDeleteFalse(userId).map(user -> {
			user.setIsDelete(Boolean.TRUE);
			return modelMapper.map(user, UserPojo.class);
		}).orElseThrow(() -> new RuntimeException("Invalid User Id!!!"));
	}

	@Override
	public List<DoctorsPojo> getDoctors() {
		return doctorRepository
				.findByIsDeleteFalseAndDoctorAvailabilityOrderByDoctorAverageRatingDesc(
						DoctorAvailabilityEnumeration.AVAILABLE)
				.orElseGet(ArrayList::new).stream()
				.map(doctor -> DoctorsPojo.builder().doctorName("Dr." + doctor.getUser().getUserName())
						.doctorAverageRating(doctor.getDoctorAverageRating())
						.userPhoneNumber(doctor.getUser().getUserPhoneNumber())
						.doctorSpeciality(doctor.getDoctorSpeciality()).build())
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public DoctorBookingDetailsPojo bookAppointment(DoctorBookingDetailsPojo doctorBookingDetailsPojo) {
		if (LocalDateTime.now()
				.isAfter(doctorBookingDetailsPojo.getLocalDate().atTime(doctorBookingDetailsPojo.getLocalTime())))
			return null;
		return doctorRepository
				.findByDoctorIdAndIsDeleteFalseAndDoctorAvailability(doctorBookingDetailsPojo.getDoctorIds(),
						DoctorAvailabilityEnumeration.AVAILABLE)
				.map(doctor -> {
					doctor.getDoctorsAppointments().stream()
							.filter(doc -> doc.getBookingDate().isEqual(doctorBookingDetailsPojo.getLocalDate()))
							.forEach(appointment -> {
								if ((appointment.getBookingTime().plusMinutes(15)
										.isBefore(doctorBookingDetailsPojo.getLocalTime()))
										&& (appointment.getBookingTime().minusMinutes(15)
												.isAfter(doctorBookingDetailsPojo.getLocalTime())))
									throw new RuntimeException("Booking slot is not available");
							});
					DoctorsAppointment doctorsAppointment = DoctorsAppointment.builder()
							.bookingDate(doctorBookingDetailsPojo.getLocalDate())
							.bookingTime(doctorBookingDetailsPojo.getLocalTime())
							.status(AppointmentEnumeration.SCHEDULED)
							.user(userRepository.findByUserIdAndIsDeleteFalse(doctorBookingDetailsPojo.getUserIds())
									.orElseThrow(() -> new RuntimeException("Invalid User Id!!!")))
							.doctor(doctor).build();
					doctor.getDoctorsAppointments().add(doctorsAppointment);
					return modelMapper.map(doctorsAppointment, DoctorBookingDetailsPojo.class);
				}).orElseThrow(() -> new RuntimeException("Invalid doctor Id!!!"));
	}

}
