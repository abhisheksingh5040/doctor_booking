package com.techno.doctorappointmentapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techno.doctorappointmentapp.pojo.DoctorBookingDetailsPojo;
import com.techno.doctorappointmentapp.pojo.RatingPojo;
import com.techno.doctorappointmentapp.reponse.SuccessResponse;
import com.techno.doctorappointmentapp.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("rating")
	public ResponseEntity<String> addRating(@RequestBody RatingPojo ratingPojo) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.addRating(ratingPojo));
	}

	@GetMapping("search")
	public ResponseEntity<SuccessResponse> searchDoctors(@RequestParam(value = "search") String search) {
		return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.builder().isError(Boolean.FALSE)
				.message("Search Results!!!!").data(userService.searchDoctors(search)).build());
	}

	@DeleteMapping("user/{userId}")
	public ResponseEntity<SuccessResponse> deleteUserById(@PathVariable(value = "userId") Long userId) {
		return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.builder().isError(Boolean.FALSE)
				.message("User deleted successfully!!!").data(userService.deleteUserById(userId)).build());

	}

	@GetMapping("doctors")
	public ResponseEntity<SuccessResponse> getDoctors() {
		return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.builder().isError(Boolean.FALSE)
				.message("All doctors fetched successfully!!!!").data(userService.getDoctors()).build());
	}
	
	@PostMapping("book-appointment")
	public ResponseEntity<SuccessResponse> bookAppointment(@RequestBody DoctorBookingDetailsPojo doctorBookingDetailsPojo) {
		return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.builder().isError(Boolean.FALSE)
				.message("Appointment booked successfully!!!!").data(userService.bookAppointment(doctorBookingDetailsPojo)).build());
	}
}
