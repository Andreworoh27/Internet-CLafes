package controller;

import java.sql.Date;
import java.util.List;

import models.Report;

public class ReportController {
	
	private Report r = new Report();
	
	public String addNewReport(String userRole, String pcId, String reportNote) {
		PCController pcc = new PCController();
		if (!userRole.equals("Customer") && !userRole.equals("Operator")) 
			return "User role must be either customer or operator";
		else if (pcc.getPcDetail(pcId) == null)
			return "PC must be chosen and exist in database";
		else if (reportNote.isEmpty())
			return "Report note must be filled";
		
		r.addNewReport(userRole, pcId, reportNote, new Date(System.currentTimeMillis()));
		return "Successfully added a new report";
	}
	
	public List<Report> getAllReportData() {
		return r.getAllReportData();
	}

}
