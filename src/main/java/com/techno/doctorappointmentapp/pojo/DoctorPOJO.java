package com.techno.doctorappointmentapp.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DoctorPOJO {

	@JsonProperty(access = Access.READ_ONLY)
	private Long doctorId;
	private String doctorName;
	private String doctorSpeciality;
	private Long doctorPhoneNumber;
	private Boolean isDeleted;
	private List<Double> doctorRating;

}
