package dto;

public class FoodDetail {
    private String foodDetailID;
    private Food food;
    private Field field;
    private double price;
    private String status;

    public FoodDetail() {
        this.foodDetailID = "";
        this.food = null;
        this.field = null;
        this.price = 0.0;
        this.status = null;
    }

    public FoodDetail(String foodDetailID, Food food, Field field, double price, String status) {
        this.foodDetailID = foodDetailID;
        this.food = food;
        this.field = field;
        this.price = price;
        this.status = status;
    }

    public String getFoodDetailID() {
        return foodDetailID;
    }

    public void setFoodDetailID(String foodDetailID) {
        this.foodDetailID = foodDetailID;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
