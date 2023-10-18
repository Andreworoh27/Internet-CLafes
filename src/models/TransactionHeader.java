package models;

public class TransactionHeader {
    private String transactionID;
    private String customerId;
    private String customerName;
    private String bookingId;

    public TransactionHeader() {
        super();
    }

    public TransactionHeader(String transactionID, String customerId, String customerName, String bookingId) {
        this.transactionID = transactionID;
        this.customerId = customerId;
        this.customerName = customerName;
        this.bookingId = bookingId;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

}
