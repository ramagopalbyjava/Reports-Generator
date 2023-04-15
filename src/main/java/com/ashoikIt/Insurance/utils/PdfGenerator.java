package com.ashoikIt.Insurance.utils;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.ashoikIt.Insurance.entity.CitizenPlan;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PdfGenerator {

	public boolean exportPdfs(HttpServletResponse response, List<CitizenPlan> rds) throws Exception {

		Document d = new Document();

		PdfWriter.getInstance(d, response.getOutputStream());
		d.open();
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		Paragraph p = new Paragraph("Citizen Plans Info", fontTiltle);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		d.add(p);

		PdfPTable t = new PdfPTable(6);

		t.addCell("ID");
		t.addCell("Citizen Name");
		t.addCell("Citizen PlanName");
		t.addCell("Citizen PlanStatus");
		t.addCell("Citizen PlanStartDate");
		t.addCell("Citizen PlanEndDate");

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
