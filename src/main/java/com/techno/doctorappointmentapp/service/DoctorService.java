package com.techno.doctorappointmentapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.techno.doctorappointmentapp.enumeration.DoctorAvailabilityEnumeration;
import com.techno.doctorappointmentapp.pojo.DoctorBookingDetailsPOJO;
import com.techno.doctorappointmentapp.pojo.DoctorPOJO;

public interface DoctorService {

	DoctorPOJO updateStatus(Long doctorId, DoctorAvailabilityEnumeration status);

	Map<LocalDate,List<DoctorBookingDetailsPOJO>> getAllAppointments();

}
