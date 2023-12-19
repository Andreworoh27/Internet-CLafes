package view;

import controller.PCController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import models.PC;

public class PCView extends Page {
	
	Scene viewPC;
	
	LayoutView lv;
	BorderPane layout;
	GridPane gridContainer;
	ScrollPane scrollContainer;
	FlowPane pcContainer, bookContainer;
	
	Button addPcButton;
	Button pcBookViewButton;
	Button pcCancelViewButton;
	Button pcFinishViewButton;
	
	PCController pcc;

	public PCView() {
		initComp();
		addComp();
		arrangeComp();
		action();
		displayView(viewPC);
	}

	@Override
	protected void initComp() {
		pcc = new PCController();
		gridContainer = new GridPane();
		scrollContainer = new ScrollPane();
		pcContainer = new FlowPane();
		bookContainer = new FlowPane();
		lv = new LayoutView();
		layout = lv.getLayout();
		viewPC = new Scene(layout, 1000, 600);
		
		addPcButton = button.setText("Add New Pc")
							.setColor("Green")
							.setFontSize("14")
							.setFontColor("WhitFe")
							.setPrefWidth(100)
							.setPadding(10)
							.build();
		
		pcFinishViewButton = button.setText("Finish Book")
								   .setColor("Green")
								   .setFontSize("14")
								   .setFontColor("White")
								   .setPrefWidth(150)
								   .setPadding(10)
								   .build();
		
		pcCancelViewButton = button.setText("Cancel Book")
								   .setColor("Green")
								   .setFontSize("14")
								   .setFontColor("White")
								   .setPrefWidth(150)
								   .setPadding(10)
								   .build();
		
		pcBookViewButton = button.setText("View All Book")
								 .setColor("Green")
							     .setFontSize("14")
							     .setFontColor("White")
							     .setPrefWidth(150)
							     .setPadding(10)
							     .build();
	}

	@Override
	protected void addComp() {
		layout.setCenter(scrollContainer);
		scrollContainer.setContent(gridContainer);
		addGridContainer();
		bookContainer.getChildren().addAll(pcBookViewButton, pcCancelViewButton, pcFinishViewButton);
	}

	private void addGridContainer() {
		if (user.getUserRole().equals("Admin")) {
			gridContainer.add(addPcButton, 0, 0);
			gridContainer.add(pcContainer, 0, 1);
		} else if (user.getUserRole().equals("Operator")) {
			gridContainer.add(bookContainer, 0, 0);
			gridContainer.add(pcContainer, 0, 1);
		} else {
			gridContainer.add(pcContainer, 0, 0);
		}
		addAllPc();
	}

	public void refreshPCContainer() {
		pcContainer.getChildren().clear();
		addAllPc();
	}

	private void addAllPc() {
		for (PC computer : pcc.getAllPCData()) {
			GridPane newComputer = card.generateCard(computer, layout, this);
			newComputer.setOnMouseClicked(event -> handleCardOnClickEvent(computer));
			pcContainer.getChildren().add(newComputer);
		}
	}

	@Override
	protected void arrangeComp() {
		BorderPane.setAlignment(scrollContainer, Pos.CENTER);
		scrollContainer.setFitToWidth(true);
		gridContainer.setPadding(new Insets(20));
		gridContainer.setAlignment(Pos.CENTER);
		gridContainer.setVgap(10);
		pcContainer.setVgap(10);
		pcContainer.setHgap(10);
		pcContainer.setPrefWrapLength(viewPC.getWidth() - 240);
		bookContainer.setHgap(10);
		bookContainer.setVgap(10);
		scrollContainer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
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
		addPcButton.setOnMouseClicked(e -> {
			AddNewPcFormView newPcFormView = new AddNewPcFormView(this);
			layout.setRight(newPcFormView.getContent());
		});

		if (pcBookViewButton != null) {
			pcBookViewButton.setOnAction(e -> {
				try {
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
