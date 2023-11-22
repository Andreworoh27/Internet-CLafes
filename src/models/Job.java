package models;

import java.util.List;

public class Job {
	
	private Integer bookId, pcId, userId;
	private String jobStatus;
	
	public Job() {}
	
	public Job(Integer bookId, Integer pcId, Integer userId, String jobStatus) {
		super();
		this.bookId = bookId;
		this.pcId = pcId;
		this.userId = userId;
		this.jobStatus = jobStatus;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
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

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	public void addNewJob(Integer userId, Integer pcId) {
		
	}
	
	public void updateJobStatus(Integer jobId, String jobStatus) {
		
	}
	
	public void getPcOnWorkingList(Integer pcId) {
		
	}
	
	public Job getTechnicianJob(Integer userId) {
		return null;
	}
	
	public List<Job> getAllJobData() {
		return null;
	}

}
