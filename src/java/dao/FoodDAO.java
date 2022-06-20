package dao;

import dto.City;
import dto.Food;
import dto.FoodCategory;
import dto.Role;
import dto.User;
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
public class FoodDAO {

    private static final String GET_ALL_INFO = "SELECT foodID, foodName, image, categoryFoodID, status "
            + "FROM tblFoods WHERE foodID like ?";
    private static final String SEARCH_FOOD_BY_NAME_FOR_MANAGER = "SELECT foodId, foodName, image, foodCate, status FROM tblFoods WHERE foodName LIKE ? ";
    private static final String DELETE_FOOD = "UPDATE tblUsers SET status = 0 WHERE userID = ?";
    private static final String VIEW_FOOD_LIST = "SELECT foodId, foodName, image, categoryFoodId, status FROM tblFoods";
    private static final String UPDATE_FOOD = "UPDATE tblFoods SET foodName = ?, image = ?, categoryFoodName = ?, status = ? WHERE foodId = ?";

    public Food getFoodByID(String foodID) throws SQLException {
        Food food = new Food();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_INFO);
                ptm.setString(1, foodID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String getFoodID = rs.getString("foodID");
                    String foodName = rs.getString("foodName");
                    String image = rs.getString("image");

                    String categoryFoodID = rs.getString("categoryFoodID");
                    FoodCategoryDAO foodCategoryDAO = new FoodCategoryDAO();
                    FoodCategory foodCategory = foodCategoryDAO.getFoodCategoryByID(categoryFoodID);

                    String status = rs.getString("status");
                    food = new Food(getFoodID, foodName, image, foodCategory, status);
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
        return food;
    }

    public List<Food> searchFoodByNameForManager(String search) throws SQLException {
        List<Food> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            try {
                if (conn != null) {
                    ptm = conn.prepareStatement(SEARCH_FOOD_BY_NAME_FOR_MANAGER);
                    ptm.setString(1, "%" + search + "%");
                    rs = ptm.executeQuery();

                    while (rs.next()) {
                        String foodId = rs.getString("foodId");
                        String foodName = rs.getString("foodName");
                        String image = rs.getString("image");
                        String foodCate = rs.getString("categoryFoodId");
                        FoodCategoryDAO fCate = new FoodCategoryDAO();
                        FoodCategory fCateId = fCate.getFoodCategoryByID(foodCate);
                        String status = rs.getString("status");
                        if (status.equals("1")) {
                            status = "Active";
                        } else {
                            status = "In-active";
                        }
                        list.add(new Food(foodId, foodName, image, fCateId, status));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
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

    public List<Food> viewFoodList() throws SQLException {
        List<Food> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(VIEW_FOOD_LIST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String foodId = rs.getString("foodId");
                    String foodName = rs.getString("foodName");
                    String image = rs.getString("image");
                    String foodCate = rs.getString("categoryFoodId");
                    FoodCategoryDAO fCate = new FoodCategoryDAO();
                    FoodCategory fCateId = fCate.getFoodCategoryByID(foodCate);
                    String status = rs.getString("status");
                    if (status.equals("1")) {
                        status = "Active";
                    } else {
                        status = "In-active";
                    }

                    list.add(new Food(foodId, foodName, image, fCateId, status));
                }
            }
            ///aa

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
    
    public boolean updateFood(Food food) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_FOOD);
                ptm.setString(1, food.getFoodName());
                ptm.setString(2, food.getImage());
                ptm.setString(3, food.getFoodCate().getFoodCateName());
                ptm.setString(4, food.getStatus());
                ptm.setString(5, food.getFoodId());
                
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
}
