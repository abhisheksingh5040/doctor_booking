package com.techno.doctorappointmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techno.doctorappointmentapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
