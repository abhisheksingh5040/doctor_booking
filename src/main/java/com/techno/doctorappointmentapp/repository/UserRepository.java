package com.techno.doctorappointmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techno.doctorappointmentapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
