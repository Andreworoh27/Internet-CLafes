package view;

import controller.PCController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import models.PC;

/*
 * Form for updating PC condition.
 * PC condition can be changed into Usable, Maintenance, or Broken.
 * Only Admin can access this form.
 */

public class UpdatePcFormView extends Page implements Content {

	BorderPane layout;
	GridPane formContainer;
	Label pcConditionLB, pageTitleLB, errorMessageLB, pcIdLB;
	ComboBox<String> pcConditionCB;
	Button updateBTN;
	PCController pcController;
	private PC computer;
	PCView pcView;

	public UpdatePcFormView(PC computer, PCView pcView) {
		this.computer = computer;
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
		pcConditionLB = label.setText("Input Pc ID : ").setFontSize("12").setTextColor("Black").build();
		pageTitleLB = label.setText("Update PC Condition : ").setFontSize("16").setTextColor("Black").build();
		pcConditionCB = new ComboBox<>();
		pcConditionCB.setPromptText(computer.getPcCondition());
		pcConditionCB.getItems().addAll("Usable", "Maintenance", "Broken");
		pcIdLB = label.setText("Pc ID : " + computer.getPcId()).build();
		errorMessageLB = label.setText("").setTextColor("Red").build();
		updateBTN = button.setText("Update PC Condition").setColor("Green").setFontSize("12").setFontColor("White")
				.build();
		layout = new BorderPane();
	}

	@Override
	protected void addComp() {
		layout.setCenter(formContainer);
		formContainer.add(pageTitleLB, 0, 0);
		formContainer.add(pcIdLB, 0, 1);
		formContainer.add(pcConditionLB, 0, 2);
		formContainer.add(pcConditionCB, 0, 3);
		formContainer.add(errorMessageLB, 0, 4);
		formContainer.add(updateBTN, 0, 5);
	}

	@Override
	protected void arrangeComp() {
		layout.setPadding(new Insets(10, 20, 10, 10));
		BorderPane.setAlignment(formContainer, Pos.CENTER);
		formContainer.setPadding(new Insets(10));
		layout.setPrefWidth(200);

		layout.setBorder(new Border(new javafx.scene.layout.BorderStroke(javafx.scene.paint.Color.BLACK,
				javafx.scene.layout.BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				new javafx.scene.layout.BorderWidths(1, 1, 0, 1))));
	}

	@Override
	protected void action() {
		updateBTN.setOnMouseClicked(e -> {
			String errMsg = pcController.updatePCCondition(computer.getPcId(), pcConditionCB.getValue());
			if (!errMsg.equals("Successfully update PC condition")) {
				displayAlert(AlertType.ERROR, errMsg);
			} else {
				displayAlert(AlertType.INFORMATION, errMsg);
				pcView.refreshPCContainer();
			}
		});
	}

	@Override
	public BorderPane getContent() {
		return layout;
	}
	
}
