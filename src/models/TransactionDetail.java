package models;

import java.sql.Timestamp;
import java.util.List;

public class TransactionDetail {
	
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
		return null;
	}
	
	public List<TransactionDetail> getAllTransactionDetail(Integer transactionId) {
		return null;
	}
	
	public void addTransactionDetail(Integer transactionId, List<PCBook> pcsBooked) {
		
	}

}
