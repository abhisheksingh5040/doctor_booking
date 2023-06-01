package com.techno.doctorappointmentapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techno.doctorappointmentapp.enumeration.DoctorAvailabilityEnumeration;
import com.techno.doctorappointmentapp.reponse.SuccessResponse;
import com.techno.doctorappointmentapp.service.DoctorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {

	private final DoctorService doctorService;

	@PutMapping("{doctorId}/{status}")
	public ResponseEntity<SuccessResponse> updateStatus(@PathVariable(value = "doctorId") Long doctorId,
			@PathVariable(value = "status") DoctorAvailabilityEnumeration status) {
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.builder().isError(Boolean.FALSE)
				.message("status updated!!!").data(doctorService.updateStatus(doctorId, status)).build());

	}
	
	@GetMapping("/")
	public ResponseEntity<SuccessResponse> getAllAppointments() {
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.builder().isError(Boolean.FALSE)
				.message("All appointment fetched successfully!!!").data(doctorService.getAllAppointments()).build());

	}
}
