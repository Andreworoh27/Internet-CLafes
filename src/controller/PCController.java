package controller;
import java.util.Date;
import java.util.List;

import models.PC;
import models.PCBook;

public class PCController {
	
	private PC pc = new PC();
	
	public List<PC> getAllPCData() {
		return pc.getAllPCData();
	}
	
	public PC getPcDetail(String pcId) {
		return pc.getPcDetail(pcId);
	}
	
	public String addNewPC(String pcId) {
		if (pcId.isEmpty())
			return "PC ID must not be empty";
		else if (getPcDetail(pcId) != null)
			return "PC ID must be unique";
		
		pc.addNewPC(pcId);
		return "Successfully add a new PC";
	}
	
	public String updatePCCondition(String pcId, String condition) {
		if (getPcDetail(pcId) == null)
			return "PC must be chosen";
		else if (!condition.equals("Usable") && !condition.equals("Maintenance") && !condition.equals("Broken"))
			return "PC condition must be either Usable, Maintenance, or Broken";
		
		pc.updatePCCondition(pcId, condition);
		return "Successfully update PC condition";
	}
	
	public Boolean isBooked(List<PCBook> bookings) {
		for (PCBook pcBook : bookings) {
			if (pcBook.getBookDate().after(new Date(System.currentTimeMillis())))
				return true;
			
			if (pcBook.getBookDate().equals(new Date(System.currentTimeMillis())))
				return true;
		}
		return false;
	}
	
	public String deletePC(String pcId) {
		PCBookController pcb = new PCBookController();
		if (getPcDetail(pcId) == null)
			return "PC must be chosen";
		else if (isBooked(pcb.getPCBookedDataByPcId(pcId)))
			return "PC is booked in the future";
		
		pc.deletePC(pcId);
		return "Successfully delete PC";
	}

}
