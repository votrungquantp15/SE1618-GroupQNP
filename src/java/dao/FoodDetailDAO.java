/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Field;
import dto.Food;
import dto.FoodDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author NITRO 5
 */
public class FoodDetailDAO {
    private static final String GET_ALL_INFO = "SELECT foodDetailID, foodID, fieldID, price, quantity, status FROM tblFoodDetail WHERE foodDetailID like ?";
    
    public FoodDetail getFoodDetailByID(String foodDetailID) throws SQLException {
        FoodDetail foodDetail = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_INFO);
                ptm.setString(1, foodDetailID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String getFoodDetailID = rs.getString("foodDetailID");
                    
                    String getFoodID = rs.getString("foodID");
                    FoodDAO foodDAO = new FoodDAO();
                    Food food = foodDAO.getFoodByID(getFoodID);
                    
                    String fieldID = rs.getString("fieldID");
                    FieldDAO fieldDAO = new FieldDAO();
                    Field field = fieldDAO.getFieldByID(fieldID);
                    
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    foodDetail = new FoodDetail(getFoodDetailID, food, field, price, quantity, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return foodDetail;
    }
}
