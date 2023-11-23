package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.Vector;

import connection.Connect;
import controller.UserController;

public class TransactionDetail {
	
	private Connect db = Connect.getConnection();
	
    private Integer transactionID, customerId;
    private String pcId, customerName;
    private Date bookedTime;

    public TransactionDetail() {}

	public TransactionDetail(Integer transactionID, String pcId, Integer customerId, String customerName,
			Date bookedTime) {
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
			    String customerName = UserController.getInstance().getUserDataById(customerId).getUsername();
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
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String pcId = rs.getString("PcID");
				Integer customerId = rs.getInt("CustomerID");
			    String customerName = UserController.getInstance().getUserDataById(customerId).getUsername();
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
		String query = "INSERT INTO TransactionDetail VALUES ()";
	}

}
