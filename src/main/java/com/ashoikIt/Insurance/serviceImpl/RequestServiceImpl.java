package com.ashoikIt.Insurance.serviceImpl;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashoikIt.Insurance.dto.SearchRequest;
import com.ashoikIt.Insurance.entity.CitizenPlan;
import com.ashoikIt.Insurance.repo.CitizenPlanRepository;
import com.ashoikIt.Insurance.service.ReportService;
import com.ashoikIt.Insurance.utils.EmailUtils;
import com.ashoikIt.Insurance.utils.ExcelGenerator;
import com.ashoikIt.Insurance.utils.PdfGenerator;

@Service
public class RequestServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository planRepository;

	@Autowired
	private PdfGenerator pdfGenerator;

	@Autowired
	private ExcelGenerator excelGenerator;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public List<String> getPlanName() {

		return planRepository.getCitizenPlanName();
	}

	@Override
	public List<String> getPlanStatus() {

		return planRepository.getCitizenPlaNStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		// filter applying
		// using Dynamic Query using of Example.of()

		CitizenPlan entity = new CitizenPlan();

		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setCitizenPlanName(request.getPlanName());
		}
		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setCitizenPlanStatus(request.getPlanStatus());
		}

		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setCitizenGeneder(request.getGender());
		}

		if (request.getStartDate() != null && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setCitizenPlanStartDate(localDate);

		}

		if (request.getEndDate() != null && !"".equals(request.getEndDate())) {
			String endDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(endDate, formatter);
			entity.setCitizenPlanEndDate(localDate);

		}
		return planRepository.findAll(Example.of(entity));
		// Dates will not work because bugs there means diff formates
		// Remaining will works three plan names ,plan status and gender

	}

	@Override
	public void exportExcel(HttpServletResponse response) throws Exception {

		List<CitizenPlan> records = planRepository.findAll();
		File file = new File("plans.xls");
		excelGenerator.exportExcels(response, records,file);
		String subject = "Citizen-Plan-Details";
		String body = "<h1>Below the Excel File is attached</h1>";
		String to = "swetharam389@gmail.com";
		emailUtils.sendMail(subject, body, to,file);
		file.delete();

	}

	@Override
	public void exportPdf(HttpServletResponse response) throws Exception {
		List<CitizenPlan> rds = planRepository.findAll();

		File file = new File("plans.pdf");
		pdfGenerator.exportPdfs(response, rds,file);
		String subject = "Citizen-Plan-Details";
		String body = "<h1>Below the Pdf File is attached</h1>";
		String to = "swetharam389@gmail.com";
		emailUtils.sendMail(subject, body, to,file);
		file.delete();
	}

}
