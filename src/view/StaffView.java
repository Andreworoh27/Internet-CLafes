package view;

import java.util.List;

import controller.UserController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import models.User;

public class StaffView extends Page {
	Scene viewAllStaff;
	BorderPane layout, tableContainer;
	LayoutView lv;
	TableView<User> staffsTB;
	UserController userController;

	public StaffView() {
		// TODO Auto-generated constructor stub
		initComp();
		addComp();
		arrangeComp();
		action();
		displayView(viewAllStaff);
	}

	@Override
	protected void initComp() {
		// TODO Auto-generated method stub
		tableContainer = new BorderPane();
		layout = new BorderPane();
		lv = new LayoutView();
		layout = lv.getLayout();
		viewAllStaff = new Scene(layout, 900, 600);
		staffsTB = new TableView<>();
		userController = new UserController();
		getAllStaff();
		addJobDataToTable();

	}

	@SuppressWarnings("unchecked")
	private void addJobDataToTable() {
		TableColumn<User, Integer> jobIdColumn = new TableColumn<>("Staff ID");
		jobIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUserId()));

		TableColumn<User, String> userNameColumn = new TableColumn<>("User Name");
		userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

		TableColumn<User, String> pcIdColumn = new TableColumn<>("Staff Age");
		pcIdColumn.setCellValueFactory(new PropertyValueFactory<>("userAge"));

		TableColumn<User, String> jobStatusColumn = new TableColumn<>("Staff Role");
		jobStatusColumn.setCellValueFactory(new PropertyValueFactory<>("userRole"));

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
		// TODO Auto-generated method stub
		tableContainer.setCenter(staffsTB);
		layout.setCenter(tableContainer);
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
