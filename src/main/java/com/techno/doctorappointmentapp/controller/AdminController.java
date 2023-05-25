package com.techno.doctorappointmentapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techno.doctorappointmentapp.reponse.SuccessResponse;
import com.techno.doctorappointmentapp.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService;

	@PostMapping("/roles/{role}")
	public ResponseEntity<SuccessResponse> addRoles(@PathVariable(value = "role") String role) {
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.builder().isError(Boolean.FALSE)
				.message("Roles created successfully!!!").data(adminService.addRoles(role)).build());

	}
}
