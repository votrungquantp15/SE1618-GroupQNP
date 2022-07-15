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
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author NITRO 5
 */
public class FoodDetailDAO {
    private static final String GET_ALL_INFO = "SELECT foodDetailID, foodID, fieldID, price, status FROM tblFoodDetail WHERE foodDetailID like ?";
    private static final String CREATE_FIELD_ID = "INSERT INTO tblFoodDetail(foodDetailId, foodId, fieldId, price, status) VALUES (?, ?, ?, ?, ?)";
    private static final String CHECK_DUPLICATE = "SELECT * FROM tblFoodDetail WHERE foodDetailId = ?";
    private static final String ACTIVE_FOOD = "UPDATE tblFoodDetail SET status = 1 WHERE foodId = ? and fieldId = ?";
    private static final String GET_FOOD_DETAIL = "SELECT foodDetailId, foodID, fieldID, price, status from tblFoodDetail where foodId = ? and fieldId = ?";
    private static final String UPDATE_FOOD = "UPDATE tblFoodDetail SET price = ? WHERE foodDetailId = ?";
    
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
                    String status = rs.getString("status");
                    foodDetail = new FoodDetail(getFoodDetailID, food, field, price, status);
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
    
    public boolean insertFieldIdOfFood(FoodDetail food) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_FIELD_ID);
                ptm.setString(1, food.getFoodDetailID());
                ptm.setString(2, food.getFood().getFoodId());
                ptm.setString(3, food.getField().getFieldId());
                ptm.setDouble(4, food.getPrice());
                ptm.setString(5, food.getStatus());
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public boolean updateFoodDetail(FoodDetail food) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_FOOD);
                ptm.setDouble(1, food.getPrice());
                ptm.setString(2, food.getFoodDetailID()); 
                
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public List<FoodDetail> getFoodDetailIdByFoodIdAndFieldId(String foodId, String fieldId) throws SQLException {
        List<FoodDetail> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null) {
                ptm = conn.prepareStatement(GET_FOOD_DETAIL);
                ptm.setString(1, foodId);
                ptm.setString(2, fieldId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String getFoodDetailId = rs.getString("foodDetailID");
                    String getFoodID = rs.getString("foodID");
                    FoodDAO foodDAO = new FoodDAO();
                    Food food = foodDAO.getFoodByID(getFoodID);
                    
                    String fieldID = rs.getString("fieldID");
                    FieldDAO fieldDAO = new FieldDAO();
                    Field field = fieldDAO.getFieldByID(fieldID);
                    
                    double price = rs.getDouble("price");
                    String status = rs.getString("status");
                    list.add(new FoodDetail(getFoodDetailId, food, field, price, status));
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
        return list;
    }
    
    public boolean activeFood(String foodId, String fieldId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ACTIVE_FOOD);
                ptm.setString(1, foodId);
                ptm.setString(2, fieldId);
                rs = ptm.executeQuery();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public boolean checkDuplicate(String FoodDetailID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, FoodDetailID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }
    
    public String handleFoodDetailID() {
        int max = 999999;
        int min = 1;
        int random_double = (int) (Math.random() * (max - min + 1) + min);
        String s = String.valueOf(random_double);
        return "FD" + s;
    }
    
    public String foodDetailIDForManager() throws SQLException {
        String foodDetailID = handleFoodDetailID();//ramdom food Id
        boolean check = false;
        do {
            check = checkDuplicate(foodDetailID);//check trùng ID
            if (check == false) {
                foodDetailID = handleFoodDetailID();
            }
        } while (check);
        return foodDetailID;
    }
}
