package dao;

import dto.District;
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

    private static final String GET_ALL_INFO = "SELECT foodID, foodName, image, categoryFoodId, status "
            + "FROM tblFoods WHERE foodID like ?";
    private static final String SEARCH_FOOD_BY_NAME_FOR_MANAGER = "SELECT foodId, foodName, image, categoryFoodId, status FROM tblFoods WHERE foodName LIKE ? ";
    private static final String SEARCH_FOOD_BY_ID_FOR_MANAGER = "SELECT foodId, foodName, image, categoryFoodId, status FROM tblFoods WHERE foodId LIKE ? ";
    private static final String DELETE_FOOD = "UPDATE tblFoods SET status = 0 WHERE foodId = ?";
    private static final String VIEW_FOOD_LIST = "SELECT foodId, foodName, image, categoryFoodId, status FROM tblFoods";
    private static final String UPDATE_FOOD = "UPDATE tblFoods SET foodName = ?, image = ?, categoryFoodId = ?, status = ? WHERE foodId = ?";
    private static final String CREATE = "INSERT INTO tblFoods(foodId, foodName, image, categoryFoodId, status) VALUES (? ,?, ?, ?, ?)";
    private static final String CHECK_DUPLICATE = "SELECT foodName FROM tblFoods WHERE foodId = ?";

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

                    String categoryFoodID = rs.getString("categoryFoodId");
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
    public List<Food> searchFoodByIdForManager(String search) throws SQLException {
        List<Food> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            try {
                if (conn != null) {
                    ptm = conn.prepareStatement(SEARCH_FOOD_BY_ID_FOR_MANAGER);
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
                ptm.setString(3, food.getFoodCate().getFoodCateId());
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
    
    public boolean deleteFood(String foodId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_FOOD);
                ptm.setString(1, foodId);
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
    
    public List<Food> pagingFood(int index) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<Food> list = new ArrayList<>();
        String query = "SELECT * FROM tblFoods\n"
                + "ORDER BY foodId\n"
                + "OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(query);
            ptm.setInt(1, (index - 1) * 5);
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
        } catch (Exception e) {
        }
        return list;
    }
    
    public int getTotalFood() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String query = "select count(*) from tblFoods";
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(query);
            rs = ptm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;

    }
    
    public boolean insertFood(Food food) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, food.getFoodId());
                ptm.setString(2, food.getFoodName());
                ptm.setString(3, food.getImage());
                ptm.setString(4, food.getFoodCate().getFoodCateId());
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
    
    public boolean checkDuplicate(String FoodID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, FoodID);
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
    
    public String handleFoodID() {
        int max = 999999;
        int min = 1;
        int random_double = (int) (Math.random() * (max - min + 1) + min);
        String s = String.valueOf(random_double);
        return "F" + s;
    }
    
    public String foodIDForManager() throws SQLException {
        String foodID = handleFoodID();//ramdom food Id
        boolean check = false;
        do {
            check = checkDuplicate(foodID);//check trùng ID
            if (check == false) {
                foodID = handleFoodID();
            }
        } while (check);
        return foodID;
    }

}
