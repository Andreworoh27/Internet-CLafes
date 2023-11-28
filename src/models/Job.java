package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import connection.Connect;

public class Job {

	private Connect db = Connect.getConnection();

	private Integer jobId, userId;
	private String pcId, jobStatus;

	public Job() {
	}

	public Job(Integer jobId, String pcId, Integer userId, String jobStatus) {
		super();
		this.jobId = jobId;
		this.pcId = pcId;
		this.userId = userId;
		this.jobStatus = jobStatus;
	}

	public Integer getjobId() {
		return jobId;
	}

	public void setjobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public void addNewJob(Integer userId, String pcId) {
		String query = "INSERT INTO Job VALUES (0, ?, ?, ?)";
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setInt(1, userId);
			ps.setString(2, pcId);
			ps.setString(3, "UnComplete");
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to add new job data");
			e.printStackTrace();
		}
	}

	public void updateJobStatus(Integer jobId, String jobStatus) {
		String query = "UPDATE Job SET JobStatus = ? WHERE JobID = ?";
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setString(1, jobStatus);
			ps.setInt(2, jobId);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to update job status");
			e.printStackTrace();
		}
	}

	public void getPcOnWorkingList(Integer pcId) {
		String query = "UPDATE Job j JOIN PC pc SET pc.PCCondition = ? WHERE j.PcID = ?";
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setString(1, "Maintenance");
			ps.setInt(2, pcId);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to get PC on working list");
			e.printStackTrace();
		}
	}

	public List<Job> getTechnicianJob(Integer userId) {
		String query = "SELECT * FROM Job WHERE UserID = ?";
		Vector<Job> jobs = new Vector<>();
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer jobId = rs.getInt("JobID");
				String pcId = rs.getString("PcID");
				String jobStatus = rs.getString("JobStatus");
				jobs.add(new Job(jobId, pcId, userId, jobStatus));
			}
		} catch (SQLException e) {
			System.out.println("Failed to get technician job");
			e.printStackTrace();
		}
		return jobs;
	}

	public List<Job> getAllJobData() {
		String query = "SELECT * FROM Job";
		Vector<Job> jobs = new Vector<>();
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer jobId = rs.getInt("JobID");
				String pcId = rs.getString("PcID");
				Integer userId = rs.getInt("UserID");
				String jobStatus = rs.getString("JobStatus");
				jobs.add(new Job(jobId, pcId, userId, jobStatus));
			}
		} catch (SQLException e) {
			System.out.println("Failed to get all job data");
			e.printStackTrace();
		}
		return jobs;
	}

	public Job getJobById(Integer jobId) {
		String query = "SELECT * FROM Job WHERE JobID = ?";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, jobId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer userId = rs.getInt("UserID");
				String pcId = rs.getString("PcID");
				String jobStatus = rs.getString("JobStatus");
				return new Job(jobId, pcId, userId, jobStatus);
			}
		} catch (SQLException e) {
			System.out.println("Failed to get job");
			e.printStackTrace();
		}
		return null;
	}

	public Job getJobByPcId(String pcId) {
		String query = "SELECT * FROM Job WHERE PcID = ? AND JobStatus = ?";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, pcId);
			ps.setString(2, "UnComplete");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer jobId = rs.getInt("JobID");
				Integer userId = rs.getInt("UserID");
				String jobStatus = rs.getString("JobStatus");
				return new Job(jobId, pcId, userId, jobStatus);
			}
		} catch (SQLException e) {
			System.out.println("Failed to get job");
			e.printStackTrace();
		}
		return null;
	}

}
