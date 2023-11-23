package view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class PCView extends Page{
	Scene viewPC;
	BorderPane borderContainer;
	GridPane gridContainer;
	Label testLB;
	
	public PCView() {
		initComp();
		addComp();
		arrangeComp();
		action();
		displayView(viewPC);
		
	}

	@Override
	protected void initComp() {
		testLB = new Label("Masuk");
		borderContainer = new BorderPane();
		gridContainer = new GridPane();
		viewPC = new Scene(borderContainer, 1000, 600);
	}

	@Override
	protected void addComp() {
		borderContainer.setCenter(gridContainer);
		addGirdContainer();
	}

	private void addGirdContainer() {
		gridContainer.add(testLB, 0, 0);
	}

	@Override
	protected void arrangeComp() {
		BorderPane.setAlignment(gridContainer, Pos.CENTER);
		gridContainer.setAlignment(Pos.CENTER);
		gridContainer.setVgap(5);
		
		setGridPaneAlignment();
	}

	private void setGridPaneAlignment() {
		GridPane.setHalignment(testLB, HPos.CENTER);
	}

	@Override
	protected void action() {
		// TODO Auto-generated method stub
		
	}
}
