package dto;

public class Booking {
    private String bookingId;
    private String bookingDate;
    private User user;
    private double totalPrice;
    private String status;

    public Booking() {
        this.bookingId = "";
        this.bookingDate = "";
        this.user = null;
        this.totalPrice = 0;
        this.status = "";
    }

    public Booking(String bookingId, String bookingDate, User user, double totalPrice, String status) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.user = user;
        this.totalPrice = totalPrice;
        this.status = status;
    }


    public String getBookingId() {
        return bookingId;
    }
    

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
