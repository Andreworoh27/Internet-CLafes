package models;

import java.sql.Date;
import java.util.List;

public class Report {
	
    private Integer reportId, pcId, userId;
    private String userRole, reportNotes;
    private Date reportDate;
    
	public Report() {}

	public Report(Integer reportId, Integer pcId, Integer userId, String userRole, String reportNotes,
			Date reportDate) {
		super();
		this.reportId = reportId;
		this.pcId = pcId;
		this.userId = userId;
		this.userRole = userRole;
		this.reportNotes = reportNotes;
		this.reportDate = reportDate;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getPcId() {
		return pcId;
	}

	public void setPcId(Integer pcId) {
		this.pcId = pcId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getReportNotes() {
		return reportNotes;
	}

	public void setReportNotes(String reportNotes) {
		this.reportNotes = reportNotes;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	
	public void addNewReport(String userRole, Integer pcId, String reportNote) {
		
	}
	
	public List<Report> getAllReportData() {
		return null;
	}
    
}
