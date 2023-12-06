package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import connection.Connect;
import controller.UserController;

public class TransactionHeader {
	
	private Connect db = Connect.getConnection();
	
    private Integer transactionId, staffId;
    private String staffName;
    private Date transactionDate;

    public TransactionHeader() {}

	public TransactionHeader(Integer transactionId, Integer staffId, String staffName, Date transactionDate) {
		super();
		this.transactionId = transactionId;
		this.staffId = staffId;
		this.staffName = staffName;
		this.transactionDate = transactionDate;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
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
	    String query = "INSERT INTO TransactionHeader (StaffID, StaffName, TransactionDate) VALUES (?, ?, ?)";
	    PreparedStatement ps = db.prepareStatement(query);
	    try {
	        UserController uc = new UserController();
	        ps.setInt(1, staffId);
	        ps.setString(2, uc.getUserDataById(staffId).getUsername());
	        ps.setDate(3, transactionDate);
	        ps.executeUpdate();
	        System.out.println("staff id: " +staffId);
	        System.out.println("staff name: " +uc.getUserDataById(staffId).getUsername());
	    } catch (SQLException e) {
	        System.out.println("Failed to add new transaction header data" + e.getMessage());
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
