package dto;

public class BookingDetail {
    private String bookingDetailID;
    private Booking booking;
    private Field field;
    private SlotDetail slotDetail;
    private double fieldPrice;
    private FoodDetail foodDetail;
    private double foodTotalPrice;
    private int foodTotalQuantity;
    private String playDate;
    private boolean status;

    public BookingDetail() {
        this.bookingDetailID = "";
        this.booking = null;
        this.field = null;
        this.slotDetail = null;
        this.fieldPrice = 0.0;
        this.foodDetail = null;
        this.foodTotalPrice = 0.0;
        this.foodTotalQuantity = 0;
        this.playDate = "";
        this.status = false;
    }

    public BookingDetail(String bookingDetailID, Booking booking, Field field, SlotDetail slotDetail, double fieldPrice, FoodDetail foodDetail, double foodTotalPrice, int foodTotalQuantity, String playDate, boolean status) {
        this.bookingDetailID = bookingDetailID;
        this.booking = booking;
        this.field = field;
        this.slotDetail = slotDetail;
        this.fieldPrice = fieldPrice;
        this.foodDetail = foodDetail;
        this.foodTotalPrice = foodTotalPrice;
        this.foodTotalQuantity = foodTotalQuantity;
        this.playDate = playDate;
        this.status = status;
    }

    public String getBookingDetailID() {
        return bookingDetailID;
    }

    public void setBookingDetailID(String bookingDetailID) {
        this.bookingDetailID = bookingDetailID;
    }

    public double getFieldPrice() {
        return fieldPrice;
    }

    public void setFieldPrice(double fieldPrice) {
        this.fieldPrice = fieldPrice;
    }

    public double getFoodTotalPrice() {
        return foodTotalPrice;
    }

    public void setFoodTotalPrice(double foodTotalPrice) {
        this.foodTotalPrice = foodTotalPrice;
    }

    public int getFoodTotalQuantity() {
        return foodTotalQuantity;
    }

    public void setFoodTotalQuantity(int foodTotalQuantity) {
        this.foodTotalQuantity = foodTotalQuantity;
    }

    public FoodDetail getFoodDetail() {
        return foodDetail;
    }

    public void setFoodDetail(FoodDetail foodDetail) {
        this.foodDetail = foodDetail;
    }

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public SlotDetail getSlotDetail() {
        return slotDetail;
    }

    public void setSlotDetail(SlotDetail slotDetail) {
        this.slotDetail = slotDetail;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
