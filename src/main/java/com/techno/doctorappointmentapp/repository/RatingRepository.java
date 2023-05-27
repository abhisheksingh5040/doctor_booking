package com.techno.doctorappointmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techno.doctorappointmentapp.entity.DoctorRating;

public interface RatingRepository extends JpaRepository<DoctorRating, Long> {

	//Optional<List<DoctorRating>> findByDoctorDoctorIdAndDoctorIsDeleteFalse(Long doctorId);
}
