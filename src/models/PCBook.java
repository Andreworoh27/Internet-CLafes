package models;

public class BookingPc {
    private String bookId;
    private String bookDate;
    private String pcId;

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    public String getBookDate() {
        return bookDate;
    }

    public String getBookId() {
        return bookId;
    }

    public String getPcId() {
        return pcId;
    }
}
