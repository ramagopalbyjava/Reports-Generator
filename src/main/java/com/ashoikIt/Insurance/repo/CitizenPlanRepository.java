package com.ashoikIt.Insurance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashoikIt.Insurance.entity.CitizenPlan;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Long> {

	@Query("select distinct (citizenPlanName) from CitizenPlan")
	public List<String> getCitizenPlanName();

	@Query("select distinct (citizenPlanStatus) from CitizenPlan")
	public List<String> getCitizenPlaNStatus();
}
