package com.techno.doctorappointmentapp.pojo;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.techno.doctorappointmentapp.enumeration.DoctorAvailabilityEnumeration;

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
public class DoctorPojo implements Serializable{

	@JsonProperty(access = Access.READ_ONLY)
	private Long doctorId;
	private String doctorName;
	private String doctorSpeciality;
	private String doctorEducation;
	@JsonProperty(access = Access.READ_ONLY)
	private Double doctorAverageRating;
	@Enumerated(EnumType.STRING)
	private DoctorAvailabilityEnumeration doctorAvailability;
	private Boolean isDelete;

	public Boolean getIsDelete() {
		return isDelete == null ? Boolean.FALSE : isDelete;
	}

}
