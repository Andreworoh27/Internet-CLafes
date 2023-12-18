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
		this.computer = computer;
		initComp();
		addComp();
		arrangeComp();
	}

	@Override
	protected void initComp() {
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
		layout.setPadding(new Insets(0, 20, 0, 0));
		GridPane.setMargin(title, new Insets(180, 0, 20, 0));
		BorderPane.setAlignment(banner, Pos.CENTER);

	}

	@Override
	protected void action() {}

	@Override
	public BorderPane getContent() {
		return layout;
	}

}
