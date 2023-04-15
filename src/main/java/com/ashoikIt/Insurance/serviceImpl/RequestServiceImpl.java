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
import com.ashoikIt.Insurance.utils.ExcelGenerator;
import com.ashoikIt.Insurance.utils.PdfGenerator;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class RequestServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository planRepository;

	@Autowired
	private PdfGenerator pdfGenerator;

	@Autowired
	private ExcelGenerator excelGenerator;

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

		List<CitizenPlan> records = planRepository.findAll();
		excelGenerator.exportExcels(response, records);
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {

		List<CitizenPlan> rds = planRepository.findAll();
		pdfGenerator.exportPdfs(response, rds);
		return true;

	}

}
