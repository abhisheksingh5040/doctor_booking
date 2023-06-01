package com.techno.doctorappointmentapp.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class DoctorBookingDetailsPOJO implements Serializable {

	@JsonProperty(access = Access.READ_ONLY)
	private Long doctorBookingDetailsId;
	private LocalDate localDate;
	private LocalTime localTime;
	private String userName;
	private Long doctorIds;
	private Long userIds;
}
