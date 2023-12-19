package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import connection.Connect;

/*
 * Represent report entity in the system.
 * Encapsulate information related to a report. 
 * Interact with database to perform CRUD operations on report data.
 */

public class Report {
	
	private Connect db = Connect.getConnection();
	
    private Integer reportId;
    private String pcId, userRole, reportNotes;
    private Date reportDate;
    
	public Report() {}

	public Report(Integer reportId, String pcId, String userRole, String reportNotes, Date reportDate) {
		super();
		this.reportId = reportId;
		this.pcId = pcId;
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

	public String getPcId() {
		
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
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
	
	public void addNewReport(String userRole, String pcId, String reportNote, Date reportDate) {
		String query = "INSERT INTO Report VALUES (0, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, pcId);
			ps.setString(2, userRole);
			ps.setString(3, reportNote);
			ps.setDate(4, reportDate);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to add new report data");
			e.printStackTrace();
		}
	}
	
	public List<Report> getAllReportData() {
		String query = "SELECT * FROM Report";
		Vector<Report> reports = new Vector<>();
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer reportId = rs.getInt("ReportID");
				String pcId = rs.getString("PcID");
				String userRole = rs.getString("UserRole");
				String reportNote = rs.getString("ReportNote");
				Date reportDate = rs.getDate("ReportDate");
				reports.add(new Report(reportId, pcId, userRole, reportNote, reportDate));
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all report data");
			e.printStackTrace();
		}
		return reports;
	}
    
}
