package com.ashoikIt.Insurance.dto;



import lombok.Data;

@Data
public class SearchRequest {

	private String planName;
	private String planStatus;
	private String gender;
	// private LocalDate startDate;// local date formate yyyy-MM-dd and html date
	// format dd-mm-yyy
	// private LocalDate endDate;// local date formate yyyy-MM-dd and html date
	// format dd-mm-yyy

	private String startDate;
	private String endDate;

}
