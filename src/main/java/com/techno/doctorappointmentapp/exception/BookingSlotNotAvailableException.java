package com.techno.doctorappointmentapp.exception;

public class BookingSlotNotAvailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookingSlotNotAvailableException(String message) {
		super(message);
	}

}
