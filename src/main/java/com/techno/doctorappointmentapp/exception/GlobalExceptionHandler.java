package com.techno.doctorappointmentapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.techno.doctorappointmentapp.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ UserNotFoundException.class, DoctorNotfoundException.class,
			BookingSlotNotAvailableException.class })
	public ResponseEntity<ErrorResponse> handler(RuntimeException exception) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(ErrorResponse.builder().IsError(Boolean.FALSE).message(exception.getMessage()).build());
	}

}
