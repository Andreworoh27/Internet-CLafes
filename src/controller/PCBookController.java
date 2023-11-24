package controller;
import java.sql.Date;
import java.util.List;

import models.PCBook;

public class PCBookController {
	
	private PCBook pcb = new PCBook();
	private static PCBookController pbc = null;
	
	private PCBookController() {}
	
	public static PCBookController getInstance() {
		if (pbc == null) pbc = new PCBookController();
		return pbc;
	}
	
	public void deleteBookData(Integer bookId) {
		pbc.deleteBookData(bookId);
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
