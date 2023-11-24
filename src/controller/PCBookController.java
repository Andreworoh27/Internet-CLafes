package controller;
import java.sql.Date;
import java.util.List;

import models.PCBook;

public class PCBookController {
	
	private PCBook pcb = new PCBook();
	
	public void deleteBookData(Integer bookId) {
		pcb.deleteBookData(bookId);
	}
	
	public List<PCBook> getPCBookedData(Integer pcId, Date date) {
		return null;
	}
	
	public void assignUsertoNewPc(Integer bookId, Integer newPcId) {
		
	}
	
	public void addNewBook(Integer pcId, Integer userId, Date bookedDate) {
		
	}
	
	public void finishBook(List<PCBook> pcsBooked) {
		
	}
	
	public List<PCBook> getAllPCBookedData() {
		return null;
	}
	
	public List<PCBook> getPcBookedByDate(Date date) {
		return null;
	}

}
