package com.techno.doctorappointmentapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techno.doctorappointmentapp.service.DoctorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {

	//private final DoctorService DoctorService;
}
