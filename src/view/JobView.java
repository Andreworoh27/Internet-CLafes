package view;

import java.util.List;

import controller.JobController;
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
import models.Job;
import models.User;

public class JobView extends Page {

	Scene viewAllJob;
	BorderPane layout;
	LayoutView lv;
	VBox content;
	Label title;
	TableView<Job> jobsTB;
	JobController jobController;
	Button addJobButton;

	public JobView() {
		initComp();
		addComp();
		arrangeComp();
		action();
		displayView(viewAllJob);
	}

	@Override
	protected void initComp() {
		addJobButton = button.setText("Add New Job").setColor("Green").setFontSize("14").setFontColor("White")
				.setPrefWidth(150).setPadding(10).build();
		jobController = new JobController();
		content = new VBox();
		layout = new BorderPane();
		lv = new LayoutView();
		layout = lv.getLayout();
		viewAllJob = new Scene(layout, 1000, 600);
		title = label.setText("Manage Job").setFontSize("20").build();
		jobsTB = new TableView<>();
		getAllJobs();
		addJobDataToTable();
	}

	@SuppressWarnings("unchecked")
	private void addJobDataToTable() {
		int width = 760 / 5;
		TableColumn<Job, Integer> jobIdColumn = new TableColumn<>("Job ID");
		jobIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getjobId()));
		jobIdColumn.setPrefWidth(width);

		TableColumn<Job, String> userNameColumn = new TableColumn<>("User Name");
		userNameColumn.setCellValueFactory(cellData -> {
			int userId = cellData.getValue().getUserId();
			UserController userController = new UserController();
			User user = userController.getUserDataById(userId);
			return new SimpleObjectProperty<>(user.getUsername());
		});
		userNameColumn.setPrefWidth(width);

		TableColumn<Job, String> pcIdColumn = new TableColumn<>("PC ID");
		pcIdColumn.setCellValueFactory(new PropertyValueFactory<>("pcId"));
	    pcIdColumn.setPrefWidth(width);

		TableColumn<Job, String> jobStatusColumn = new TableColumn<>("Job Status");
		jobStatusColumn.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
	    jobStatusColumn.setPrefWidth(width);

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
		updateColumn.setPrefWidth(width);

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
		content.getChildren().addAll(title, addJobButton, jobsTB);
		layout.setCenter(content);
	}

	@Override
	protected void arrangeComp() {
		content.setPadding(new Insets(20));
		content.setSpacing(10);
		jobsTB.setMinHeight(500);
	}

	@Override
	protected void action() {
		addJobButton.setOnMouseClicked(e -> {
			AddNewJobFormView newJobFormView = new AddNewJobFormView(this);
			layout.setRight(newJobFormView.getContent());
		});
	}

}
