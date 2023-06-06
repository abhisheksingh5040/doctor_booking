package com.techno.doctorappointmentapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techno.doctorappointmentapp.entity.Doctor;
import com.techno.doctorappointmentapp.enumeration.DoctorAvailabilityEnumeration;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	Optional<Doctor> findByDoctorIdAndIsDeleteFalse(Long doctorId);

	Optional<List<Doctor>> findByIsDeleteFalseAndDoctorAvailabilityOrderByDoctorAverageRatingDesc(
			DoctorAvailabilityEnumeration availabilityEnumeration);

	Optional<Doctor> findByDoctorIdAndIsDeleteFalseAndDoctorAvailability(Long doctorId,
			DoctorAvailabilityEnumeration available);

	@Query("SELECT u FROM Doctor u WHERE u.doctorName LIKE %?1% OR u.doctorSpeciality LIKE %?1% OR u.doctorSpeciality LIKE %?1% OR u.user.userEmailId LIKE %?1% OR CAST(u.user.userPhoneNumber AS string) LIKE %?1% order by u.doctorAverageRating desc")
	Optional<List<Doctor>> search(String search);

}
