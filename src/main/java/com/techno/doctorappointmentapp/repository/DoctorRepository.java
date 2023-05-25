package com.techno.doctorappointmentapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techno.doctorappointmentapp.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

	Optional<Doctor> findByDoctorIdAndIsDeleteFalse(Long doctorId);

}
