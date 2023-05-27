package com.techno.doctorappointmentapp.service;

import java.util.List;

import com.techno.doctorappointmentapp.pojo.DoctorPOJO;
import com.techno.doctorappointmentapp.pojo.UserPOJO;

public interface UserService {

	String addRating(Long doctorId, Long userId, Double rating);

	List<DoctorPOJO> searchDoctors(String search);

	UserPOJO deleteUserById(Long userId);

}
