package dto;

public class BookingHistory {
    private String bookingID;
    private String bookingDate;
    private String fieldName;
    private double price;
    private boolean status;

    public BookingHistory() {
        this.bookingID = "";
        this.bookingDate = "";
        this.fieldName = "";
        this.price = 0.0;
        this.status = false;
    }

    public BookingHistory(String bookingID, String bookingDate, String fieldName, double price, boolean status) {
        this.bookingID = bookingID;
        this.bookingDate = bookingDate;
        this.fieldName = fieldName;
        this.price = price;
        this.status = status;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
