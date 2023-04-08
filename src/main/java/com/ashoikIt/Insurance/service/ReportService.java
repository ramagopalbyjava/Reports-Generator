package com.ashoikIt.Insurance.service;

import java.util.List;

import com.ashoikIt.Insurance.dto.SearchRequest;
import com.ashoikIt.Insurance.entity.CitizenPlan;

public interface ReportService {

	public List<String> getPlanName();

	public List<String> getPlanStatus();

	public List<CitizenPlan> search(SearchRequest request);

	public boolean exportExcel();

	public boolean exportPdf();

}
