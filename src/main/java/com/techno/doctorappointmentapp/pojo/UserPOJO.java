package com.techno.doctorappointmentapp.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.techno.doctorappointmentapp.enumeration.GenderEnumaration;

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
public class UserPOJO {

	@JsonProperty(access = Access.READ_ONLY)
	private Long userId;
	private String userName;
	private String userAge;
	private String userEmailId;
	private GenderEnumaration userSex;
	private Long userPhoneNumber;
	private Boolean isDeleted;
	
}
