package view;

import java.sql.Date;
import java.util.List;

import controller.PCBookController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import models.PCBook;

public class PcBookView extends Page {
	
	Scene viewPCBook;
	BorderPane layout;
	GridPane gridContainer;
	Label testLB, titleLabel;
	LayoutView lv;
	VBox content;
	FlowPane pcContainer;
	Button PcBookViewButton;
	TableView<PCBook> pcBookTableView;

	public PcBookView() {
		initComp();
		addComp();
		arrangeComp();
		action();
		displayView(viewPCBook);
	}

	@Override
	protected void initComp() {
		lv = new LayoutView();
		layout = new BorderPane();
		layout = lv.getLayout();
		gridContainer = new GridPane();
		pcBookTableView = new TableView<>();
		viewPCBook = new Scene(layout, 1000, 600);
		titleLabel = label.setText("PC Book Data").setFontSize("20").build();
		content = new VBox();
	}

	@Override
	protected void addComp() {
		layout.setCenter(content);
		content.getChildren().addAll(titleLabel, pcBookTableView);
		displayPCBook();
	}

	private void displayPCBook() {
		int width = 760 / 5;
		
		TableColumn<PCBook, Integer> bookIdColumn = new TableColumn<>("Book ID");
		bookIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBookId()));
		bookIdColumn.setPrefWidth(width);
		
		TableColumn<PCBook, String> pcIdColumn = new TableColumn<>("PC ID");
		pcIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPcId()));
		pcIdColumn.setPrefWidth(width);
		
		TableColumn<PCBook, Integer> userIdColumn = new TableColumn<>("User ID");
		userIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUserId()));
		userIdColumn.setPrefWidth(width);
		
		TableColumn<PCBook, Date> pcBookDateColumn = new TableColumn<>("Book Date");
		pcBookDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBookDate()));
		pcBookDateColumn.setPrefWidth(width);
		
		TableColumn<PCBook, Void> assignColumn = new TableColumn<>("Assign To Other Pc");
		assignColumn.setCellFactory(param -> new TableCell<>() {
			private final Button updateButton = new Button("Assign User");

			{
				updateButton.setOnAction(event -> {
					PCBook pcBook = getTableView().getItems().get(getIndex());
					AssignUserToOtherPCView updateStaffJobView = new AssignUserToOtherPCView(pcBook, PcBookView.this);
					layout.setRight(updateStaffJobView.getContent());
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(updateButton);
				}
			}
		});
		assignColumn.setPrefWidth(width);

		pcBookTableView.getColumns().addAll(bookIdColumn, pcIdColumn, userIdColumn, pcBookDateColumn, assignColumn);
	}

	public void refreshBookData() {
		pcBookTableView.getItems().clear();
		getAllPCBook();
	}

	@Override
	protected void arrangeComp() {
		content.setSpacing(10);
		content.setPadding(new Insets(20));
	}

	@Override
	protected void action() {
		getAllPCBook();
	}

	private void getAllPCBook() {
		PCBookController pcBookController = new PCBookController();
		List<PCBook> pcBook = pcBookController.getAllPCBookedData();
		ObservableList<PCBook> data = FXCollections.observableArrayList(pcBook);
		pcBookTableView.setItems(data);
	}
	
}