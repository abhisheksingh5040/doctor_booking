package com.techno.doctorappointmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techno.doctorappointmentapp.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

}
