package controller;
import java.sql.Date;
import java.util.List;

import models.PCBook;
import view.Page;

public class PCBookController {
	
	private PCBook pcb = new PCBook();
	
	public void deleteBookData(Integer bookId) {
		pcb.deleteBookData(bookId);
	}
	
	public List<PCBook> getPCBookedData(String pcId, Date date) {
		return pcb.getPCBookedData(pcId, date);
	}
	
	public void assignUsertoNewPc(Integer bookId, Integer newPcId) {
		
	}
	
	public void addNewBook(Integer pcId, Integer userId, Date bookedDate) {
		
	}
	
	public String finishBook(Date date) {
		List<PCBook> bookings = getPcBookedByDate(date);
		if (bookings.size() == 0) {
			return "There is no booking on the chosen date";
		} else {
			if (date.before(new Date(System.currentTimeMillis()))) {
				return "Chosen date must be before today";
			} else {
				for (PCBook booking : bookings) {
					deleteBookData(booking.getBookId());
				}
				
				TransactionController tc = new TransactionController();
				tc.addTransaction(0, bookings, Page.user.getUserId(), date);
			}
		}
		
		return "";
	}
	
	public List<PCBook> getAllPCBookedData() {
		return pcb.getAllPCBookedData();
	}
	
	public List<PCBook> getPcBookedByDate(Date date) {
		return pcb.getPcBookedByDate(date);
	}

}
