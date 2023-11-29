package view;

import java.sql.Date;
import java.util.List;

import controller.ReportController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import models.Report;

public class ReportView extends Page{
	Scene reportScene;
	
	LayoutView lv;
	BorderPane layout;
	
	TableView<Report> reportTable;
	
	ReportController rc;
	
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
		reportTable = new TableView<Report>();
		TableColumn<Report, Integer> reportIdCol = new TableColumn<>("Report ID");
		reportIdCol.setCellValueFactory(new PropertyValueFactory<>("reportId"));
		reportIdCol.setPrefWidth(160);

		TableColumn<Report, String> pcIdCol = new TableColumn<>("PC ID");
		pcIdCol.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		pcIdCol.setPrefWidth(160);
		
		TableColumn<Report, String> roleIdCol = new TableColumn<>("User Role");
		roleIdCol.setCellValueFactory(new PropertyValueFactory<>("userRole"));
		roleIdCol.setPrefWidth(160);

		TableColumn<Report, String> reportNotesCol = new TableColumn<>("Report Notes");
		reportNotesCol.setCellValueFactory(new PropertyValueFactory<>("reportNotes"));
		reportNotesCol.setPrefWidth(160);
		
		TableColumn<Report, Date> reportDateCol = new TableColumn<>("Report Date");
		reportDateCol.setCellValueFactory(new PropertyValueFactory<>("reportDate"));
		reportDateCol.setPrefWidth(160);
		
		reportTable.getColumns().addAll(reportIdCol, pcIdCol, roleIdCol, reportNotesCol, reportDateCol);
		reportScene = new Scene(layout, 1000, 600);
	}

	@Override
	protected void addComp() {
		layout.setCenter(reportTable);
	}

	@Override
	protected void arrangeComp() {
		// TODO Auto-generated method stub
		
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
}
