package com.techno.doctorappointmentapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.techno.doctorappointmentapp.enumeration.DoctorAvailabilityEnumeration;

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
@Table(name = "dba_doctor_details")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_id")
	private Long doctorId;
	@Column(name = "doctor_name", nullable = false, length = 50)
	private String doctorName;
	@Column(name = "doctor_speciality", nullable = false, length = 50)
	private String doctorSpeciality;
	@Column(name = "doctor_education", nullable = false, length = 50)
	private String doctorEducation;
	@Column(name = "doctor_average_rating")
	private Double doctorAverageRating;
	@Enumerated(EnumType.STRING)
	@Column(name = "doctor_availability")
	private DoctorAvailabilityEnumeration doctorAvailability;
	@Column(name = "is_delete")
	private Boolean isDelete;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="doctor_id")
    private List<DoctorRating> doctorRating;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "doctor")
    private List<DoctorsAppointment> doctorsAppointments;
	
	public Boolean getIsDelete() {
		return isDelete == null ? Boolean.FALSE : isDelete;
	}
}
