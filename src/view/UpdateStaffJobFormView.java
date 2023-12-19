package view;

import controller.JobController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import models.Job;

public class UpdateStaffJobFormView extends Page implements Content {

	BorderPane layout;
	GridPane formContainer;
	Label staffIdLB, pageTitleLB, errorMessageLB, pcIdLB, jobStatusLB;
	ComboBox<String> jobStatusCB;
	Button updateBTN;
	JobController jobContoller;
	private Job currentJob;
	JobView jobView;

	public UpdateStaffJobFormView(Job currentJob, JobView jobView) {
		this.jobView = jobView;
		this.currentJob = currentJob;
		initComp();
		addComp();
		arrangeComp();
		action();
	}

	@Override
	public BorderPane getContent() {
		return layout;
	}

	@Override
	protected void initComp() {
		layout = new BorderPane();
		formContainer = new GridPane();
		staffIdLB = label.setText("Staff ID : " + currentJob.getUserId()).setFontSize("12").setTextColor("Black")
				.build();
		pageTitleLB = label.setText("Update Staff Job Status : ").setFontSize("16").setTextColor("Black").build();
		jobStatusLB = label.setText("Job Status").setFontSize("12").setTextColor("Black").build();
		pcIdLB = label.setText("Pc ID : " + currentJob.getPcId()).setFontSize("12").setTextColor("Black").build();
		jobContoller = new JobController();
		jobStatusCB = new ComboBox<>();
		jobStatusCB.setPromptText(currentJob.getJobStatus());
		jobStatusCB.getItems().addAll("Complete", "UnComplete");
		errorMessageLB = label.setText("").setTextColor("Red").build();
		updateBTN = button.setText("Update Job Status").setColor("Green").setFontSize("12").setFontColor("White").build();
	}

	@Override
	protected void addComp() {
		layout.setCenter(formContainer);
		formContainer.add(pageTitleLB, 0, 1);
		formContainer.add(staffIdLB, 0, 2);
		formContainer.add(pcIdLB, 0, 3);
		formContainer.add(jobStatusLB, 0, 4);
		formContainer.add(jobStatusCB, 0, 5);
		formContainer.add(errorMessageLB, 0, 6);
		formContainer.add(updateBTN, 0, 7);
	}

	@Override
	protected void arrangeComp() {
		layout.setPadding(new Insets(10, 20, 10, 10));
		BorderPane.setAlignment(formContainer, Pos.CENTER);
		formContainer.setPadding(new Insets(10));
		layout.setBorder(new Border(new javafx.scene.layout.BorderStroke(javafx.scene.paint.Color.BLACK,
				javafx.scene.layout.BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				new javafx.scene.layout.BorderWidths(1, 1, 0, 1))));
	}

	@Override
	protected void action() {
		updateBTN.setOnMouseClicked(e -> {
			String errMsg = jobContoller.updateJobStatus(currentJob.getjobId(), jobStatusCB.getValue().toString());
			if (errMsg.equals("Successfully update job status")) {
				jobView.refreshJobData();
			} else {
				displayAlert(AlertType.ERROR, errMsg);
			}
		});
	}

}
