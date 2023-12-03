package view;

import java.util.ArrayList;
import java.util.List;

import controller.PCBookController;
import controller.PCController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import models.PC;
import models.PCBook;

public class AssignUserToOtherPCView extends Page implements Content {
	BorderPane layout;
	GridPane formContainer;
	Label userIdLB, pageTitleLB, errorMessageLB, pcIdLB, bookIdLB;
	ComboBox<String> PcsCB;
	Button assignBTN;
	PCBookController pcBookController;
	PCBook currentPcBook;
	PcBookView pcBookView;
	PCController pcController;

	public AssignUserToOtherPCView(PCBook currentPcBook, PcBookView pcBookView) {
		// TODO Auto-generated constructor stub
		this.pcBookView = pcBookView;
		this.currentPcBook = currentPcBook;
		initComp();
		addComp();
		arrangeComp();
		action();
	}

	@Override
	public BorderPane getContent() {
		// TODO Auto-generated method stub
		return layout;
	}

	@Override
	protected void initComp() {
		// TODO Auto-generated method stub
		layout = new BorderPane();
		formContainer = new GridPane();
		pcController = new PCController();
		bookIdLB = label.setText("Book ID : " + currentPcBook.getBookId()).setFontSize("12").setTextColor("Black")
				.setPadding(new Insets(5, 0, 5, 0)).build();
		userIdLB = label.setText("User ID : " + currentPcBook.getUserId()).setFontSize("12").setTextColor("Black")
				.setPadding(new Insets(5, 0, 5, 0)).build();
		pageTitleLB = label.setText("Assing User to Other Pc : ").setFontSize("16").setTextColor("Black").build();
		pcIdLB = label.setText("Assing to PC :").setFontSize("12").setTextColor("Black").build();
		pcBookController = new PCBookController();
		PcsCB = new ComboBox<>();
		PcsCB.setPromptText(currentPcBook.getPcId());
		List<String> pcIds = new ArrayList<>();
		for (PC pc : pcController.getAllPCData()) {
			pcIds.add(pc.getPcId());
		}
		PcsCB.getItems().addAll(pcIds);
		errorMessageLB = label.setText("").setTextColor("Red").build();
		assignBTN = button.setText("Assign User").setColor("Green").setFontSize("12").setFontColor("Black").build();
	}

	@Override
	protected void addComp() {
		// TODO Auto-generated method stub
		layout.setCenter(formContainer);
		formContainer.add(pageTitleLB, 0, 1);
		formContainer.add(bookIdLB, 0, 2);
		formContainer.add(userIdLB, 0, 3);
		formContainer.add(pcIdLB, 0, 4);
		formContainer.add(PcsCB, 0, 5);
		formContainer.add(errorMessageLB, 0, 6);
		formContainer.add(assignBTN, 0, 7);
	}

	@Override
	protected void arrangeComp() {
		// TODO Auto-generated method stub
		layout.setPadding(new Insets(10, 20, 10, 10));

		// Center the formContainer in the middle of the screen
		BorderPane.setAlignment(formContainer, Pos.CENTER);

		// Set top margin for pcConditionCB
//		GridPane.setMargin(pcConditionCB, new Insets(10, 0, 20, 0));

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
		assignBTN.setOnMouseClicked(e -> {
			String errMsg = pcBookController.assignUsertoNewPc(currentPcBook.getBookId(), PcsCB.getValue().toString());
			if (errMsg.equals("Successfully assigned user to a new PC")) {
				errorMessageLB = label.setText(errMsg).setTextColor("Green").setFontSize("12").build();
				pcBookView.refreshBookData();
			} else {
				errorMessageLB = label.setText(errMsg).setTextColor("Red").build();
			}
		});
	}
}
