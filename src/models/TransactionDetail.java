package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import connection.Connect;
import controller.UserController;

public class TransactionDetail {
	
	private Connect db = Connect.getConnection();
	
    private Integer transactionId, customerId;
    private String pcId, customerName;
    private Date bookedTime;

    public TransactionDetail() {}

	public TransactionDetail(Integer transactionId, String pcId, Integer customerId, String customerName,
			Date bookedTime) {
		super();
		this.transactionId = transactionId;
		this.pcId = pcId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.bookedTime = bookedTime;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionID) {
		this.transactionId = transactionID;
	}

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getBookedTime() {
		return bookedTime;
	}

	public void setBookedTime(Date bookedTime) {
		this.bookedTime = bookedTime;
	}
	
	public List<TransactionDetail> getUserTransactionDetail(Integer userId) {
		String query = "SELECT * FROM TransactionDetail WHERE CustomerID = ?";
		
		Vector<TransactionDetail> tds = new Vector<>();
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer transactionId = rs.getInt("TransactionID");
				String pcId = rs.getString("PcID");
				Integer customerId = rs.getInt("CustomerID");
			    String customerName = rs.getString("CustomerName");
			    Date bookedTime = rs.getDate("BookedTime");
			    tds.add(new TransactionDetail(transactionId, pcId, customerId, customerName, bookedTime));  
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch user transaction detail data");
			e.printStackTrace();
		}
		
		return tds;
	}
	
	public List<TransactionDetail> getAllTransactionDetail(Integer transactionId) {
		String query = "SELECT * FROM TransactionDetail WHERE TransactionID = ?";
		
		Vector<TransactionDetail> tds = new Vector<>();
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, transactionId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String pcId = rs.getString("PcID");
				Integer customerId = rs.getInt("CustomerID");
				String customerName = rs.getString("CustomerName");
			    Date bookedTime = rs.getDate("BookedTime");
			    tds.add(new TransactionDetail(transactionId, pcId, customerId, customerName, bookedTime)); 
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch transaction detail data");
			e.printStackTrace();
		}
		
		return tds;
	}
	
	public void addTransactionDetail(Integer transactionId, List<PCBook> pcsBooked) {
		String query = "INSERT INTO TransactionDetail VALUES ";
		int size = pcsBooked.size();
		for (int i = 0; i < size - 1; i++) {
			query += "(?, ?, ?, ?, ?),\n";
		}
		query += "(?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = db.prepareStatement(query);
			UserController uc = new UserController();
			int i = 1;
			for (PCBook pcBook : pcsBooked) {
				ps.setInt(i++, transactionId);
				ps.setInt(i++, pcBook.getUserId());
				ps.setString(i++, uc.getUserDataById(pcBook.getUserId()).getUsername());
				ps.setString(i++, pcBook.getPcId());
				ps.setDate(i++, pcBook.getBookDate());
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to add new transaction detail data");
			e.printStackTrace();
		}
		
//		VERSI TIDAK PAKAI LIST
//		String query = "INSERT INTO TransactionDetail VALUES (?, ?, ?, ?, ?)";
//		
//		try {
//			PreparedStatement ps = db.prepareStatement(query);
//			UserController uc = new UserController();
//			ps.setInt(1, transactionId);
//			ps.setInt(2, pcBooked.getUserId());
//			ps.setString(3, uc.getUserDataById(pcBooked.getUserId()).getUsername());
//			ps.setString(4, pcBooked.getPcId());
//			ps.setDate(5, pcBooked.getBookDate());
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			System.out.println("Failed to add new transaction detail data");
//			e.printStackTrace();
//		}
	}

}
