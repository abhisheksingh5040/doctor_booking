package com.techno.doctorappointmentapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.techno.doctorappointmentapp.enumeration.DoctorAvailabilityEnumeration;
import com.techno.doctorappointmentapp.pojo.DoctorBookingDetailsPojo;
import com.techno.doctorappointmentapp.pojo.DoctorPojo;

public interface DoctorService {

	DoctorPojo updateStatus(Long doctorId, DoctorAvailabilityEnumeration status);

	Map<LocalDate,List<DoctorBookingDetailsPojo>> getAllAppointments();

}
