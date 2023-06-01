package com.techno.doctorappointmentapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techno.doctorappointmentapp.entity.DoctorsAppointment;

public interface DoctorAppointmentRepository extends JpaRepository<DoctorsAppointment, Long> {

	Optional<List<DoctorsAppointment>> findAllByOrderByBookingDateAscBookingTimeAsc();


}
