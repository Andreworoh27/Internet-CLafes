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

public class RegisterView extends Page{

	Scene register;
	
	BorderPane borderContainer;
	GridPane gridContainer;
	
	Label registerLB, usernameLB, passwordLB, ageLB, confirmLB, errorLB;
	TextField usernameTF, ageTF;
	PasswordField passwordPF, confirmPF;
	Button registerBTN, gotoLoginBTN;
	
	public RegisterView() {
		initComp();
		addComp();
		arrangeComp();
		action();
		displayView(register);
	}
	
	@Override
	protected void initComp() {
		borderContainer = new BorderPane();
		gridContainer = new GridPane();
		
		registerLB = label.setText("Register").setFontSize("20").build();
		usernameLB = label.setText("Username:").build();
		passwordLB = label.setText("Password:").build();
		confirmLB = label.setText("Confirm Password:").build();
		ageLB = label.setText("Age:").build();
		errorLB = label.setText("").setTextColor("Red").build();
		
		usernameTF = tf.setPromptText("").build();
		passwordPF = new PasswordField();
		confirmPF = new PasswordField();
		ageTF = new TextField();
		
		registerBTN = button.setText("Register").setFontSize("15").build();
		gotoLoginBTN = button.setText("Login Here!")
							.setColor("transparent")
							.setFontColor("Black")
							.setFontSize("12")
							.build();
		
		register = new Scene(borderContainer, 1000, 600);
	}

	@Override
	protected void addComp() {
		borderContainer.setCenter(gridContainer);
		addGirdContainer();
	}

	private void addGirdContainer() {
		gridContainer.add(registerLB, 0, 0);
		gridContainer.add(usernameLB, 0, 1);
		gridContainer.add(usernameTF, 0, 2);
		gridContainer.add(passwordLB, 0, 3);
		gridContainer.add(passwordPF, 0, 4);
		gridContainer.add(confirmLB, 0, 5);
		gridContainer.add(confirmPF, 0, 6);
		gridContainer.add(errorLB, 0, 9);
		gridContainer.add(ageLB, 0, 7);
		gridContainer.add(ageTF, 0, 8);
		gridContainer.add(registerBTN, 0, 10);
		gridContainer.add(gotoLoginBTN, 0, 11);
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
		registerLB.setFont(font);
		registerLB.setPadding(new Insets(20));
	}

	private void setGridPaneAlignment() {
		GridPane.setHalignment(registerLB, HPos.CENTER);
		GridPane.setHalignment(usernameLB, HPos.CENTER);
		GridPane.setHalignment(usernameTF, HPos.CENTER);
		GridPane.setHalignment(passwordLB, HPos.CENTER);
		GridPane.setHalignment(passwordPF, HPos.CENTER);
		GridPane.setHalignment(confirmLB, HPos.CENTER);
		GridPane.setHalignment(confirmPF, HPos.CENTER);
		GridPane.setHalignment(ageLB, HPos.CENTER);
		GridPane.setHalignment(ageTF, HPos.CENTER);
		GridPane.setHalignment(registerBTN, HPos.CENTER);
		GridPane.setHalignment(gotoLoginBTN, HPos.CENTER);
	}

	@Override
	protected void action() {
		registerBTN.setOnAction(e -> {
			String username = usernameTF.getText(); 
			String password = passwordPF.getText();
			String confirmPassword = confirmPF.getText();
			String ageText = ageTF.getText();
			try {
			    Integer age = Integer.parseInt(ageText);
			    String status = uc.register(username, password, confirmPassword, age);
			    if(status.equals("Successfully add a new user")) {
			    	displayAlert(AlertType.INFORMATION, status);
			    	new LoginView();
			    } else {
			    	displayAlert(AlertType.ERROR, status);
			    }
			} catch (NumberFormatException e1) {
				displayAlert(AlertType.ERROR, "Age must be numeric");
			}
		});
		
		gotoLoginBTN.setOnAction(e -> {
			new LoginView();
		});
	}
	
}
