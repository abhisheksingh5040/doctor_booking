package com.techno.doctorappointmentapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techno.doctorappointmentapp.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	public ResponseEntity<String> addRating(@PathVariable Long doctorId,@PathVariable Long userId,@PathVariable Double rating) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(userService.addRating(doctorId,userId,rating));
	}
}
