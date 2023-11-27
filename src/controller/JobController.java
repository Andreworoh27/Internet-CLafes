package controller;

import java.util.List;

import models.Job;
import models.PC;

public class JobController {
	
	private Job j = new Job();
	
	public void addNewJob(Integer userId, Integer pcId) {
		
	}
	
	public String updateJobStatus(Integer jobId, String jobStatus) {
		if (!jobStatus.equals("Complete") && !jobStatus.equals("UnComplete"))
			return "Job status must be either complete or uncomplete";
		
		j.updateJobStatus(jobId, jobStatus);
		
		Job job = j.getJobById(jobId);
		PC pc = new PC().getPcDetail(job.getPcId());
		if (jobStatus.equals("Complete"))
			pc.updatePCCondition(job.getPcId(), "Usable");
		else if (jobStatus.equals("UnComplete"))
			pc.updatePCCondition(job.getPcId(), "Maintenance");
		
		return "Successfully update job status";
	}
	
	public void getPcOnWorkingList(Integer pcId) {
		j.getPcOnWorkingList(pcId);
	}
	
	public List<Job> getTechnicianJob(Integer userId) {
		return j.getTechnicianJob(userId);
	}
	
	public List<Job> getAllJobData() {
		return j.getAllJobData();
	}

}
