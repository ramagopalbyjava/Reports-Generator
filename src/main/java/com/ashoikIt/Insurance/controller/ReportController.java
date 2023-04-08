package com.ashoikIt.Insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashoikIt.Insurance.dto.SearchRequest;
import com.ashoikIt.Insurance.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/")
	public String indexPage(Model model) {
		// iif you want bing sReauest to ui used Model
		// SearchRequest searchObj = new SearchRequest();
		// model.addAttribute("search", searchObj);
		// (Or)
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "index";
	}

	private void init(Model model) {
		model.addAttribute("planNames", reportService.getPlanName());
		model.addAttribute("planStatus", reportService.getPlanStatus());
	}

	@PostMapping("/search")
	public String handleSearchRequest(SearchRequest request, Model model) {
		System.out.println(request);
		init(model);
		return "index";

	}
}
