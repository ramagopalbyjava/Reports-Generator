package com.ashoikIt.Insurance.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CITIZEN_PLANS_INFO")
public class CitizenPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long citizenId;
	private String citizenName;
	private String citizenGeneder;
	private String citizenPlanName;
	private String citizenPlanStatus;
	private LocalDate citizenPlanStartDate;
	private LocalDate citizenPlanEndDate;
	private double citizenBenefitAmount;
	private String citizenDenialReason;
	private LocalDate citizenTerminatedDate;
	private String citizenTerminationReson;

}
