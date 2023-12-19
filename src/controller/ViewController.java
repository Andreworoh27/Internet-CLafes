package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * Manage routing for pages.
 * Displaying pages for each changes. 
 * */

public class ViewController {
	
	public static Stage stage;
	
	public static void displayPage(Scene newScene) {
		stage.setScene(newScene);
		stage.show();
	}
	
}
