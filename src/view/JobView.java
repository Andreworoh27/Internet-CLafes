package view;

import java.util.List;

import controller.JobController;
import controller.UserController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
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
		viewAllJob = new Scene(layout, 900, 600);
		jobsTB = new TableView<>();
		getAllJobs();
		addJobDataToTable();

	}

	@SuppressWarnings("unchecked")
	private void addJobDataToTable() {
		TableColumn<Job, Integer> jobIdColumn = new TableColumn<>("Job ID");
		jobIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getjobId()));

		TableColumn<Job, String> userNameColumn = new TableColumn<>("User Name");
		userNameColumn.setCellValueFactory(cellData -> {
			int userId = cellData.getValue().getUserId();
			UserController userController = new UserController();
			User user = userController.getUserDataById(userId);
			return new SimpleObjectProperty<>(user.getUsername());
		});

		TableColumn<Job, String> pcIdColumn = new TableColumn<>("PC ID");
		pcIdColumn.setCellValueFactory(new PropertyValueFactory<>("pcId"));

		TableColumn<Job, String> jobStatusColumn = new TableColumn<>("Job Status");
		jobStatusColumn.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));

		jobsTB.getColumns().addAll(jobIdColumn, userNameColumn, pcIdColumn, jobStatusColumn);
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
		tableContainer.setCenter(jobsTB);
		tableContainer.setTop(addJobButton);
		layout.setCenter(tableContainer);
	}

	@Override
	protected void arrangeComp() {
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
