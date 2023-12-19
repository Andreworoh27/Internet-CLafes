package controller;
import java.sql.Date;
import java.util.List;

import models.PCBook;
import models.TransactionDetail;
import models.TransactionHeader;

/*
 * Manage transaction-related operations.
 * Interact with TransactionHeader and TransactionDetail class.
 * */

public class TransactionController {
	
	private TransactionHeader th = new TransactionHeader();
	private TransactionDetail td = new TransactionDetail();
	
	public List<TransactionHeader> getAllTransactionHeaderData() {
		return th.getAllTransactionHeaderData();
	}

	public List<TransactionDetail> getAllTransactionDetail(Integer transactionId) {
		return td.getAllTransactionDetail(transactionId);
	}
	
	public List<TransactionDetail> getUserTransactionDetail(Integer userId) {
		return td.getUserTransactionDetail(userId);
	}
	
	public void addTransaction(Integer transactionId, List<PCBook> pcBooked, Integer staffId, Date transactionDate) {
		Integer generatedTransactionId = th.addNewTransactionHeader(staffId, transactionDate);
		td.addTransactionDetail(generatedTransactionId, pcBooked);
	}
	
}
