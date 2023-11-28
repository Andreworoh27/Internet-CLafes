package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class JobView extends Page {

	Scene viewAllJob;
	BorderPane layout;
	LayoutView lv;

	public JobView() {
		// TODO Auto-generated constructor stub
		initComp();
		addComp();
		arrangeComp();
		action();
		displayView(viewAllJob);
	}

	@Override
	protected void initComp() {
		// TODO Auto-generated method stub
		layout = new BorderPane();
		lv = new LayoutView();
		layout = lv.getLayout();
		viewAllJob = new Scene(layout, 900, 600);
	}

	@Override
	protected void addComp() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void arrangeComp() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void action() {
		// TODO Auto-generated method stub

	}

}
