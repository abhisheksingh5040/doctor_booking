package com.techno.doctorappointmentapp.service;

import java.util.List;

import com.techno.doctorappointmentapp.pojo.DoctorBookingDetailsPojo;
import com.techno.doctorappointmentapp.pojo.DoctorPojo;
import com.techno.doctorappointmentapp.pojo.DoctorsPojo;
import com.techno.doctorappointmentapp.pojo.RatingPojo;
import com.techno.doctorappointmentapp.pojo.UserPojo;

public interface UserService {

	String addRating(RatingPojo ratingPojo);

	List<DoctorPojo> searchDoctors(String search);

	UserPojo deleteUserById(Long userId);

	List<DoctorsPojo> getDoctors();

	DoctorBookingDetailsPojo bookAppointment(DoctorBookingDetailsPojo doctorBookingDetailsPojo);

}
