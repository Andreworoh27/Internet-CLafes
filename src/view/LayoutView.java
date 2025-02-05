package view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/*
 * Display and control navbar from the compoenent so that it can be template for other pages.
 * */

public class LayoutView extends Page implements Layout {

	BorderPane borderContainer;
	VBox navBar;
	HBox statusBar;

	public LayoutView() {
		initComp();
		addComp();
		arrangeComp();
		action();
	}

	@Override
	protected void initComp() {
		borderContainer = new BorderPane();
		String[] nav = null;
		if (user.getUserRole().equalsIgnoreCase("Admin")) {
			String[] menu = { "home", "job", "transaction", "report", "staff", "logout" };
			nav = menu.clone();
		} else if (user.getUserRole().equalsIgnoreCase("Customer")) {
			String[] menu = { "home", "transaction", "logout" };
			nav = menu.clone();
		} else if (user.getUserRole().equalsIgnoreCase("Operator")) {
			String[] menu = { "home", "logout" };
			nav = menu.clone();
		} else if (user.getUserRole().equalsIgnoreCase("Computer Technician")) {
			String[] menu = { "home", "job", "logout" };
			nav = menu.clone();
		}

		navBar = nb.generateMenu(nav);
		statusBar = new HBox();
	}

	@Override
	protected void addComp() {
		borderContainer.setTop(statusBar);
		borderContainer.setLeft(navBar);
	}

	@Override
	protected void arrangeComp() {}

	@Override
	protected void action() {}

	@Override
	public BorderPane getLayout() {
		return borderContainer;
	}

}
