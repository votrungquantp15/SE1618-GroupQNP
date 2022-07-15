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
public class FoodDetailError {
    private String foodDetailError;
    private String foodIdError;
    private String fieldIdError;
    private String priceError;
    private String statusError;

    public FoodDetailError() {
    }

    public FoodDetailError(String foodDetailError, String foodIdError, String fieldIdError, String priceError, String statusError) {
        this.foodDetailError = foodDetailError;
        this.foodIdError = foodIdError;
        this.fieldIdError = fieldIdError;
        this.priceError = priceError;
        this.statusError = statusError;
    }

    public String getFoodDetailError() {
        return foodDetailError;
    }

    public void setFoodDetailError(String foodDetailError) {
        this.foodDetailError = foodDetailError;
    }

    public String getFoodIdError() {
        return foodIdError;
    }

    public void setFoodIdError(String foodIdError) {
        this.foodIdError = foodIdError;
    }

    public String getFieldIdError() {
        return fieldIdError;
    }

    public void setFieldIdError(String fieldIdError) {
        this.fieldIdError = fieldIdError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }
    
}
