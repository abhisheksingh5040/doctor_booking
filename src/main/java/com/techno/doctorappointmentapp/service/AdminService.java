package com.techno.doctorappointmentapp.service;

import java.util.List;

import com.techno.doctorappointmentapp.pojo.UserPojo;

public interface AdminService {

	Long addRoles(String role);

	UserPojo getUserById(Long userId);

	List<UserPojo> getAllUsers();

	UserPojo deletedUser(Long userId);

}
