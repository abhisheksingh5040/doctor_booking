package com.techno.doctorappointmentapp.exception;

public class DoctorNotfoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DoctorNotfoundException(String message) {
		super(message);
	}
}
