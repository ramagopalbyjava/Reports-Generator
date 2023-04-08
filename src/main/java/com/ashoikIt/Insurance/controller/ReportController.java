package com.ashoikIt.Insurance.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ashoikIt.Insurance.dto.SearchRequest;
import com.ashoikIt.Insurance.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/")
	public String getData() {
		return "index";
	}

	@GetMapping("/plans")
	public ResponseEntity<SearchRequest> getAllPlans() {
		reportService.getPlanName();
		return  new ResponseEntity<SearchRequest>(HttpStatus.OK);

	}
}
