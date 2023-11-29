package view;

import java.sql.Date;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import models.TransactionDetail;

public class CustomerTransactionHistoryView extends Page {
	
	Scene transactionHistory;
	
	LayoutView lv;
	BorderPane layout;
	
	TableView<TransactionDetail> transHistoryTable;
	
	public CustomerTransactionHistoryView() {
		initComp();
		addComp();
		arrangeComp();
		action();
		displayView(transactionHistory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void initComp() {
		lv = new LayoutView();
		layout = lv.getLayout();
		transHistoryTable = new TableView<TransactionDetail>();
		TableColumn<TransactionDetail, Integer> transactionIdCol = new TableColumn<>("Transaction ID");
		transactionIdCol.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
		transactionIdCol.setPrefWidth(160);

		TableColumn<TransactionDetail, String> pcIdCol = new TableColumn<>("PC ID");
		pcIdCol.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		pcIdCol.setPrefWidth(160);
		
		TableColumn<TransactionDetail, Integer> customerIdCol = new TableColumn<>("Customer ID");
		customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
		customerIdCol.setPrefWidth(160);

		TableColumn<TransactionDetail, String> customerNameCol = new TableColumn<>("Customer Name");
		customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		customerNameCol.setPrefWidth(160);
		
		TableColumn<TransactionDetail, Date> bookTimeCol = new TableColumn<>("Booked Time");
		bookTimeCol.setCellValueFactory(new PropertyValueFactory<>("bookedTime"));
		bookTimeCol.setPrefWidth(160);
		
		transHistoryTable.getColumns().addAll(transactionIdCol, pcIdCol, customerIdCol, customerNameCol, bookTimeCol);
		transactionHistory = new Scene(layout, 1000, 600);
	}

	@Override
	protected void addComp() {
		layout.setCenter(transHistoryTable);
	}

	@Override
	protected void arrangeComp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void action() {
		// TODO Auto-generated method stub
		
	}
	
	

}
