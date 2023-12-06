package view;

import java.sql.Date;
import java.util.List;

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
import models.TransactionHeader;

public class StaffTransactionHistoryView extends Page {
	
	Scene transactionHistory;
	
	LayoutView lv;
	BorderPane layout, content;
	Label title, detailTitle;
	VBox headerBox, detailBox;
	
	TableView<TransactionHeader> transHeaderTable;
	TableView<TransactionDetail> transDetailTable;
	
	TransactionController tc = new TransactionController();
	
	public StaffTransactionHistoryView() {
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
		content = new BorderPane();
		title = new Label("Transaction Header Data");
		detailTitle = new Label();
		headerBox = new VBox();
		detailBox = new VBox();
		buildHeaderTable();
		refreshHeaderTable();
		buildDetailTable();
		transactionHistory = new Scene(layout, 1000, 600);
	}

	@Override
	protected void addComp() {
		layout.setCenter(content);
		content.setCenter(headerBox);
		headerBox.getChildren().addAll(title, transHeaderTable);
		detailBox.getChildren().addAll(detailTitle, transDetailTable);
	}

	@Override
	protected void arrangeComp() {
		headerBox.setSpacing(10);
		detailBox.setSpacing(10);
		BorderPane.setMargin(detailBox, new Insets(20, 0, 0, 0));
	}

	@Override
	protected void action() {
		transHeaderTable.setOnMouseClicked(e -> {
			if (e.getClickCount() == 1) {
                TransactionHeader selectedRow = transHeaderTable.getSelectionModel().getSelectedItem();
                refreshDetailTable(selectedRow.getTransactionId());
                detailTitle.setText("Transaction Detail of Transaction #" + selectedRow.getTransactionId());
                content.setBottom(detailBox);
            }
		});
	}
	
	private void buildHeaderTable() {
		transHeaderTable = new TableView<TransactionHeader>();
		TableColumn<TransactionHeader, Integer> transactionIdCol = new TableColumn<>("Transaction ID");
		transactionIdCol.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
		transactionIdCol.setPrefWidth(200);
		
		TableColumn<TransactionHeader, Integer> staffIdCol = new TableColumn<>("Staff ID");
		staffIdCol.setCellValueFactory(new PropertyValueFactory<>("staffId"));
		staffIdCol.setPrefWidth(200);

		TableColumn<TransactionHeader, String> staffNameCol = new TableColumn<>("Staff Name");
		staffNameCol.setCellValueFactory(new PropertyValueFactory<>("staffName"));
		staffNameCol.setPrefWidth(200);
		
		TableColumn<TransactionHeader, Date> transactionDateCol = new TableColumn<>("Transaction Date");
		transactionDateCol.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
		transactionDateCol.setPrefWidth(200);
		
		transHeaderTable.getColumns().addAll(transactionIdCol, staffIdCol, staffNameCol, transactionDateCol);
	}
	
	private void refreshHeaderTable() {
		List<TransactionHeader> ths = tc.getAllTransactionHeaderData();
		ObservableList<TransactionHeader> data = FXCollections.observableArrayList(ths);
        transHeaderTable.setItems(data);
	}
	
	private void buildDetailTable() {
		transDetailTable = new TableView<TransactionDetail>();
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
		
		transDetailTable.getColumns().addAll(transactionIdCol, pcIdCol, customerIdCol, customerNameCol, bookTimeCol);
	}
	
	private void refreshDetailTable(Integer transactionId) {
		List<TransactionDetail> tds = tc.getAllTransactionDetail(transactionId);
		ObservableList<TransactionDetail> data = FXCollections.observableArrayList(tds);
        transDetailTable.setItems(data);
	}

}
