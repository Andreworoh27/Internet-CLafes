package view;

import java.util.List;

import controller.JobController;
import controller.UserController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import models.Job;
import models.User;

public class JobView extends Page {

	Scene viewAllJob;
	BorderPane layout, tableContainer;
	LayoutView lv;
	TableView<Job> jobsTB;
	JobController jobController;
	Button addJobButton;

	public JobView() {
		// TODO Auto-generated constructor stub
		initComp();
		addComp();
		arrangeComp();
		action();
		displayView(viewAllJob);
	}

	@Override
	protected void initComp() {
		// TODO Auto-generated method stub
		addJobButton = button.setText("Add New Job").setColor("Green").setFontSize("14").setFontColor("White")
				.setPrefWidth(100).setPadding(10).build();
		jobController = new JobController();
		tableContainer = new BorderPane();
		layout = new BorderPane();
		lv = new LayoutView();
		layout = lv.getLayout();
		viewAllJob = new Scene(layout, 1000, 600);
		jobsTB = new TableView<>();
		getAllJobs();
		addJobDataToTable();

	}

	@SuppressWarnings("unchecked")
	private void addJobDataToTable() {
		TableColumn<Job, Integer> jobIdColumn = new TableColumn<>("Job ID");
		jobIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getjobId()));
		jobIdColumn.setStyle( "-fx-alignment: CENTER;  -fx-font-size:15px;  ");

		TableColumn<Job, String> userNameColumn = new TableColumn<>("User Name");
		userNameColumn.setCellValueFactory(cellData -> {
			int userId = cellData.getValue().getUserId();
			UserController userController = new UserController();
			User user = userController.getUserDataById(userId);
			return new SimpleObjectProperty<>(user.getUsername());
		});
		userNameColumn.prefWidthProperty().bind(jobsTB.widthProperty().multiply(0.24));
		userNameColumn.setStyle( "-fx-alignment: CENTER; -fx-font-size:15px;  ");

		TableColumn<Job, String> pcIdColumn = new TableColumn<>("PC ID");
		pcIdColumn.setCellValueFactory(new PropertyValueFactory<>("pcId"));
	    pcIdColumn.prefWidthProperty().bind(jobsTB.widthProperty().multiply(0.24));
	    pcIdColumn.setStyle( "-fx-alignment: CENTER; -fx-font-size:15px;  ");

		TableColumn<Job, String> jobStatusColumn = new TableColumn<>("Job Status");
		jobStatusColumn.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
	    jobStatusColumn.prefWidthProperty().bind(jobsTB.widthProperty().multiply(0.24));
	    jobStatusColumn.setStyle( "-fx-alignment: CENTER; -fx-font-size:15px;  ");

		TableColumn<Job, Void> updateColumn = new TableColumn<>("Update");
		updateColumn.setCellFactory(param -> new TableCell<>() {
			private final Button updateButton = new Button("Update");

			{
				updateButton.setOnAction(event -> {
					Job job = getTableView().getItems().get(getIndex());
					UpdateStaffJobFormView updateStaffJobView = new UpdateStaffJobFormView(job, JobView.this);
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
		updateColumn.prefWidthProperty().bind(jobsTB.widthProperty().multiply(0.24));
		updateColumn.setStyle( "-fx-alignment: CENTER; -fx-font-size:15px;  ");

		jobsTB.getColumns().addAll(jobIdColumn, userNameColumn, pcIdColumn, jobStatusColumn, updateColumn);
	}

	private void getAllJobs() {
		List<Job> jobs = jobController.getAllJobData();
		ObservableList<Job> jobsData = FXCollections.observableArrayList(jobs);
		jobsTB.setItems(jobsData);
	}

	public void refreshJobData() {
		jobsTB.getItems().clear();
		getAllJobs();
	}

	@Override
	protected void addComp() {
		// TODO Auto-generated method stub
		tableContainer.setTop(label.setText("Manage Job").setFontSize("20").build());
		tableContainer.setCenter(addJobButton);
		tableContainer.setBottom(jobsTB);
		layout.setCenter(tableContainer);
	}

	@SuppressWarnings("static-access")
	@Override
	protected void arrangeComp() {
		tableContainer.setPadding(new Insets(10));
		tableContainer.setAlignment(addJobButton, Pos.CENTER_LEFT);
//		tableContainer.setMaxHeight(600);
		jobsTB.setMinHeight(500);
		// TODO Auto-generated method stub
	}

	@Override
	protected void action() {
		// TODO Auto-generated method stub
		addJobButton.setOnMouseClicked(e -> {
			AddNewJobFormView newJobFormView = new AddNewJobFormView(this);
			layout.setRight(newJobFormView.getContent());
		});
	}

}
