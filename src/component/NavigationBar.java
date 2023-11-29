package component;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import view.JobView;
import view.PCView;
import view.ReportView;
import view.CustomerTransactionHistoryView;

public class NavigationBar {
	VBox sideBar;
	ButtonBuilder button;

	public NavigationBar() {
		this.sideBar = new VBox();
		button = new ButtonBuilder();
		sideBar.setPrefWidth(200);
		sideBar.setSpacing(10);
		sideBar.setStyle("-fx-border-color: black; -fx-border-width: 0 1px 0 0; -fx-border-style: solid;");
	}
                                         
	public VBox generateMenu(String[] menu) {
		for (String string : menu) {
			Button menuButton = button.setText(string).setImage(string).setPadding(10).setColor("transparent")
					.setFontColor("Black").setFontSize("20").build();

			menuButton.setOnMouseClicked(e -> {
				switch (menuButton.getText()) {
				case "home":
					new PCView();
					break;
				case "monitor":
					break;
				case "job":
					new JobView();
					break;
				case "transaction":
					new CustomerTransactionHistoryView();
					break;
				case "report":
					new ReportView();
					break;
				default:
					break;
				}
			});
			sideBar.getChildren().add(menuButton);
		}
		return sideBar;
	}
}
