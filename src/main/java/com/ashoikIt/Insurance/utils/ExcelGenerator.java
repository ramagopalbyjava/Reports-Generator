package com.ashoikIt.Insurance.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.ashoikIt.Insurance.entity.CitizenPlan;

@Component
public class ExcelGenerator {

	public boolean exportExcels(HttpServletResponse response, List<CitizenPlan> records,File f) throws Exception {
		Workbook workBook = new HSSFWorkbook();

		Sheet sheet = workBook.createSheet();

		Row headRow = sheet.createRow(0);

		headRow.createCell(0).setCellValue("ID");
		headRow.createCell(1).setCellValue("Citizen Name");
		headRow.createCell(2).setCellValue("Citizen Plan Name");
		headRow.createCell(3).setCellValue("Citizen Plan Status");
		headRow.createCell(4).setCellValue("Citizen Start Date");
		headRow.createCell(5).setCellValue("Citizen End Date");

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
		
		
		  FileOutputStream fos = new FileOutputStream(f); workBook.write(fos);
		  fos.close(); workBook.close();
		 
		
		ServletOutputStream outputStream = response.getOutputStream();
		workBook.write(outputStream);
		workBook.close();
		return true;
	}
}
