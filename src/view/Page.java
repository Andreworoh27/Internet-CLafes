package view;

import component.ButtonBuilder;
import component.Card;
import component.LabelBuilder;
import component.NavigationBar;
import component.TextFieldBuilder;
import controller.UserController;
import controller.ViewController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import models.User;

public abstract class Page{

	public static User user;
	public UserController uc = new UserController();
	
	public ButtonBuilder button = new ButtonBuilder();
	public LabelBuilder label = new LabelBuilder();
	public TextFieldBuilder tf = new TextFieldBuilder();
	public NavigationBar nb = new NavigationBar();
	public Card card = new Card();
	
	
	protected abstract void initComp();
	protected abstract void addComp();
	protected abstract void arrangeComp();
	protected abstract void action();
	
	protected void displayView(Scene currentScene) {
		ViewController.displayPage(currentScene);
	}
	
	public static void displayAlert(AlertType alertType, String errorMessage) {
		Alert alert = new Alert(alertType);
		alert.setContentText(errorMessage);
		alert.showAndWait();
	}
	
}
