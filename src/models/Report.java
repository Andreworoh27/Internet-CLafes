package models;

public class Report {
    private String reportId;
    private String pcId;
    private String reportNotes;

    public Report() {

    }

    public Report(String reportId, String pcId, String reportNotes) {
        this.reportId = reportId;
        this.pcId = pcId;
        this.reportNotes = reportNotes;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public void setReportNotes(String reportNotes) {
        this.reportNotes = reportNotes;
    }

    public String getPcId() {
        return pcId;
    }

    public String getReportId() {
        return reportId;
    }

    public String getReportNotes() {
        return reportNotes;
    }
}
