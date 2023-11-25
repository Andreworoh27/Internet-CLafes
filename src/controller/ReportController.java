package controller;

import java.sql.Date;
import java.util.List;

import models.Report;

public class ReportController {
	
	private Report r = new Report();
	
	public String addNewReport(String userRole, Integer pcId, String reportNote) {
		PCController pcc = new PCController();
		String errorMessage = "";
		if (!userRole.equals("Customer") && !userRole.equals("Operator")) 
			errorMessage = "User role must be either customer or operator";
		else if (pcc.getPcDetail(errorMessage) == null)
			errorMessage = "PC must be chosen and exist in database";
		else if (reportNote.isEmpty())
			errorMessage = "Report note must be filled";
		
		if (errorMessage.isEmpty()) r.addNewReport(userRole, errorMessage, reportNote, new Date(System.currentTimeMillis()));
		
		return errorMessage;
	}
	
	public List<Report> getAllReportData() {
		return r.getAllReportData();
	}

}
