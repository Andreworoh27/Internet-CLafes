package view;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import models.User;

public class UpdateStaffRoleFormView extends Page implements Content {
	BorderPane layout;
	GridPane formContainer;
	Label staffIdLB, pageTitleLB, errorMessageLB, staffRoleLB;
	ComboBox<String> staffRoleCB;
	Button updateBTN;
	UserController userController;
	User currentStaff;
	StaffView staffView;

	public UpdateStaffRoleFormView(User currentStaff, StaffView staffView) {
		// TODO Auto-generated constructor stub
		this.staffView = staffView;
		this.currentStaff = currentStaff;
		initComp();
		addComp();
		arrangeComp();
		action();
	}

	@Override
	public BorderPane getContent() {
		// TODO Auto-generated method stub
		return layout;
	}

	@Override
	protected void initComp() {
		// TODO Auto-generated method stub
		layout = new BorderPane();
		formContainer = new GridPane();
		staffIdLB = label.setText("Staff ID : " + currentStaff.getUserId()).setFontSize("12").setTextColor("Black")
				.build();
		pageTitleLB = label.setText("Update Staff Role : ").setFontSize("16").setTextColor("Black").build();
		staffRoleLB = label.setText("Role :").setFontSize("12").setTextColor("Black").build();
		staffIdLB = label.setText("Staff ID : " + currentStaff.getUserId()).setFontSize("12").setTextColor("Black")
				.build();
		userController = new UserController();
		staffRoleCB = new ComboBox<>();
		staffRoleCB.setPromptText(currentStaff.getUserRole());
		staffRoleCB.getItems().addAll("Admin", "Operator", "Computer Technician");
		errorMessageLB = label.setText("").setTextColor("Red").build();
		updateBTN = button.setText("Update Staff Role").setColor("Green").setFontSize("12").setFontColor("Black")
				.build();
	}

	@Override
	protected void addComp() {
		// TODO Auto-generated method stub
		layout.setCenter(formContainer);
		formContainer.add(pageTitleLB, 0, 1);
		formContainer.add(staffIdLB, 0, 2);
		formContainer.add(staffRoleLB, 0, 3);
		formContainer.add(staffRoleCB, 0, 4);
		formContainer.add(errorMessageLB, 0, 5);
		formContainer.add(updateBTN, 0, 6);
	}

	@Override
	protected void arrangeComp() {
		// TODO Auto-generated method stub
		layout.setPadding(new Insets(10, 20, 10, 10));

		// Center the formContainer in the middle of the screen
		BorderPane.setAlignment(formContainer, Pos.CENTER);

		// Set top margin for pcConditionCB
//		GridPane.setMargin(pcConditionCB, new Insets(10, 0, 20, 0));

		// Set padding for the formContainer
		formContainer.setPadding(new Insets(10));

		// Add right border
		layout.setBorder(new Border(new javafx.scene.layout.BorderStroke(javafx.scene.paint.Color.BLACK,
				javafx.scene.layout.BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				new javafx.scene.layout.BorderWidths(1, 1, 0, 1))));
	}

	@Override
	protected void action() {
		// TODO Auto-generated method stub
		updateBTN.setOnMouseClicked(e -> {
			String errMsg = userController.changeUserRole(currentStaff.getUserId(), staffRoleCB.getValue().toString());
			if (errMsg.equals("Successfully changed user role")) {
				errorMessageLB = label.setText(errMsg).setTextColor("Green").setFontSize("12").build();
				staffView.refreshStaffData();
			} else {
				errorMessageLB = label.setText(errMsg).setTextColor("Red").build();
			}
		});
	}
}
