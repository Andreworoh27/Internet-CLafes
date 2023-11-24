package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import connection.Connect;
import controller.UserController;

public class TransactionHeader {
	
	private Connect db = Connect.getConnection();
	
    private Integer transactionID, staffId;
    private String staffName;
    private Date transactionDate;

    public TransactionHeader() {}

	public TransactionHeader(Integer transactionID, Integer staffId, String staffName, Date transactionDate) {
		super();
		this.transactionID = transactionID;
		this.staffId = staffId;
		this.staffName = staffName;
		this.transactionDate = transactionDate;
	}

	public Integer getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public List<TransactionHeader> getAllTransactionHeaderData() {
		String query = "SELECT * FROM TransactionHeader";
		
		Vector<TransactionHeader> ths = new Vector<>();
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			while (rs.next()) {
				Integer transactionId = rs.getInt("TransactionID");
				Integer staffId = rs.getInt("StaffID");
				String staffName = rs.getString("StaffName");
				Date transactionDate = rs.getDate("TransactionDate");
				ths.add(new TransactionHeader(transactionId, staffId, staffName, transactionDate));
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch transaction header data");
			e.printStackTrace();
		}
		
		return ths;
	}

	public Integer addNewTransactionHeader(Integer staffId, Date transactionDate) {
		String query = "INSERT INTO TransactionHeader VALUES (?, ?)";
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setInt(1, staffId);
			ps.setDate(2, transactionDate);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to add new transaction header data");
			e.printStackTrace();
		}
		
		Integer transactionId = 0;
		try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	        if (generatedKeys.next()) {
	            transactionId = generatedKeys.getInt(1);
	        }
	    } catch (SQLException e) {
			System.out.println("Failed to fetch latest transaction id");
			e.printStackTrace();
		}
		
		return transactionId;
	}
	
}
