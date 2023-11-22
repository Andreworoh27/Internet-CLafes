package models;

import java.sql.Date;
import java.util.List;

public class PCBook {
	
    private Integer bookId, pcId, userId;
    private Date bookDate;
    
    public PCBook() {}
    
	public PCBook(Integer bookId, Integer pcId, Integer userId, Date bookDate) {
		super();
		this.bookId = bookId;
		this.pcId = pcId;
		this.userId = userId;
		this.bookDate = bookDate;
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

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	
public void deleteBookData(Integer bookId) {
		
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
