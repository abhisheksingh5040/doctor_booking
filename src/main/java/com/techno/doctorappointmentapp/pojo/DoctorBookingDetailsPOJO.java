package com.techno.doctorappointmentapp.pojo;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class DoctorBookingDetailsPOJO {

	@JsonProperty(access = Access.READ_ONLY)
	private Long doctorBookingDetailsId;
	private LocalDate localDate;
	private LocalTime localTime;
}
