package controller;

import java.sql.Date;
import java.util.List;

import models.PC;
import models.PCBook;
import view.Page;

/*
 * Manage PC book-related operations. 
 * Interact with the PCBook class, as well as TransactionController and PCController.
 */

public class PCBookController {

	private PCBook pcb = new PCBook();

	public List<PCBook> getPCBookedData(String pcId, Date date) {
		return pcb.getPCBookedData(pcId, date);
	}

	public List<PCBook> getAllPCBookedData() {
		return pcb.getAllPCBookedData();
	}

	public List<PCBook> getPcBookedByDate(Date date) {
		return pcb.getPcBookedByDate(date);
	}

	public PCBook getPCBookedById(Integer bookId) {
		return pcb.getPcBookedById(bookId);
	}
	
	public List<PCBook> getPCBookedDataByPcId(String pcId) {
		return pcb.getPCBookedDataByPcId(pcId);
	}
	
	public String addNewBook(String pcId, Integer userId, Date bookedDate) {
		PC pc = new PCController().getPcDetail(pcId);
		if (pc == null) {
			return "PC must be chosen";
		} else if (pc.getPcCondition().equals("Usable")) {
			Date todayDate = new Date(System.currentTimeMillis());
			if (bookedDate == null || !(bookedDate.equals(todayDate) || bookedDate.after(todayDate)))
				return "Date must be chosen between today or after";
			else if (getPCBookedData(pcId, bookedDate).size() > 0)
				return "PC is not available to be book on the chosen date";
		} else {
			return "PC is not available for use";
		}

		pcb.addNewBook(pcId, userId, bookedDate);
		return "Successfully book PC";
	}

	public String deleteBookData(Integer bookId) {
		PCBook booking = pcb.getPcBookedById(bookId);
		if (booking == null) 
			return "Booking doesn't exist";
		
		pcb.deleteBookData(bookId);
		return "Successfully deleted book data";
	}
	
	public List<PCBook> cancelBook(Date date) {
		if (date.before(new Date(System.currentTimeMillis())))
			return null;
		return getPcBookedByDate(date);
	}

	public String assignUsertoNewPc(Integer bookId, String newPcId) {
		PC pc = new PCController().getPcDetail(newPcId);
		PCBook booking = pcb.getPcBookedById(bookId);
		if (pc == null) {
			return "New PC must be chosen";
		} else if (pc.getPcCondition().equals("Usable")) {
			if (getPCBookedData(newPcId, booking.getBookDate()).size() > 0)
				return "PC is not available for the selected booking date";
		} else {
			return "PC is not available for use";
		}

		pcb.assignUsertoNewPc(bookId, newPcId);
		return "Successfully assigned user to a new PC";
	}

	public String finishBook(Date date) {
		List<PCBook> bookings = getPcBookedByDate(date);
		if (bookings.size() == 0) {
			return "There is no booking on the chosen date";
		} else {
			if (date.after(new Date(System.currentTimeMillis()))) {
				return "Chosen date must be before today";
			} else {
				for (PCBook booking : bookings) {
					deleteBookData(booking.getBookId());
				}

				TransactionController tc = new TransactionController();
				tc.addTransaction(0, bookings, Page.user.getUserId(), date);
			}
		}

		return "Successfully finish booking";
	}

}
