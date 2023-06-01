package com.techno.doctorappointmentapp.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.techno.doctorappointmentapp.entity.DoctorsAppointment;
import com.techno.doctorappointmentapp.enumeration.DoctorAvailabilityEnumeration;
import com.techno.doctorappointmentapp.pojo.DoctorBookingDetailsPOJO;
import com.techno.doctorappointmentapp.pojo.DoctorPOJO;
import com.techno.doctorappointmentapp.repository.DoctorAppointmentRepository;
import com.techno.doctorappointmentapp.repository.DoctorRepository;
import com.techno.doctorappointmentapp.service.DoctorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

	private final ModelMapper modelMapper;
	private final DoctorRepository doctorRepository;
	private final DoctorAppointmentRepository doctorAppointmentRepository;

	@Override
	public DoctorPOJO updateStatus(Long doctorId, DoctorAvailabilityEnumeration status) {
		return doctorRepository.findByDoctorIdAndIsDeleteFalse(doctorId).map(doctor -> {
			doctor.setDoctorAvailability(status);
			return modelMapper.map(doctor, DoctorPOJO.class);
		}).orElseGet(DoctorPOJO::new);
	}

	@Override
	public Map<LocalDate, List<DoctorBookingDetailsPOJO>> getAllAppointments() {
		return doctorAppointmentRepository.findAllByOrderByBookingDateAscBookingTimeAsc().orElseGet(ArrayList::new)
				.stream()
				.collect(Collectors.groupingBy(DoctorsAppointment::getBookingDate,
						Collectors.mapping(appoinment -> DoctorBookingDetailsPOJO.builder()
								.localDate(appoinment.getBookingDate()).localTime(appoinment.getBookingTime())
								.userName(appoinment.getUser().getUserName()).build(), Collectors.toList())));
	}

}
