package view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import controller.PCBookController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

public class PcCancelView extends Page {

	Scene viewCancelBook;
	BorderPane borderContainer;
	BorderPane layout;
	GridPane gridContainer;
	LayoutView lv;
	FlowPane pcContainer;
	Button pcCancelViewButton;
	TableView<PCBook> pcBookTableView;
	Label dateLB;
	DatePicker datePicker;
	List<PCBook> pcBook;
	PCBookController pcBookController;
	
	public PcCancelView() {
		System.out.println("PCCancelView constructor called");
		initComp();
		addComp();
		arrangeComp();
		action();
		displayView(viewCancelBook);
	}

	@Override
	protected void initComp() {
		borderContainer = new BorderPane();
		layout = new BorderPane();
		lv = new LayoutView();
		layout = lv.getLayout();
		gridContainer = new GridPane();
		pcBookTableView = new TableView<>();
		viewCancelBook = new Scene(layout, 900, 600);
		dateLB = new Label("Choose Date: ");
		datePicker = new DatePicker();
		pcBookController = new PCBookController();
		// Kalo cancel itu dia bisa dipilih kalau dia belum lewat tanggal
		// berati kalau dia pilih tanggal -> munculin semua tanggal yang belum lewat
	}

	@Override
	protected void addComp() {
		layout.setCenter(borderContainer);
		borderContainer.setCenter(gridContainer);
		manageGridContainer();

		datePicker.valueProperty().addListener((obs, oldDate, newDate) -> {
			getPCBookAfterDate(newDate);
		});
	}

	private void getPCBookAfterDate(LocalDate newDate) {
		
		if(newDate!= null) {
			pcBookTableView = new TableView<>();
			ObservableList<PCBook> pcBookList = FXCollections.observableArrayList();
			if(newDate.isAfter(LocalDate.now())) {
				pcBookList.clear();
				Date sqlDate = Date.valueOf(newDate);
				pcBook = pcBookController.getAllPCBookedData();
				
				List <PCBook> canceledBooks = pcBookController.cancelBook(sqlDate);
				
				if(canceledBooks == null) {
					//Display message nanti
					return;
				}
				else {
					for(PCBook book : pcBook) {
						LocalDate bookDate = book.getBookDate().toLocalDate();
						//BookDate itu data dari semua book
						//new date itu data dari date picker
						if(bookDate.isAfter(newDate)) {
							pcBookList.add(book);
						}
					}
				}
			pcBookTableView.getItems().clear();
			pcBookTableView.setItems(pcBookList);
			displayPCBook(pcBookList);
			}
			else if(newDate.isBefore(LocalDate.now())){
				pcBookTableView = new TableView<>();
				pcBookList.clear();
				pcBookTableView.setItems(pcBookList);
				displayPCBook(pcBookList);

				return;
			}
		}
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

		TableColumn<PCBook, Void> cancelColumn = new TableColumn<>("Cancel Book");
		cancelColumn.setCellFactory(new Callback<>() {
			public TableCell<PCBook, Void> call(TableColumn<PCBook, Void> param) {
				return new TableCell<>() {
					private Button cancelButton = new Button("Cancel");
					{
						cancelButton.setOnAction(event -> {
							PCBook pcBook = getTableRow().getItem();
							if(pcBook != null) {
								deleteBookData(pcBook);
								getTableView().getItems().remove(pcBook);
							}
						});
					}

					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(cancelButton);
						}
					}

				};
			}
		});
		
		pcBookTableView.getColumns().addAll(bookIdColumn, pcIdColumn, userIdColumn, pcBookDateColumn, cancelColumn);
		gridContainer.add(pcBookTableView, 0, 1, 2, 1);
	}

	private void manageGridContainer() {
		gridContainer.add(dateLB, 0, 0);
		gridContainer.add(datePicker, 1, 0);
		gridContainer.add(pcBookTableView, 0, 1, 2, 1);
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
		if (datePicker.getValue() != null) {
			getPCBookAfterDate(datePicker.getValue());
		}
	}

	private void deleteBookData(PCBook pcBookItem) {
		PCBookController pcBookController = new PCBookController();
		pcBookController.deleteBookData(pcBookItem.getBookId());
	}

}
