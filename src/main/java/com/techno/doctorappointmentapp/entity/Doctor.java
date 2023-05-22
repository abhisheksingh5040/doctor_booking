package com.techno.doctorappointmentapp.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

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
@Entity
@Table(name = "doctor_details")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_id")
	private Long doctorId;
	@Column(name = "doctor_name", nullable = false, length = 50)
	private String doctorName;
	@Column(name = "doctor_speciality", nullable = false, length = 50)
	private String doctorSpeciality;
	@Column(name = "doctor_phone_number", nullable = false)
	private Long doctorPhoneNumber;
	@Column(name = "doctor_email_id", nullable = false, length = 50)
	private String doctorEmailId;
	@Enumerated(EnumType.STRING)
	@Column(name = "doctor_sex", nullable = false)
	private GenderEnumaration doctorSex;
	@JsonProperty(access = Access.READ_ONLY)
	@Column(name = "doctor_average_rating")
	private Double doctorAverageRating;
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	@ElementCollection
	@CollectionTable(name = "doctor_rating_table", joinColumns = @JoinColumn(name = "doctor_id"))
	@Column(name = "doctor_rating")
	private List<Double> doctorRating;

	public Boolean getIsDeleted() {
		return isDeleted == null ? Boolean.FALSE : isDeleted;
	}
}
