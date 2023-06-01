package com.techno.doctorappointmentapp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
@Table(name = "dba_user_details")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "user_name", nullable = false, length = 50)
	private String userName;
	@Column(name = "user_age", nullable = false, length = 10)
	private Integer userAge;
	@Column(name = "user_email_id", nullable = false, length = 50)
	private String userEmailId;
	@Column(name = "user_password", nullable = false, length = 50)
	private String userPassword;
	@Enumerated(EnumType.STRING)
	@Column(name = "user_sex", nullable = false)
	private GenderEnumaration userSex;
	@Column(name = "user_phone_number", nullable = false)
	private Long userPhoneNumber;
	@Column(name = "is_delete")
	private Boolean isDelete;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Doctor doctor;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "users")
	private Set<Roles> roles;
}
