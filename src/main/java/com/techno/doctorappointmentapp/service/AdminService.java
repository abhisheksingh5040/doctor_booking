package com.techno.doctorappointmentapp.service;

import java.util.List;

import com.techno.doctorappointmentapp.pojo.UserPOJO;

public interface AdminService {

	Long addRoles(String role);

	UserPOJO getUserById(Long userId);

	List<UserPOJO> getAllUsers();

	UserPOJO deletedUser(Long userId);

}
