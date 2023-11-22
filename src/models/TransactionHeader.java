package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import connection.Connect;

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
		db.rs = db.executeQuery(query);
		
		Vector<TransactionHeader> ths = new Vector<>();
		try {
			while (db.rs.next()) {
				Integer transactionId = db.rs.getInt("TransactionID");
				Integer staffId = db.rs.getInt("StaffID");
				Date transactionDate = db.rs.getDate("TransactionDate");
				ths.add(new TransactionHeader(transactionId, staffId, query, transactionDate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addNewTransactionHeader(Integer staffId, Date transactionDate) {
		String query = "INSERT INTO TransactionHeader VALUES (?, ?)";
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setInt(1, staffId);
			ps.setDate(2, transactionDate);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Failed to add new transaction header data");
			e.printStackTrace();
		}
	}
	
}
