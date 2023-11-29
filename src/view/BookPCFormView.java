package view;

import java.sql.Date;

import controller.PCBookController;
import controller.ReportController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import models.PC;

public class BookPCFormView extends Page implements Content{
	BorderPane layout;
	GridPane formContainer;
	Label pcIdLB, pageTitleLB, errorMessageLB;
	Button bookButton;
	PCBookController pbc;
	DatePicker datePicker;
	PCView pcView;
	PC computer;
	
	
	public BookPCFormView(PC computer, PCView pcView) {
		this.computer = computer;
		this.pcView = pcView;
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
		formContainer = new GridPane();
		pbc = new PCBookController();
		pageTitleLB = label.setText("Report PC : "+computer.getPcId()).setFontSize("16").setTextColor("Black").build();
		pcIdLB = label.setText("Insert Booking Date : ").setFontSize("12").setTextColor("Black").build();
		datePicker = new DatePicker();
		errorMessageLB = label.setText("").setTextColor("Red").build();
		bookButton = button.setText("Book").setColor("Green").setFontSize("12").setFontColor("White").build();
		layout = new BorderPane();
	}

	@Override
	protected void addComp() {
		// TODO Auto-generated method stub
		layout.setCenter(formContainer);
		formContainer.add(pageTitleLB, 0, 0);
		formContainer.add(pcIdLB, 0, 1);
		formContainer.add(datePicker, 0, 2);
		formContainer.add(errorMessageLB, 0, 3);
		formContainer.add(bookButton, 0, 4);

	}

	@Override
	protected void arrangeComp() {
		layout.setPadding(new Insets(10, 20, 10, 10));

		BorderPane.setAlignment(formContainer, Pos.CENTER);

		GridPane.setMargin(datePicker, new Insets(10, 0, 20, 0));

		formContainer.setPadding(new Insets(10));

		layout.setBorder(new Border(new javafx.scene.layout.BorderStroke(javafx.scene.paint.Color.BLACK,
				javafx.scene.layout.BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				new javafx.scene.layout.BorderWidths(1, 1, 0, 1))));
		
	}

	@Override
	protected void action() {
		
		bookButton.setOnMouseClicked(e -> {
			datePicker.getValue();
			
			displayAlert(AlertType.INFORMATION, pbc.addNewBook(computer.getPcId(), user.getUserId(), Date.valueOf(datePicker.getValue())));
		});
		
	}
}
