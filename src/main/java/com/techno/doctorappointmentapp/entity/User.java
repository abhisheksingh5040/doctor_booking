package com.techno.doctorappointmentapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name="user_details")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;
	@Column(name="user_name",nullable = false,length = 50)
	private String userName;
	@Column(name="user_age",nullable = false,length = 10)
	private String userAge;
	@Column(name="user_email_id",nullable = false,length = 50)
	private String userEmailId;
	@Enumerated(EnumType.STRING)
	@Column(name="user_sex",nullable = false)
	private GenderEnumaration userSex;
	@Column(name="user_phone_number",nullable = false)
	private Long userPhoneNumber;
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	public Boolean getIsDeleted() {
		return isDeleted == null ? Boolean.FALSE : Boolean.TRUE;
	}
}
