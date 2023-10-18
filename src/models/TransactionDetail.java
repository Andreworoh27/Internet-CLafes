package models;

public class TransactionDetail {
    private String transactionID;
    private String pcId;
    private String bookingDate;

    public TransactionDetail() {
    }

    public TransactionDetail(String transactionID, String pcId, String bookingDate) {
        this.transactionID = transactionID;
        this.bookingDate = bookingDate;
        this.pcId = pcId;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getPcId() {
        return pcId;
    }

    public String getBookingDate() {
        return bookingDate;
    }
}
