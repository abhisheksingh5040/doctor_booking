package com.techno.doctorappointmentapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techno.doctorappointmentapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserIdAndIsDeleteFalse(Long userId);

	Optional<List<User>> findByIsDeleteFalseAndDoctorNull();

	Optional<User> findByUserIdAndIsDeleteTrue(Long userId);

}
