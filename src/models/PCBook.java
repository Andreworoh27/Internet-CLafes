package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import connection.Connect;

public class PCBook {
	
	private Connect db = Connect.getConnection();
	
    private Integer bookId, userId;
    private String pcId;
    private Date bookDate;
    
    public PCBook() {}
    
	public PCBook(Integer bookId, String pcId, Integer userId, Date bookDate) {
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

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
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
	
	public void addNewBook(String pcId, Integer userId, Date bookedDate) {
		String query = "INSERT INTO PCBook VALUES (0, ?, ?, ?)";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, pcId);
			ps.setInt(2, userId);
			ps.setDate(3, bookedDate);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to add new book data");
			e.printStackTrace();
		}
	}
	
	public void deleteBookData(Integer bookId) {
		String query = "DELETE FROM PCBook WHERE BookID = ?";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, bookId);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to delete book data");
			e.printStackTrace();
		}
	}
	
	public void assignUsertoNewPc(Integer bookId, String newPcId) {
		String query = "UPDATE PCBook SET PcID = ? WHERE BookID = ?";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, newPcId);
			ps.setInt(2, bookId);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to change booked pc data");
			e.printStackTrace();
		}
	}
	
	public void finishBook(List<PCBook> pcsBooked) {
		
	}
	
	public List<PCBook> getPCBookedData(String pcId, Date date) {
		String query = "SELECT * FROM PCBook WHERE PcID = ? AND BookDate = ?";
		Vector<PCBook> bookings = new Vector<>();
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, pcId);
			ps.setDate(2, date);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer bookId = rs.getInt("BookID");
				Integer userId = rs.getInt("UserID");
				bookings.add(new PCBook(bookId, pcId, userId, date));
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch PC booked data");
			e.printStackTrace();
		}
		
		return bookings;
	}
	
	
	public List<PCBook> getAllPCBookedData() {
		String query = "SELECT * FROM PCBook";
		Vector<PCBook> bookings = new Vector<>();
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer bookId = rs.getInt("BookID");
				Integer userId = rs.getInt("UserID");
				String pcId = rs.getString("PcID");
				Date bookDate = rs.getDate("BookDate");
				bookings.add(new PCBook(bookId, pcId, userId, bookDate));
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch PC booked data");
			e.printStackTrace();
		}
		
		return bookings;
	}
	
	public List<PCBook> getPcBookedByDate(Date date) {
		String query = "SELECT * FROM PCBook WHERE BookDate = ?";
		Vector<PCBook> bookings = new Vector<>();
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setDate(1, date);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer bookId = rs.getInt("BookID");
				Integer userId = rs.getInt("UserID");
				String pcId = rs.getString("PcID");
				bookings.add(new PCBook(bookId, pcId, userId, date));
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch PC booked data");
			e.printStackTrace();
		}
		
		return bookings;
	}
	
	public PCBook getPcBookedById(Integer bookId) {
		String query = "SELECT * FROM PCBook WHERE BookID = ?";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, bookId);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer userId = rs.getInt("UserID");
				String pcId = rs.getString("PcID");
				Date bookDate = rs.getDate("BookDate");
				return new PCBook(bookId, pcId, userId, bookDate);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch PC booked data");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<PCBook> getPCBookedDataByPcId(String pcId) {
		String query = "SELECT * FROM PCBook WHERE PcId = ?";
		Vector<PCBook> bookings = new Vector<>();
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, pcId);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer bookId = rs.getInt("BookID");
				Integer userId = rs.getInt("UserID");
				Date bookDate = rs.getDate("BookDate");
				bookings.add(new PCBook(bookId, pcId, userId, bookDate));
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch PC booked data");
			e.printStackTrace();
		}
		
		return bookings;
	}
    
}
