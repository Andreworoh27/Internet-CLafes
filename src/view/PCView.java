package view;

import java.awt.Color;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.PC;

public class PCView extends Page{
	Scene viewPC;
	BorderPane layout;
	GridPane gridContainer;
	Label testLB;
	LayoutView lv;
	PC pc;
	FlowPane pcContainer;
	
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
		pcContainer = new FlowPane();
		pc = new PC();
		lv = new LayoutView();
		layout = lv.getLayout();
		viewPC = new Scene(layout, 900, 600);
	}

	@Override
	protected void addComp() {
		layout.setCenter(gridContainer);
		addGirdContainer();
	}

	private void addGirdContainer() {
		gridContainer.add(pcContainer, 0, 0);

		for (PC computer : pc.getAllPCData()) {
			pcContainer.getChildren().add(card.generateCard(computer));
		}
	}

	@Override
	protected void arrangeComp() {
		BorderPane.setAlignment(gridContainer, Pos.CENTER);
		gridContainer.setAlignment(Pos.CENTER);
		gridContainer.setVgap(5);
		pcContainer.setVgap(10);
		pcContainer.setHgap(10);
		pcContainer.setPrefWidth(viewPC.getWidth());
		pcContainer.setPadding(new Insets(10));
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
