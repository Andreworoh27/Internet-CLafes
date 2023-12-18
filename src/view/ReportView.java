package view;

import java.sql.Date;
import java.util.List;

import component.LabelBuilder;
import controller.ReportController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import models.Report;

public class ReportView extends Page {
	
	Scene reportScene;
	
	LayoutView lv;
	BorderPane layout;
	VBox content;
	
	Label title;
	TableView<Report> reportTable;
	
	ReportController rc;
	LabelBuilder lb;
	
	public ReportView() {
		initComp();
		addComp();
		arrangeComp();
		action();
		displayView(reportScene);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void initComp() {
		lv = new LayoutView();
		layout = lv.getLayout();
		content = new VBox();
		lb = new LabelBuilder();
		title = lb.setText("PC Report").setFontSize("20").build();
		reportTable = new TableView<Report>();
		buildReportTable();
		reportScene = new Scene(layout, 1000, 600);
	}

	@Override
	protected void addComp() {
		content.getChildren().addAll(title, reportTable);
		layout.setCenter(content);
	}

	@Override
	protected void arrangeComp() {
		content.setSpacing(10);
		content.setPadding(new Insets(20));
	}

	@Override
	protected void action() {
		getAllPCBook();
	}
	
	private void getAllPCBook() {
		rc = new ReportController();
		List<Report> pcBook = rc.getAllReportData();
		ObservableList<Report> data = FXCollections.observableArrayList(pcBook);
		reportTable.setItems(data);
	}
	
	private void buildReportTable() {
		int width = 760 / 5;
		TableColumn<Report, Integer> reportIdCol = new TableColumn<>("Report ID");
		reportIdCol.setCellValueFactory(new PropertyValueFactory<>("reportId"));
		reportIdCol.setPrefWidth(width - 70);

		TableColumn<Report, String> pcIdCol = new TableColumn<>("PC ID");
		pcIdCol.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		pcIdCol.setPrefWidth(width);
		
		TableColumn<Report, String> roleIdCol = new TableColumn<>("User Role");
		roleIdCol.setCellValueFactory(new PropertyValueFactory<>("userRole"));
		roleIdCol.setPrefWidth(width);

		TableColumn<Report, String> reportNotesCol = new TableColumn<>("Report Notes");
		reportNotesCol.setCellValueFactory(new PropertyValueFactory<>("reportNotes"));
		reportNotesCol.setPrefWidth(width + 70);
		
		TableColumn<Report, Date> reportDateCol = new TableColumn<>("Report Date");
		reportDateCol.setCellValueFactory(new PropertyValueFactory<>("reportDate"));
		reportDateCol.setPrefWidth(width);
		
		reportTable.getColumns().addAll(reportIdCol, pcIdCol, roleIdCol, reportNotesCol, reportDateCol);
	}
	
}
