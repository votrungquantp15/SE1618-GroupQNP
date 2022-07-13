/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author predator
 */
public class FoodError {
    private String foodIdError;
    private String foodNameError;
    private String foodImageError;
    private String categoryFoodIdError;
    private String status;

    public FoodError() {
    }

    public FoodError(String foodIdError, String foodNameError, String foodImageError, String categoryFoodIdError, String status) {
        this.foodIdError = foodIdError;
        this.foodNameError = foodNameError;
        this.foodImageError = foodImageError;
        this.categoryFoodIdError = categoryFoodIdError;
        this.status = status;
    }

    public String getFoodIdError() {
        return foodIdError;
    }

    public void setFoodIdError(String foodIdError) {
        this.foodIdError = foodIdError;
    }

    public String getFoodNameError() {
        return foodNameError;
    }

    public void setFoodNameError(String foodNameError) {
        this.foodNameError = foodNameError;
    }

    public String getFoodImageError() {
        return foodImageError;
    }

    public void setFoodImageError(String foodImageError) {
        this.foodImageError = foodImageError;
    }

    public String getCategoryFoodIdError() {
        return categoryFoodIdError;
    }

    public void setCategoryFoodIdError(String categoryFoodIdError) {
        this.categoryFoodIdError = categoryFoodIdError;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
