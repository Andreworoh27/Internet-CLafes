package view;

import java.util.List;

import controller.UserController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import models.User;

public class StaffView extends Page {
	
	Scene viewAllStaff;
	
	BorderPane layout;
	LayoutView lv;
	VBox content;
	
	Label title;
	TableView<User> staffsTB;
	
	UserController userController;

	public StaffView() {
		initComp();
		addComp();
		arrangeComp();
		action();
		displayView(viewAllStaff);
	}

	@Override
	protected void initComp() {
		layout = new BorderPane();
		lv = new LayoutView();
		layout = lv.getLayout();
		content = new VBox();
		
		viewAllStaff = new Scene(layout, 1000, 600);
		
		title = label.setText("Staff Data").setFontSize("20").build();
		staffsTB = new TableView<>();
		userController = new UserController();
		getAllStaff();
		addJobDataToTable();
	}

	@SuppressWarnings("unchecked")
	private void addJobDataToTable() {
		int width = 760 / 5;
		TableColumn<User, Integer> jobIdColumn = new TableColumn<>("Staff ID");
		jobIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUserId()));
		jobIdColumn.setPrefWidth(width);
		
		TableColumn<User, String> userNameColumn = new TableColumn<>("User Name");
		userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		userNameColumn.setPrefWidth(width);

		TableColumn<User, String> pcIdColumn = new TableColumn<>("Staff Age");
		pcIdColumn.setCellValueFactory(new PropertyValueFactory<>("userAge"));
		pcIdColumn.setPrefWidth(width);
		
		TableColumn<User, String> jobStatusColumn = new TableColumn<>("Staff Role");
		jobStatusColumn.setCellValueFactory(new PropertyValueFactory<>("userRole"));
		jobStatusColumn.setPrefWidth(width);

		TableColumn<User, Void> updateColumn = new TableColumn<>("Update");
		updateColumn.setCellFactory(param -> new TableCell<>() {
			private final Button updateButton = new Button("Update");

			{
				updateButton.setOnAction(event -> {
					User staff = getTableView().getItems().get(getIndex());
					UpdateStaffRoleFormView updateStaffJobView = new UpdateStaffRoleFormView(staff, StaffView.this);
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
		updateColumn.setPrefWidth(width);

		staffsTB.getColumns().addAll(jobIdColumn, userNameColumn, pcIdColumn, jobStatusColumn, updateColumn);
	}

	private void getAllStaff() {
		List<User> staffs = userController.getAllStaff();
		ObservableList<User> staffsdata = FXCollections.observableArrayList(staffs);
		staffsTB.setItems(staffsdata);
	}

	public void refreshStaffData() {
		staffsTB.getItems().clear();
		getAllStaff();
	}

	@Override
	protected void addComp() {
		content.getChildren().addAll(title, staffsTB);
		layout.setCenter(content);
	}

	@Override
	protected void arrangeComp() {
		content.setSpacing(10);
		content.setPadding(new Insets(20));
	}

	@Override
	protected void action() {}
	
}
