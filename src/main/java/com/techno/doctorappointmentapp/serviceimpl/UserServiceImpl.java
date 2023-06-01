package com.techno.doctorappointmentapp.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.techno.doctorappointmentapp.pojo.DoctorBookingDetailsPOJO;
import com.techno.doctorappointmentapp.pojo.DoctorPOJO;
import com.techno.doctorappointmentapp.pojo.DoctorsPOJO;
import com.techno.doctorappointmentapp.pojo.RatingPOJO;
import com.techno.doctorappointmentapp.pojo.UserPOJO;
import com.techno.doctorappointmentapp.repository.DoctorAppointmentRepository;
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
	private final DoctorAppointmentRepository appointmentRepository;

	@Transactional
	@Override
	public String addRating(RatingPOJO ratingPOJO) {
		return doctorRepository.findByDoctorIdAndIsDeleteFalse(ratingPOJO.getDoctorId()).map(doctor -> {
			long count = doctor.getDoctorRating().size();
			Double averageRating = doctor.getDoctorRating().stream()
					.collect(Collectors.averagingDouble(DoctorRating::getRating));

			doctor.setDoctorAverageRating((averageRating * count + ratingPOJO.getRating()) / (count + 1));
			User user = userRepository.findByUserIdAndIsDeleteFalse(ratingPOJO.getUserId()).orElseThrow(null);
			DoctorRating doctorRating = DoctorRating.builder().rating(ratingPOJO.getRating())
					.comment(ratingPOJO.getComment()).user(user).build();
			doctor.setDoctorRating(Arrays.asList(doctorRating));
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
		}).orElseThrow(() -> new RuntimeException("Invalid User Id!!!"));
	}

	@Override
	public List<DoctorsPOJO> getDoctors() {
		return doctorRepository
				.findByIsDeleteFalseAndDoctorAvailabilityOrderByDoctorAverageRatingDesc(
						DoctorAvailabilityEnumeration.AVAILABLE)
				.orElseGet(ArrayList::new).stream()
				.map(doctor -> DoctorsPOJO.builder().doctorName("Dr." + doctor.getUser().getUserName())
						.doctorAverageRating(doctor.getDoctorAverageRating())
						.userPhoneNumber(doctor.getUser().getUserPhoneNumber())
						.doctorSpeciality(doctor.getDoctorSpeciality()).build())
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public DoctorBookingDetailsPOJO bookAppointment(DoctorBookingDetailsPOJO doctorBookingDetailsPOJO) {
		if (LocalDateTime.now()
				.isAfter(doctorBookingDetailsPOJO.getLocalDate().atTime(doctorBookingDetailsPOJO.getLocalTime())))
			return null;
		return doctorRepository
				.findByDoctorIdAndIsDeleteFalseAndDoctorAvailability(doctorBookingDetailsPOJO.getDoctorIds(),
						DoctorAvailabilityEnumeration.AVAILABLE)
				.map(doctor -> {
					doctor.getDoctorsAppointments().stream()
							.filter(doc -> doc.getBookingDate().isEqual(doctorBookingDetailsPOJO.getLocalDate()))
							.forEach(appointment -> {
								if ((appointment.getBookingTime().plusMinutes(15)
										.isBefore(doctorBookingDetailsPOJO.getLocalTime()))
										&& (appointment.getBookingTime().minusMinutes(15)
												.isAfter(doctorBookingDetailsPOJO.getLocalTime())))
									throw new RuntimeException("Booking slot is not available");
							});
					return modelMapper.map(appointmentRepository.save(DoctorsAppointment.builder()
							.bookingDate(doctorBookingDetailsPOJO.getLocalDate())
							.bookingTime(doctorBookingDetailsPOJO.getLocalTime())
							.status(AppointmentEnumeration.SCHEDULED)
							.user(userRepository.findByUserIdAndIsDeleteFalse(doctorBookingDetailsPOJO.getUserIds())
									.orElseThrow(() -> new RuntimeException("Invalid User Id!!!")))
							.doctor(doctor).build()), DoctorBookingDetailsPOJO.class);
				}).orElseThrow(() -> new RuntimeException("Invalid doctor Id!!!"));
	}

}
