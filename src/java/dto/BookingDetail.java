package dto;

public class BookingDetail {

    private Booking booking;
    private Field field;
    private Slot slot;
    private double fieldPrice;
    private Food food;
    private FoodDetail foodDetail;
    private double foodTotalPrice;
    private int foodTotalQuantity;
    private String playDate;
    private boolean status;

    public BookingDetail() {
        this.booking = null;
        this.field = null;
        this.slot = null;
        this.fieldPrice = 0.0;
        this.food = null;
        this.foodDetail = null;
        this.foodTotalPrice = 0.0;
        this.foodTotalQuantity = 0;
        this.playDate = "";
        this.status = false;
    }

    public BookingDetail(Booking booking, Field field, Slot slot, double fieldPrice, Food food, FoodDetail foodDetail, double foodTotalPrice, int foodTotalQuantity, String playDate, boolean status) {
        this.booking = booking;
        this.field = field;
        this.slot = slot;
        this.fieldPrice = fieldPrice;
        this.food = food;
        this.foodDetail = foodDetail;
        this.foodTotalPrice = foodTotalPrice;
        this.foodTotalQuantity = foodTotalQuantity;
        this.playDate = playDate;
        this.status = status;
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

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
