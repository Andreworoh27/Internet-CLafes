package view;

import component.ButtonBuilder;
import component.LabelBuilder;
import component.Navbar;
import component.TextFieldBuilder;
import controller.UserController;
import controller.ViewController;
import javafx.scene.Scene;
import models.User;

public abstract class Page{
	
<<<<<<< Updated upstream
	public UserController uc = new UserController();
=======
	public static User user;
	public UserController uc = UserController.getInstance();
>>>>>>> Stashed changes
	
	public ButtonBuilder button = new ButtonBuilder();
	public LabelBuilder label = new LabelBuilder();
	public TextFieldBuilder tf = new TextFieldBuilder();
	public Navbar nb = new Navbar();
	
	
	protected abstract void initComp();
	protected abstract void addComp();
	protected abstract void arrangeComp();
	protected abstract void action();
	
	protected void displayView(Scene currentScene) {
		ViewController.displayPage(currentScene);
	}
	
}
