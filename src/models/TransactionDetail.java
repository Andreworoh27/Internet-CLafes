package models;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import connection.Connect;
import controller.UserController;

public class TransactionDetail {
	
	private Connect db = Connect.getConnection();
	
    private Integer transactionID, pcId, customerId;
    private String customerName;
    private Timestamp bookedTime;

    public TransactionDetail() {}

	public TransactionDetail(Integer transactionID, Integer pcId, Integer customerId, String customerName,
			Timestamp bookedTime) {
		super();
		this.transactionID = transactionID;
		this.pcId = pcId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.bookedTime = bookedTime;
	}

	public Integer getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}

	public Integer getPcId() {
		return pcId;
	}

	public void setPcId(Integer pcId) {
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

	public Timestamp getBookedTime() {
		return bookedTime;
	}

	public void setBookedTime(Timestamp bookedTime) {
		this.bookedTime = bookedTime;
	}
	
	public List<TransactionDetail> getUserTransactionDetail(Integer userId) {
		String query = "SELECT * FROM TransactionDetail WHERE CustomerID = ?";
		
		PreparedStatement ps = db.prepareStatement(query);
		Vector<TransactionDetail> tds = new Vector<>();
		try {
			ps.setInt(1, userId);
			db.rs = ps.executeQuery();
			
			while (db.rs.next()) {
				Integer transactionID = db.rs.getInt("TransactionID");
				Integer customerId = db.rs.getInt("CustomerID");
			    String customerName = UserController.getInstance().getUserDataById(customerId).getUsername();
			    Timestamp bookedTime = db.rs.getTimestamp("BookedTime");
			    tds.add(new TransactionDetail(transactionID, transactionID, customerId, customerName, bookedTime)); 
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch user transaction detail data");
			e.printStackTrace();
		}
		
		return tds;
	}
	
	public List<TransactionDetail> getAllTransactionDetail(Integer transactionId) {
		String query = "SELECT * FROM TransactionDetail";
		
		PreparedStatement ps = db.prepareStatement(query);
		Vector<TransactionDetail> tds = new Vector<>();
		try {
			db.rs = ps.executeQuery();
			while (db.rs.next()) {
				Integer transactionID = db.rs.getInt("TransactionID");
				Integer customerId = db.rs.getInt("CustomerID");
			    String customerName = UserController.getInstance().getUserDataById(customerId).getUsername();
			    Timestamp bookedTime = db.rs.getTimestamp("BookedTime");
			    tds.add(new TransactionDetail(transactionID, transactionID, customerId, customerName, bookedTime)); 
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch transaction detail data");
			e.printStackTrace();
		}
		
		return tds;
	}
	
	public void addTransactionDetail(Integer transactionId, List<PCBook> pcsBooked) {
		
	}

}
