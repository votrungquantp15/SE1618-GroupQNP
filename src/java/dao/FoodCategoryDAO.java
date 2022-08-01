/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.FoodCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author NITRO 5
 */
public class FoodCategoryDAO {
    private static final String GET_ALL_INFO = "SELECT categoryFoodID, categoryFoodName, status FROM tblFoodCategory WHERE categoryFoodID like ?";
    private static final String GET_ALL_FOOD_CATEGORY = "SELECT categoryFoodId, categoryFoodName, status FROM tblFoodCategory";

    public FoodCategory getFoodCategoryByID(String categoryFoodID) throws SQLException {
        FoodCategory foodCategory = new FoodCategory();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_INFO);
                ptm.setString(1, categoryFoodID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String getCategoryFoodID = rs.getString("categoryFoodID");
                    String categoryFoodName = rs.getString("categoryFoodName");
                    String status = rs.getString("status");
                    foodCategory = new FoodCategory(getCategoryFoodID, categoryFoodName, status);
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
        return foodCategory;
    }
    
    public List<FoodCategory> getAllFoodCategory() throws SQLException {
        List<FoodCategory> listFoodCategory = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_FOOD_CATEGORY);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getCategoryFoodId = rs.getString("categoryFoodId");
                    String CategoryFoodName = rs.getString("categoryFoodName");
                    String status = rs.getString("status");
                    if (status == "1")
                        status = "active";
                    else 
                        status = "In-active";
                    listFoodCategory.add(new FoodCategory(getCategoryFoodId, CategoryFoodName, status));
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
        return listFoodCategory;
    }
    
    public List<FoodCategory> getAllFoodCategoryForList() throws SQLException {
        List<FoodCategory> listFoodCategory = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_FOOD_CATEGORY);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getCategoryFoodId = rs.getString("categoryFoodId");
                    String CategoryFoodName = rs.getString("categoryFoodName");
                    String status = rs.getString("status");
                    listFoodCategory.add(new FoodCategory(getCategoryFoodId, CategoryFoodName, status));
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
        return listFoodCategory;
    }
}
