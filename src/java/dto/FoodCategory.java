package dto;

public class FoodCategory {
    private String foodCateId;
    private String foodCateName;
    private String status;

    public FoodCategory() {
        this.foodCateId = "";
        this.foodCateName = "";
        this.status = "";
    }

    public FoodCategory(String foodCateId, String foodCateName, String status) {
        this.foodCateId = foodCateId;
        this.foodCateName = foodCateName;
        this.status = status;
    }

    public String getFoodCateId() {
        return foodCateId;
    }

    public void setFoodCateId(String foodCateId) {
        this.foodCateId = foodCateId;
    }

    public String getFoodCateName() {
        return foodCateName;
    }

    public void setFoodCateName(String foodCateName) {
        this.foodCateName = foodCateName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
