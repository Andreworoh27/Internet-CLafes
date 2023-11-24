package view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LayoutView extends Page implements Layout{

	BorderPane borderContainer;
	VBox navBar;
	HBox statusBar;
	
	public LayoutView() {
		
		initComp();
		addComp();
		arrangeComp();
		action();
	}
	
	@Override
	protected void initComp() {
		borderContainer = new BorderPane();
		String[] menu = {"home", "monitor"};
		navBar = nb.generateMenu(menu);
		statusBar = new HBox();
	}

	@Override
	protected void addComp() {
		borderContainer.setTop(statusBar);
		borderContainer.setLeft(navBar);
		
	}

	@Override
	protected void arrangeComp() {
	}

	@Override
	protected void action() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BorderPane getLayout() {
		// TODO Auto-generated method stub
		return borderContainer;
	}

}
