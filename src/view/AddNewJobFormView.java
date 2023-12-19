package view;

import controller.JobController;
import controller.PCController;
import controller.UserController;
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
import models.PC;
import models.User;

/*
 * Form for updating staff role.
 * Only Admin can access this form.
 * Admin can Update Technician Job Status.
 * */


public class AddNewJobFormView extends Page implements Content {
	
	BorderPane layout;
	GridPane formContainer;
	Label pcIdLB, userIdLB, pageTitleLB, errorMessageLB;
	ComboBox<String> usersListCB, pcListCB;
	Button insertBTN;
	JobController jobController;
	JobView jobView;

	public AddNewJobFormView(JobView jobView) {
		this.jobView = jobView;
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
		formContainer = new GridPane();
		jobController = new JobController();
		userIdLB = label.setText("Choose User ID : ").setFontSize("12").setTextColor("Black").build();
		usersListCB = new ComboBox<>();
		pcListCB = new ComboBox<>();
		pcListCB.setPromptText(new PCController().getAllPCData().get(0).getPcId().toString());
		usersListCB.setPromptText(new UserController().getAllUserData().get(0).getUserId().toString());
		pcListCB.setValue(new PCController().getAllPCData().get(0).getPcId().toString());
		usersListCB.setValue(new UserController().getAllUserData().get(0).getUserId().toString());
		pcIdLB = label.setText("Choose Pc ID : ").setFontSize("12").setTextColor("Black").build();
		pageTitleLB = label.setText("Add New Technician Job: ").setFontSize("16").setTextColor("Black").build();
		errorMessageLB = label.setText("").setTextColor("Red").build();
		insertBTN = button.setText("Add New Job").setColor("Green").setFontSize("12").setFontColor("White").build();
		layout = new BorderPane();
		addAllUsers();
		addAllPc();
	}

	public void addAllUsers() {
		for (User user : (new UserController().getAllUserData())) {
			usersListCB.getItems().add(user.getUserId().toString());
		}
	}

	public void addAllPc() {
		for (PC pc : (new PCController().getAllPCData())) {
			pcListCB.getItems().add(pc.getPcId().toString());
		}
	}

	@Override
	protected void addComp() {
		layout.setCenter(formContainer);
		formContainer.add(pageTitleLB, 0, 0);
		formContainer.add(userIdLB, 0, 1);
		formContainer.add(usersListCB, 0, 2);
		formContainer.add(pcIdLB, 0, 3);
		formContainer.add(pcListCB, 0, 4);
		formContainer.add(insertBTN, 0, 5);
		formContainer.add(errorMessageLB, 0, 6);
	}

	@Override
	protected void arrangeComp() {
		layout.setPadding(new Insets(10, 20, 10, 10));
		BorderPane.setAlignment(formContainer, Pos.CENTER);
		GridPane.setMargin(pcListCB, new Insets(10, 0, 20, 0));
		formContainer.setPadding(new Insets(10));
		layout.setPrefWidth(200);

		layout.setBorder(new Border(new javafx.scene.layout.BorderStroke(javafx.scene.paint.Color.BLACK,
				javafx.scene.layout.BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				new javafx.scene.layout.BorderWidths(1, 1, 0, 1))));
	}

	@Override
	protected void action() {
		insertBTN.setOnMouseClicked(e -> {
			String errMsg = jobController.addNewJob(Integer.parseInt(usersListCB.getValue().toString()),
					pcListCB.getValue().toString());
			if (errMsg.equals("Successfully added a new job")) {
				jobView.refreshJobData();
			} else {
				displayAlert(AlertType.ERROR, errMsg);
			}
		});
	}

}
