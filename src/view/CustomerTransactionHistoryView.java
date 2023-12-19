package view;

import java.sql.Date;
import java.util.List;

import component.LabelBuilder;
import controller.TransactionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import models.TransactionDetail;

public class CustomerTransactionHistoryView extends Page {
	
	Scene transactionHistory;
	
	LayoutView lv;
	BorderPane layout;
	VBox content;
	
	Label title;
	TableView<TransactionDetail> transHistoryTable;
	
	TransactionController tc = new TransactionController();
	
	public CustomerTransactionHistoryView() {
		initComp();
		addComp();
		arrangeComp();
		action();
		displayView(transactionHistory);
	}
	
	@Override
	protected void initComp() {
		lv = new LayoutView();
		layout = lv.getLayout();
		content = new VBox();
		title = label.setText("Your Transaction History").setFontSize("20").build();
		buildDetailTable();
		refreshTable();
		transactionHistory = new Scene(layout, 1000, 600);
	}

	@Override
	protected void addComp() {
		content.getChildren().addAll(title, transHistoryTable);
		layout.setCenter(content);
	}

	@Override
	protected void arrangeComp() {
		content.setSpacing(10);
		content.setPadding(new Insets(20));
	}

	@Override
	protected void action() {}
	
	private void buildDetailTable() {
		int width = 760 / 5;
		
		transHistoryTable = new TableView<TransactionDetail>();
		TableColumn<TransactionDetail, Integer> transactionIdCol = new TableColumn<>("Transaction ID");
		transactionIdCol.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
		transactionIdCol.setPrefWidth(width);

		TableColumn<TransactionDetail, String> pcIdCol = new TableColumn<>("PC ID");
		pcIdCol.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		pcIdCol.setPrefWidth(width);
		
		TableColumn<TransactionDetail, Integer> customerIdCol = new TableColumn<>("Customer ID");
		customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
		customerIdCol.setPrefWidth(width);

		TableColumn<TransactionDetail, String> customerNameCol = new TableColumn<>("Customer Name");
		customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		customerNameCol.setPrefWidth(width);
		
		TableColumn<TransactionDetail, Date> bookTimeCol = new TableColumn<>("Booked Time");
		bookTimeCol.setCellValueFactory(new PropertyValueFactory<>("bookedTime"));
		bookTimeCol.setPrefWidth(width);
		
		transHistoryTable.getColumns().addAll(transactionIdCol, pcIdCol, customerIdCol, customerNameCol, bookTimeCol);
	}
	
	private void refreshTable() {
		List<TransactionDetail> tds = tc.getUserTransactionDetail(Page.user.getUserId());
		ObservableList<TransactionDetail> data = FXCollections.observableArrayList(tds);
        transHistoryTable.setItems(data);
	}

}
