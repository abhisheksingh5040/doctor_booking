package com.techno.doctorappointmentapp.pojo;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.techno.doctorappointmentapp.enumeration.DoctorAvailabilityEnumeration;
import com.techno.doctorappointmentapp.enumeration.GenderEnumaration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(value = Include.NON_DEFAULT)
public class DoctorsPojo implements Serializable {

	private Long doctorId;
	private String doctorName;
	private String doctorSpeciality;
	private String doctorEducation;
	private Double doctorAverageRating;
	@Enumerated(EnumType.STRING)
	private DoctorAvailabilityEnumeration doctorAvailability;
	private String userAge;
	private String userEmailId;
	private GenderEnumaration userSex;
	private Long userPhoneNumber;
}
