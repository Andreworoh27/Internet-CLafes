package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import models.User;

public class LoginView extends Page {

	Scene login;

	BorderPane borderContainer;
	GridPane gridContainer;

	Label loginLB, usernameLB, passwordLB, errorLB;
	TextField usernameTF;
	PasswordField passwordPF;
	Button loginBTN, gotoRegisterBTN;

	public LoginView() {
		user = new User();
		initComp();
		addComp();
		arrangeComp();
		action();
		displayView(login);
	}

	@Override
	protected void initComp() {
		borderContainer = new BorderPane();
		gridContainer = new GridPane();
		
		loginLB = label.setText("Login").setFontSize("20").build();
		usernameLB = label.setText("Username :").build();
		passwordLB = label.setText("Password :").build();
		errorLB = label.setText("").setTextColor("Red").build();
		
		usernameTF = tf.build();
		passwordPF = new PasswordField();
		
		loginBTN = button.setText("Login").setFontSize("15").build();
		gotoRegisterBTN = button.setText("Register Here!")
								.setColor("transparent")
								.setFontColor("Black")
								.setFontSize("12")
								.build();
		
		login = new Scene(borderContainer, 1000, 600);
	}

	@Override
	protected void addComp() {
		borderContainer.setCenter(gridContainer);
		addGridContainer();
	}

	private void addGridContainer() {
		gridContainer.add(loginLB, 0, 0);
		gridContainer.add(usernameLB, 0, 1);
		gridContainer.add(usernameTF, 0, 2);
		gridContainer.add(passwordLB, 0, 3);
		gridContainer.add(passwordPF, 0, 4);
		gridContainer.add(errorLB, 0, 5);
		gridContainer.add(loginBTN, 0, 6);
		gridContainer.add(gotoRegisterBTN, 0, 7);
	}

	@Override
	protected void arrangeComp() {
		BorderPane.setAlignment(gridContainer, Pos.CENTER);
		gridContainer.setAlignment(Pos.CENTER);
		gridContainer.setVgap(10);

		setGridPaneAlignment();

		usernameTF.setPrefWidth(250);
		passwordPF.setPrefWidth(250);
		Font font = Font.font("Open Sans", 25);
		loginLB.setFont(font);
		loginLB.setPadding(new Insets(20));
	}

	private void setGridPaneAlignment() {
		GridPane.setHalignment(loginLB, HPos.CENTER);
		GridPane.setHalignment(usernameLB, HPos.CENTER);
		GridPane.setHalignment(usernameTF, HPos.CENTER);
		GridPane.setHalignment(passwordLB, HPos.CENTER);
		GridPane.setHalignment(passwordPF, HPos.CENTER);
		GridPane.setHalignment(loginBTN, HPos.CENTER);
		GridPane.setHalignment(gotoRegisterBTN, HPos.CENTER);
		GridPane.setHalignment(errorLB, HPos.CENTER);
	}

	@Override
	protected void action() {
		loginBTN.setOnAction(e -> {
			String username = usernameTF.getText();
			String password = passwordPF.getText();
			String status = uc.login(username, password);
			if (status.equals("Successfully logged in")) {
				new PCView();
			} else {
				displayAlert(AlertType.ERROR, status);
			}
		});

		gotoRegisterBTN.setOnAction(e -> {
			new RegisterView();
		});
	}

}
