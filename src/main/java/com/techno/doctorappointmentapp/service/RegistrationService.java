package com.techno.doctorappointmentapp.service;

import com.techno.doctorappointmentapp.pojo.DoctorPOJO;
import com.techno.doctorappointmentapp.pojo.UserPOJO;

public interface RegistrationService {

	UserPOJO registerUser(UserPOJO userPOJO);

	DoctorPOJO registerDoctor(DoctorPOJO doctorPOJO);

}
