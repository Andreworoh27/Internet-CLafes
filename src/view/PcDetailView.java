package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import models.PC;

public class PcDetailView extends Page implements Content {
	PC computer;
	BorderPane layout;
	Label pcId, pcStatus, title;

	GridPane banner;

	public PcDetailView(PC computer) {
		// TODO Auto-generated constructor stub
		this.computer = computer;
		initComp();
		addComp();
		arrangeComp();
	}

	@Override
	protected void initComp() {
		// TODO Auto-generated method stub
		layout = new BorderPane();
		pcId = new Label();
		pcStatus = new Label();
		title = new Label();
		title.setText("Pc Detail : ");
		pcId.setText("Pc Id : " + computer.getPcId());
		pcStatus.setText("Pc condition : " + computer.getPcCondition());
		banner = new GridPane();
	}

	@Override
	protected void addComp() {
		// TODO Auto-generated method stub
		Image icon = new Image(
				getClass().getResource("/resource/" + computer.getPcCondition() + ".png").toExternalForm());
		ImageView imageView = new ImageView(icon);
		imageView.setFitWidth(100);
		imageView.setFitHeight(100);

		layout.setCenter(banner);
		layout.setPrefSize(150, 450);
		banner.add(title, 0, 1);
		banner.add(pcId, 0, 2);
		banner.add(pcStatus, 0, 3);
		banner.add(imageView, 0, 4);
	}

	@Override
	protected void arrangeComp() {
		// TODO Auto-generated method stub
		// Add padding to the right side of the BorderPane
		layout.setPadding(new Insets(0, 20, 0, 0));

		// Set margin for the title label
		GridPane.setMargin(title, new Insets(180, 0, 20, 0));

		// Set alignment for the center of the BorderPane
		BorderPane.setAlignment(banner, Pos.CENTER);

	}

	@Override
	protected void action() {
		// TODO Auto-generated method stub

	}

	@Override
	public BorderPane getContent() {
		// TODO Auto-generated method stub
		return layout;
	}

}
