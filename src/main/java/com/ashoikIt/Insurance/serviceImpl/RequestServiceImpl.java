package com.ashoikIt.Insurance.serviceImpl;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashoikIt.Insurance.dto.SearchRequest;
import com.ashoikIt.Insurance.entity.CitizenPlan;
import com.ashoikIt.Insurance.repo.CitizenPlanRepository;
import com.ashoikIt.Insurance.service.ReportService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class RequestServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository planRepository;

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
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		Workbook workBook = new HSSFWorkbook();

		Sheet sheet = workBook.createSheet();

		Row headRow = sheet.createRow(0);

		headRow.createCell(0).setCellValue("ID");
		headRow.createCell(1).setCellValue("Citizen Name");
		headRow.createCell(2).setCellValue("Citizen Plan Name");
		headRow.createCell(3).setCellValue("Citizen Plan Status");
		headRow.createCell(4).setCellValue("Citizen Start Date");
		headRow.createCell(5).setCellValue("Citizen End Date");

		List<CitizenPlan> records = planRepository.findAll();
		Integer data = 1;
		for (CitizenPlan plans : records) {
			Row row = sheet.createRow(data);
			row.createCell(0).setCellValue(plans.getCitizenId());
			row.createCell(1).setCellValue(plans.getCitizenName());
			row.createCell(2).setCellValue(plans.getCitizenPlanName());
			row.createCell(3).setCellValue(plans.getCitizenPlanStatus());
			row.createCell(4).setCellValue(plans.getCitizenPlanStartDate() + " ");
			row.createCell(5).setCellValue(plans.getCitizenPlanEndDate() + " ");
			data++;
		}
		ServletOutputStream outputStream = response.getOutputStream();
		workBook.write(outputStream);
		workBook.close();
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {

		Document d = new Document();

		PdfWriter.getInstance(d, response.getOutputStream());
		d.open();

		Paragraph p = new Paragraph("Citizen Plans Info");
		d.add(p);
		PdfPTable t = new PdfPTable(6);

		t.addCell("ID");
		t.addCell("Citizen Name");
		t.addCell("Citizen PlanName");
		t.addCell("Citizen PlanStatus");
		t.addCell("Citizen PlanStartDate");
		t.addCell("Citizen PlanEndDate");

		List<CitizenPlan> rds = planRepository.findAll();

		for (CitizenPlan plans : rds) {
			t.addCell(String.valueOf(plans.getCitizenId()));
			t.addCell(plans.getCitizenName());
			t.addCell(plans.getCitizenPlanName());
			t.addCell(plans.getCitizenPlanStatus());
			t.addCell(plans.getCitizenPlanStartDate() + "");
			t.addCell(plans.getCitizenPlanEndDate() + " ");
		}
		d.add(t);
		d.close();

		return true;

	}

}
