package view;

import javafx.scene.layout.BorderPane;

/*
 * Interface for content.
 * View that uses this means it can pass the content to other page.
 * */

public interface Content {
	
	public BorderPane getContent();
	
}
