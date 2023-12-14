package view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

import controller.PCBookController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import models.PCBook;

public class PcFinishView extends Page {
	Scene viewFinishBook;
	BorderPane borderContainer;
	BorderPane layout;
	GridPane gridContainer;
	LayoutView lv;
	FlowPane pcContainer;
	Button pcFinishButton;
	TableView<PCBook> pcBookTableView;
	Label dateLB;
	DatePicker datePicker;
	List<PCBook> pcBook;
	PCBookController pcBookController;
	String finishBookDate;
	
	public PcFinishView() {
		System.out.println("PcFinishView constructor called");
		initComp();
		addComp();
		arrangeComp();
		action();
		displayView(viewFinishBook);
	}
	
	@Override
	protected void initComp() {
		borderContainer = new BorderPane();
		layout = new BorderPane();
		lv = new LayoutView();
		layout = lv.getLayout();
		gridContainer = new GridPane();
		pcBookTableView = new TableView<>();
		viewFinishBook = new Scene(layout, 900, 600);
		dateLB = new Label("Choose Date: ");
		datePicker = new DatePicker();
		pcBookController = new PCBookController();
		pcFinishButton = button.setText("Finish Book").setFontSize("14").setFontColor("White").setPadding(5).setPrefWidth(100).setPadding(10).build();
	}

	@Override
	protected void addComp() {
		layout.setCenter(borderContainer);
		borderContainer.setCenter(gridContainer);
		manageGridContainer();

		datePicker.valueProperty().addListener((obs, oldDate, newDate) -> {
			pcBookTableView = new TableView<>();
			ObservableList<PCBook> pcBookList = FXCollections.observableArrayList();
			pcBook = pcBookController.finishBookCheck(java.sql.Date.valueOf(newDate));
			if(pcBook == null) {
				pcBookList.clear();
				pcBookList.addAll(new Vector<PCBook>());
				pcBookTableView.getItems().clear();
				pcBookTableView.setItems(pcBookList);
				displayPCBook(pcBookList);
				return;
			}
			pcBookList.addAll(pcBook);
			pcBookTableView.getItems().clear();
			pcBookTableView.setItems(pcBookList);
			displayPCBook(pcBookList);
			pcBook.clear();
		});
	}

	@SuppressWarnings("unchecked")
	private void displayPCBook(List<PCBook> pcBook) {

		TableColumn<PCBook, Integer> bookIdColumn = new TableColumn<>("Book ID");
		bookIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBookId()));

		TableColumn<PCBook, String> pcIdColumn = new TableColumn<>("PC ID");
		pcIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPcId()));

		TableColumn<PCBook, Integer> userIdColumn = new TableColumn<>("User ID");
		userIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUserId()));

		TableColumn<PCBook, Date> pcBookDateColumn = new TableColumn<>("Book Date");
		pcBookDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBookDate()));
		
		pcBookTableView.getColumns().addAll(bookIdColumn, pcIdColumn, userIdColumn, pcBookDateColumn);
		gridContainer.add(pcBookTableView, 0, 1, 2, 1);
	}
	
	private void manageGridContainer() {
		gridContainer.add(dateLB, 0, 0);
		gridContainer.add(datePicker, 1, 0);
		gridContainer.add(pcBookTableView, 0, 1, 2, 1);
		gridContainer.add(pcFinishButton, 0, 2);
	}
	
	@Override
	protected void arrangeComp() {
		gridContainer.setAlignment(Pos.CENTER);
		gridContainer.setVgap(10);

		setGridPaneAlignment();
		datePicker.setPrefWidth(250);
	}
	
	private void setGridPaneAlignment() {
		GridPane.setHalignment(dateLB, HPos.CENTER);
	}

	@Override
	protected void action() {
		datePicker.valueProperty().addListener((obs, oldDate, newDate) -> {
			pcFinishButton.setOnAction(e -> {
				try {
					Page.displayAlert(AlertType.INFORMATION, pcBookController.finishBook(java.sql.Date.valueOf(newDate)));
					pcBookTableView = new TableView<>();
					ObservableList<PCBook> pcBookList = FXCollections.observableArrayList();
					pcBook = pcBookController.finishBookCheck(java.sql.Date.valueOf(newDate));
					if(pcBook == null) {
						pcBookList.clear();
						pcBookList.addAll(new Vector<PCBook>());
						pcBookTableView.getItems().clear();
						pcBookTableView.setItems(pcBookList);
						displayPCBook(pcBookList);
						return;
					}
					pcBookList.addAll(pcBook);
					pcBookTableView.getItems().clear();
					pcBookTableView.setItems(pcBookList);
					displayPCBook(pcBookList);
					pcBook.clear();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			});
		});
		
	}
}
