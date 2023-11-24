package view;

import java.awt.Color;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PCView extends Page{
	Scene viewPC;
	BorderPane layout;
	GridPane gridContainer;
	Label testLB;
	LayoutView lv;
	
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
		gridContainer = new GridPane();
		lv = new LayoutView();
		layout = lv.getLayout();
		viewPC = new Scene(layout, 1000, 600);
	}

	@Override
	protected void addComp() {
		layout.setRight(gridContainer);
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
		gridContainer.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-style: solid;");


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
