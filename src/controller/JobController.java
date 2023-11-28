package controller;

import java.util.List;

import models.Job;
import models.PC;
import models.User;

public class JobController {
	
	private Job j = new Job();
	
	public void getPcOnWorkingList(Integer pcId) {
		j.getPcOnWorkingList(pcId);
	}
	
	public List<Job> getTechnicianJob(Integer userId) {
		return j.getTechnicianJob(userId);
	}
	
	public List<Job> getAllJobData() {
		return j.getAllJobData();
	}
	
	public String addNewJob(Integer userId, String pcId) {
		UserController uc = new UserController();
		User user = uc.getUserDataById(userId);
		if (user == null)
			return "User must be chosen";
		if (!user.getUserRole().equals("Computer Technician"))
			return "User must be a Computer Technician";
		
		PCController pcc = new PCController();
		PC pc = pcc.getPcDetail(pcId);
		if (pc == null)
			return "PC must be chosen and exist in database";
		if (j.getJobByPcId(pcId) != null)
			return "Another technician is doing this job";
		j.addNewJob(userId, pcId);
		return "Successfully added a new job";
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

}
