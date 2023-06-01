package com.techno.doctorappointmentapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techno.doctorappointmentapp.entity.Doctor;
import com.techno.doctorappointmentapp.enumeration.DoctorAvailabilityEnumeration;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	Optional<Doctor> findByDoctorIdAndIsDeleteFalse(Long doctorId);

	Optional<List<Doctor>> findByIsDeleteFalseAndDoctorAvailabilityOrderByDoctorAverageRatingDesc(
			DoctorAvailabilityEnumeration availabilityEnumeration);

	Optional<Doctor> findByDoctorIdAndIsDeleteFalseAndDoctorAvailability(Long doctorId,
			DoctorAvailabilityEnumeration available);

//	Optional<List<Doctor>> findByDoctorNameOrdoctorSpecialityOrDoctorEducationOrUserUserEmailIdOrUserUserPhoneNumberOrderByDoctorAverageRatingDesc(
//			String search);

}
