package view;

import java.sql.Date;
import java.util.List;

import controller.PCBookController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import models.PC;
import models.PCBook;

public class PcBookView extends Page {
	Scene viewPCBook;
	BorderPane layout;
	GridPane gridContainer;
	Label testLB, titleLabel;
	LayoutView lv;
	PC pc;
	FlowPane pcContainer;
	Button PcBookViewButton;
	TableView<PCBook> pcBookTableView;
	VBox vBox;

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
		viewPCBook = new Scene(layout, 900, 600);
		titleLabel = new Label("VIEW PC BOOK");
		vBox = new VBox();
	}

	@Override
	protected void addComp() {
		layout.setCenter(gridContainer);
		vBox.getChildren().addAll(titleLabel, pcBookTableView);
		manageGridContainer(vBox);
		displayPCBook();
	}

	@SuppressWarnings("unchecked")
	private void displayPCBook() {
		TableColumn<PCBook, Integer> bookIdColumn = new TableColumn<>("Book ID");
		bookIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBookId()));

		TableColumn<PCBook, String> pcIdColumn = new TableColumn<>("PC ID");
		pcIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPcId()));

		TableColumn<PCBook, Integer> userIdColumn = new TableColumn<>("User ID");
		userIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUserId()));

		TableColumn<PCBook, Date> pcBookDateColumn = new TableColumn<>("Book Date");
		pcBookDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBookDate()));

		pcBookTableView.getColumns().addAll(bookIdColumn, pcIdColumn, userIdColumn, pcBookDateColumn);
	}

	private void manageGridContainer(VBox vBox) {
		gridContainer.add(vBox, 0, 1, 2, 1);
	}

	@Override
	protected void arrangeComp() {
		gridContainer.setAlignment(Pos.CENTER);
		titleLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;-fx-alignment: center;");
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