package models;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import connection.Connect;

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
		String query = "SELECT * FROM TransactionDetail WHERE CustomerId = ?";
		
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setInt(1, userId);
		} catch (SQLException e) {
			System.out.println("Failed to fetch user transaction detail data");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public List<TransactionDetail> getAllTransactionDetail(Integer transactionId) {
		return null;
	}
	
	public void addTransactionDetail(Integer transactionId, List<PCBook> pcsBooked) {
		
	}

}
