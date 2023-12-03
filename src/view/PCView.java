package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import models.PC;

public class PCView extends Page {
	Scene viewPC;
	BorderPane layout;
	GridPane gridContainer;
	Label testLB;
	LayoutView lv;
	PC pc;
	FlowPane pcContainer;
	Button addPcButton;
	Button pcBookViewButton;
	Button pcCancelViewButton;
	Button pcFinishViewButton;

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
		addPcButton = button.setText("Add New Pc").setColor("Green").setFontSize("14").setFontColor("White")
				.setPadding(5).setPrefWidth(100).setPadding(10).build();
		pcFinishViewButton = button.setText("Finish").setFontSize("14").setFontColor("White").setPadding(5)
				.setPrefWidth(100).setPadding(10).build();
	}

	@Override
	protected void addComp() {
		layout.setCenter(gridContainer);
		addGridContainer();

		if (Page.user != null && Page.user.getUserRole().equals("Operator")) {
			pcBookViewButton = new Button("View PC Book");
			pcCancelViewButton = new Button("Cancel PC Book");
			addContainerForOperator();
		}
	}

	private void addContainerForOperator() {
		gridContainer.add(pcBookViewButton, 0, 15);
		gridContainer.add(pcCancelViewButton, 0, 16);
		gridContainer.add(pcFinishViewButton, 0, 17);
	}

	private void addGridContainer() {
		if (user.getUserRole().equals("Admin")) {
			gridContainer.add(addPcButton, 0, 0);
			gridContainer.add(pcContainer, 0, 1);
		} else {
			gridContainer.add(pcContainer, 0, 0);
		}
		addAllPc();
	}

	public void refreshPCContainer() {
		pcContainer.getChildren().clear(); // Clear existing children

		// Add new PCs to pcContainer
		addAllPc();
	}

	private void addAllPc() {
		for (PC computer : pc.getAllPCData()) {
			GridPane newComputer = card.generateCard(computer, layout, this);
			newComputer.setOnMouseClicked(event -> handleCardOnClickEvent(computer));
			pcContainer.getChildren().add(newComputer);
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
		pcContainer.setPadding(new Insets(10));// Set margin to the right of the "Add New PC" button

	}

	private void setGridPaneAlignment() {
		GridPane.setHalignment(testLB, HPos.CENTER);
	}

	private void handleCardOnClickEvent(PC computer) {
		PcDetailView pcDetailView = new PcDetailView(computer);
		layout.setRight(pcDetailView.getContent());
	}

	public Page getParentPage() {
		return this;
	}

	@Override
	protected void action() {
		// TODO Auto-generated method stub
		addPcButton.setOnMouseClicked(e -> {
			AddNewPcFormView newPcFormView = new AddNewPcFormView(this);
			layout.setRight(newPcFormView.getContent());
		});

		if (pcBookViewButton != null) {
			pcBookViewButton.setOnAction(e -> {
				try {
					System.out.println("Button masuk");
					new PcBookView();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
		}
		if (pcCancelViewButton != null) {
			pcCancelViewButton.setOnAction(e -> {
				try {
					new PcCancelView();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
		}
		if (pcFinishViewButton != null) {
			pcFinishViewButton.setOnAction(e -> {
				try {
					new PcFinishView();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
		}

	}
}
