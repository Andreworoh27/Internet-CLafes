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

public class PcFinishView extends Page {
	Scene viewFinishBook;
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
	}

	@Override
	protected void addComp() {
		layout.setCenter(borderContainer);
		borderContainer.setCenter(gridContainer);
		manageGridContainer();

		datePicker.valueProperty().addListener((obs, oldDate, newDate) -> {
			getPCBookBeforeDate(newDate);
		});
	}
	
	private void getPCBookBeforeDate(LocalDate newDate) {
		if(newDate!= null) {
			pcBookTableView = new TableView<>();
			ObservableList<PCBook> pcBookList = FXCollections.observableArrayList();
			if(newDate.isBefore(LocalDate.now())) {

				Date sqlDate = Date.valueOf(newDate);
				pcBook = pcBookController.getPcBookedByDate(sqlDate);	
				
//				else {
					for(PCBook book: pcBook) {
						pcBookList.add(book);
					}
//				}
				
				pcBookTableView.getItems().clear();
				pcBookTableView.setItems(pcBookList);
				displayPCBook(pcBookList);
			}
			else if(newDate.isAfter(LocalDate.now())){
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

		TableColumn<PCBook, Void> finishColumn = new TableColumn<>("Finish Book");
		finishColumn.setCellFactory(new Callback<>() {
			public TableCell<PCBook, Void> call(TableColumn<PCBook, Void> param) {
				return new TableCell<>() {
					private Button finishButton = new Button("Finish");
					{
						finishButton.setOnAction(event -> {
							PCBook pcBook = getTableRow().getItem();
							if(pcBook != null) {
								finishBookDate(pcBook);
								getTableView().getItems().remove(pcBook);
							}
						});
					}

					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(finishButton);
						}
					}

				};
			}
		});
		
		pcBookTableView.getColumns().addAll(bookIdColumn, pcIdColumn, userIdColumn, pcBookDateColumn, finishColumn);
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
		if(datePicker.getValue() != null) {
			getPCBookBeforeDate(datePicker.getValue());
		}
		
	}
	
	private void finishBookDate(PCBook pcBookItem) {
//		Date bookDate = pcBookItem.getBookDate();
		pcBookController.finishBook(pcBookItem.getBookDate());
		System.out.println("Tanggal: " +pcBookItem.getBookDate());
		System.out.println("id nya: " +pcBookItem.getBookId());
	}

	
}
