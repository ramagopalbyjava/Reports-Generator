package com.ashoikIt.Insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ashoikIt.Insurance.dto.SearchRequest;
import com.ashoikIt.Insurance.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/")
	public String indexPage(Model model) {
		//iif you want bing sReauest to ui used Model
		SearchRequest sReguest = new SearchRequest();
		model.addAttribute("search", sReguest);
		return "index";
	}

	@GetMapping("/plans")
	public ResponseEntity<SearchRequest> getAllPlans() {
		reportService.getPlanName();
		return new ResponseEntity<SearchRequest>(HttpStatus.OK);

	}
}
