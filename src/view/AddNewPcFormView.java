package view;

import controller.PCController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;

public class AddNewPcFormView extends Page implements Content {
	BorderPane layout;
	GridPane formContainer;
	Label pcIdLB, pageTitleLB, errorMessageLB;
	TextField pcIdTF;
	Button insertBTN;
	PCController pcController;
	PCView pcView;

	public AddNewPcFormView(PCView pcView) {
		this.pcView = pcView;
		initComp();
		addComp();
		arrangeComp();
		action();
	}

	@Override
	protected void initComp() {
		// TODO Auto-generated method stub
		formContainer = new GridPane();
		pcController = new PCController();
		pcIdLB = label.setText("Input Pc ID : ").setFontSize("12").setTextColor("Black").build();
		pageTitleLB = label.setText("Insert New PC : ").setFontSize("16").setTextColor("Black").build();
		pcIdTF = tf.setPromptText("ex : PC001").build();
		errorMessageLB = label.setText("").setTextColor("Red").build();
		insertBTN = button.setText("Add New Pc").setColor("Green").setFontSize("12").setFontColor("Black").build();
		layout = new BorderPane();
	}

	@Override
	protected void addComp() {
		// TODO Auto-generated method stub
		layout.setCenter(formContainer);
		formContainer.add(pageTitleLB, 0, 0);
		formContainer.add(pcIdLB, 0, 1);
		formContainer.add(pcIdTF, 0, 2);
		formContainer.add(insertBTN, 0, 4);
		formContainer.add(errorMessageLB, 0, 3);
	}

	@Override
	protected void arrangeComp() {
		layout.setPadding(new Insets(10, 20, 10, 10));

		// Center the formContainer in the middle of the screen
		BorderPane.setAlignment(formContainer, Pos.CENTER);

		// Set top margin for pcIdTF
		GridPane.setMargin(pcIdTF, new Insets(10, 0, 20, 0));

		// Set padding for the formContainer
		formContainer.setPadding(new Insets(10));

		// Add right border
		layout.setBorder(new Border(new javafx.scene.layout.BorderStroke(javafx.scene.paint.Color.BLACK,
				javafx.scene.layout.BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				new javafx.scene.layout.BorderWidths(1, 1, 0, 1))));
	}

	@Override
	protected void action() {
		// TODO Auto-generated method stub
		insertBTN.setOnMouseClicked(e -> {
			String pcId = pcIdTF.getText().toString();
			String errMsg = pcController.addNewPC(pcId);
			if (!errMsg.isEmpty()) {
				errorMessageLB.setText(errMsg);
			} else {
				errorMessageLB.setText("");
				// Get the parent PCView and refresh the PCContainer
				pcView.refreshPCContainer();
			}
		});
	}

	@Override
	public BorderPane getContent() {
		// TODO Auto-generated method stub
		return layout;
	}

}
