package component;

import controller.PCBookController;
import controller.PCController;
import controller.ReportController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import models.PC;
import view.BookPCFormView;
import view.PCView;
import view.Page;
import view.ReportPCFormView;
import view.UpdatePcFormView;

public class Card {

	GridPane card;
	VBox buttonPlaceholder;
	ButtonBuilder button = new ButtonBuilder();
	LabelBuilder label = new LabelBuilder();
	PCBookController pbc = new PCBookController();
	ReportController rc = new ReportController();
	PCController pcc = new PCController();
	BorderPane layout;
	PCView pcView;

	public Card() {
		this.card = new GridPane();
		this.buttonPlaceholder = new VBox();
	}

	public GridPane generateCard(PC pc, BorderPane layout, PCView pcView) {
		this.card = new GridPane();
		this.layout = layout;
		this.pcView = pcView;
		this.buttonPlaceholder = new VBox();
		card.setMinWidth(100);
		card.setMinHeight(120);
		card.setPadding(new Insets(10));
		card.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-style: solid; -fx-border-radius: 5;");

		Image icon = new Image(getClass().getResource("/resource/" + pc.getPcCondition() + ".png").toExternalForm());
		ImageView imageView = new ImageView(icon);
		imageView.setFitWidth(100);
		imageView.setFitHeight(100);

		Button book = button.setText("Book").setColor("Green").setFontColor("White").setFontSize("12").setPadding(5)
				.setPrefWidth(100).build();
		Button report = button.setText("Report").setColor("Red").setFontColor("White").setFontSize("12").setPadding(5)
				.setPrefWidth(100).build();
		Button update = button.setText("Update").setColor("Green").setFontColor("White").setFontSize("12").setPadding(5)
				.setPrefWidth(100).build();
		Button delete = button.setText("Delete").setColor("Red").setFontColor("White").setFontSize("12").setPadding(5)
				.setPrefWidth(100).build();

		addAction(pc, book, report, update, delete);

		if (Page.user.getUserRole().equals("Customer"))
			buttonPlaceholder.getChildren().addAll(book, report);
		if (Page.user.getUserRole().equals("Operator"))
			buttonPlaceholder.getChildren().addAll(report);
		if (Page.user.getUserRole().equals("Admin"))
			buttonPlaceholder.getChildren().addAll(update, delete);

		buttonPlaceholder.setSpacing(5);
		card.add(label.setText(pc.getPcId()).setFontSize("15").setTextColor("Black").build(), 0, 0);
		card.add(label.setText(pc.getPcCondition()).setFontSize("12").setTextColor("Black").build(), 0, 1);
		card.add(imageView, 0, 2);
		card.add(buttonPlaceholder, 0, 3);
		buttonPlaceholder.getChildren().removeAll();
		return card;
	}

	private void addAction(PC pc, Button book, Button report, Button update, Button delete) {
		book.setOnAction(e -> {
			BookPCFormView bookPCFormView = new BookPCFormView(pc, pcView);
			layout.setRight(bookPCFormView.getContent());
		});

		report.setOnAction(e -> {
			ReportPCFormView reportPCFormView = new ReportPCFormView(pc, pcView);
			layout.setRight(reportPCFormView.getContent());
		});

		delete.setOnAction(e -> {
			pcc.deletePC(pc.getPcId());
			new PCView();
		});

		update.setOnAction(e -> {
			UpdatePcFormView updatePcFormView = new UpdatePcFormView(pc, pcView);
			layout.setRight(updatePcFormView.getContent());
		});

	}
}
