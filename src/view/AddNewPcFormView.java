package view;

import controller.PCController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;

/*
 * Form for adding new PC.
 * Only Admin can access this form.
 * Admin can add new PC.
 * */

public class AddNewPcFormView extends Page implements Content {
	
	BorderPane layout;
	GridPane formContainer;
	Label pcIdLB, pageTitleLB;
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
		formContainer = new GridPane();
		pcController = new PCController();
		pcIdLB = label.setText("Input Pc ID : ").setFontSize("12").setTextColor("Black").build();
		pageTitleLB = label.setText("Insert New PC : ").setFontSize("16").setTextColor("Black").build();
		pcIdTF = tf.setPromptText("ex : PC001").build();
		insertBTN = button.setText("Add New PC").setColor("Green").setFontSize("12").setFontColor("White").build();
		layout = new BorderPane();
	}

	@Override
	protected void addComp() {
		layout.setCenter(formContainer);
		formContainer.add(pageTitleLB, 0, 0);
		formContainer.add(pcIdLB, 0, 1);
		formContainer.add(pcIdTF, 0, 2);
		formContainer.add(insertBTN, 0, 4);
	}

	@Override
	protected void arrangeComp() {
		layout.setPadding(new Insets(10, 20, 10, 10));
		BorderPane.setAlignment(formContainer, Pos.CENTER);
		GridPane.setMargin(pcIdTF, new Insets(10, 0, 20, 0));
		formContainer.setPadding(new Insets(10));
		layout.setPrefWidth(200);

		layout.setBorder(new Border(new javafx.scene.layout.BorderStroke(javafx.scene.paint.Color.BLACK,
				javafx.scene.layout.BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				new javafx.scene.layout.BorderWidths(1, 1, 0, 1))));
	}

	@Override
	protected void action() {
		insertBTN.setOnMouseClicked(e -> {
			String pcId = pcIdTF.getText().toString();
			String errMsg = pcController.addNewPC(pcId);
			if (!errMsg.equals("Successfully add a new PC")) {
				displayAlert(AlertType.ERROR, errMsg);
			} else {
				pcView.refreshPCContainer();
			}
		});
	}

	@Override
	public BorderPane getContent() {
		return layout;
	}

}
