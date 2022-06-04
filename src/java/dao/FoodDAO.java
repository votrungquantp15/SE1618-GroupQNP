
package dao;

import dto.Food;
import dto.FoodCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author NITRO 5
 */
public class FoodDAO {
    private static final String GET_ALL_INFO = "SELECT foodID, foodName, image, categoryFoodID, status "
            + "FROM tblFoods WHERE foodID like ?";
      
    public Food getFoodByID(String foodID) throws SQLException{
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
}
