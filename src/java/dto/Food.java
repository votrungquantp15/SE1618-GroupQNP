package dto;

public class Food {
    private String foodId;
    private String foodName;
    private String image;
    private FoodCategory foodCate;
    private String status;

    public Food() {
        this.foodId = "";
        this.foodName = "";
        this.image = "";
        this.foodCate = null;
        this.status = "";
    }

    public Food(String foodId, String foodName, String image, FoodCategory foodCate, String status) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.image = image;
        this.foodCate = foodCate;
        this.status = status;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public FoodCategory getFoodCate() {
        return foodCate;
    }

    public void setFoodCate(FoodCategory foodCate) {
        this.foodCate = foodCate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
