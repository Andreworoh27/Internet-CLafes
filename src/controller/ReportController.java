package controller;

import java.sql.Date;
import java.util.List;

import models.Report;

/*
 * Manage report-related operations. 
 * Interact with the Report class, as well as PCController.
 */

public class ReportController {
	
	private Report r = new Report();
	
	public List<Report> getAllReportData() {
		return r.getAllReportData();
	}
	
	public String addNewReport(String userRole, String pcId, String reportNote) {
		PCController pcc = new PCController();
		if (!userRole.equals("Customer") && !userRole.equals("Operator")) 
			return "Only Customer or Operator that can make a report";
		else if (pcc.getPcDetail(pcId) == null)
			return "PC must be chosen and exist in database";
		else if (reportNote.isEmpty())
			return "Report note must be filled";
		
		r.addNewReport(userRole, pcId, reportNote, new Date(System.currentTimeMillis()));
		return "Successfully added a new report";
	}

}
