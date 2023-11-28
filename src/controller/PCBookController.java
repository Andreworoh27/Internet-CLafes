package controller;

import java.sql.Date;
import java.util.List;

import models.PC;
import models.PCBook;
import view.Page;

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
		PC pc = new PC().getPcDetail(pcId);
		if (pc == null) {
			return "You must choose a PC to be booked";
		} else if (pc.getPcCondition().equals("Usable")) {
			Date todayDate = new Date(System.currentTimeMillis());
			if (!(bookedDate.equals(todayDate) || bookedDate.after(todayDate)))
				return "Date must be chosen between today or after";
			else if (getPCBookedData(pcId, bookedDate).size() > 0)
				return "PC is not available to be book on the chosen date";
		} else {
			return "PC is not available for use";
		}

		pcb.addNewBook(pcId, userId, bookedDate);
		return "Successfully book PC";
	}

	// kalau cancel, source = "Cancel"
	// kalau finish, source = "Finish"
	public String deleteBookData(Integer bookId, String source) {
		if (source.equals("Cancel")) {
			PCBook booking = pcb.getPcBookedById(bookId);
			System.out.println(booking.getBookDate());
			if (booking == null) {
				System.out.println("masuk ke if");
				return "Booking doesn't exist";
			} else {
				if (booking.getBookDate().before(new Date(System.currentTimeMillis()))) {
					System.out.println("masuk ke if dalem");
					return "Chosen date must be before today";
				} else {
					System.out.println("masuk ke else dalem");
					pcb.deleteBookData(bookId);
				}
			}
		} else {
			System.out.println("masuk ke else luar");
			pcb.deleteBookData(bookId);
		}
		
		return "";
	}

	public String assignUsertoNewPc(Integer bookId, String newPcId) {
		PC pc = new PC().getPcDetail(newPcId);
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
			if (date.before(new Date(System.currentTimeMillis()))) {
				return "Chosen date must be before today";
			} else {
				for (PCBook booking : bookings) {
					deleteBookData(booking.getBookId(), "Finish");
				}

				TransactionController tc = new TransactionController();
				tc.addTransaction(0, bookings, Page.user.getUserId(), date);
			}
		}

		return "Successfully finish booking";
	}

}
