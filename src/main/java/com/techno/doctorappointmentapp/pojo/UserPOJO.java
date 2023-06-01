package com.techno.doctorappointmentapp.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
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
public class UserPOJO implements Serializable{

	@JsonProperty(access = Access.READ_ONLY)
	private Long userId;
	private String userName;
	private Integer userAge;
	private String userEmailId;
	private String userPassword;
	@Enumerated(EnumType.STRING)
	private GenderEnumaration userSex;
	private Long userPhoneNumber;
	private Boolean isDelete;
	private Set<Long> roleId;
	private DoctorPOJO doctor;

	public Boolean getIsDelete() {
		return isDelete == null ? Boolean.FALSE : isDelete;
	}

}
