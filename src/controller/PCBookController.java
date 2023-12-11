package controller;

import java.sql.Date;
import java.time.LocalDate;
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
			return "PC must be chosen";
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

	public String deleteBookData(Integer bookId) {
		PCBook booking = pcb.getPcBookedById(bookId);
		if (booking == null) 
			return "Booking doesn't exist";
		
		pcb.deleteBookData(bookId);
		return "Successfully deleted book data";
	}
	
	public List<PCBook> cancelBook(Date date) {
		if (date.before(new Date(System.currentTimeMillis())))
			return null;// di depan cek kalau null, berarti date salah "Chosen date must be before today"
		return getPcBookedByDate(date);
	}
	
	public List<PCBook> finishBookCheck(Date date){
		if (date.after(new Date(System.currentTimeMillis())))
			return null;
		return getPcBookedByDate(date);
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
			System.out.println("Masuk di if 1");
			return "There is no booking on the chosen date";
		} else {
			if (date.after(new Date(System.currentTimeMillis()))) {
				System.out.println("tanggal date: " +date);
				System.out.println("tanggal new date: " +new Date(System.currentTimeMillis()));
				System.out.println("Masuk di if 2");
				return "Chosen date must be before today";
			} else {
				for (PCBook booking : bookings) {
					System.out.println("Masuk di if 3");
					deleteBookData(booking.getBookId());
				}

				TransactionController tc = new TransactionController();
				System.out.println("==== Kalau user.getUserId:" +Page.user.getUserId());
				tc.addTransaction(0, bookings, Page.user.getUserId(), date);
				System.out.println("Masuk disini ga");
			}
		}

		return "Successfully finish booking";
	}

}
