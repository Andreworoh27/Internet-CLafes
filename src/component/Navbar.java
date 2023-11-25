package component;

import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Navbar {
	
	VBox sideBar;
	ButtonBuilder button;
	
	public Navbar() {
		this.sideBar = new VBox();
		button = new ButtonBuilder();
        sideBar.setMinWidth(200);
        sideBar.setSpacing(10);
		sideBar.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-style: solid;");

	}

	public VBox generateMenu(String[] menu) {
		for (String string : menu) {
			Button menuButton = button.setText(string).setImage(string).setPadding(10).setColor("transparent").setFontColor("Black").setFontSize("20").build();

	        sideBar.getChildren().add(menuButton);
		}
		return sideBar;
	}
}
