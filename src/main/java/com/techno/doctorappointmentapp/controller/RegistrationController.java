package com.techno.doctorappointmentapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techno.doctorappointmentapp.pojo.UserPOJO;
import com.techno.doctorappointmentapp.reponse.SuccessResponse;
import com.techno.doctorappointmentapp.service.RegistrationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class RegistrationController {

	private final RegistrationService registrationService;

	@PostMapping("/user")
	public ResponseEntity<SuccessResponse> registerUser(@RequestBody UserPOJO userPOJO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.builder().isError(Boolean.FALSE)
				.message("User added successfully!!!!!")
				.data(registrationService.registerUser(userPOJO)).build());
	}
}
