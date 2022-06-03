/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.City;
import dto.Field;
import dto.FieldCategory;
import dto.Location;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author NITRO 5
 */
public class FieldDAO {

    private static final String GET_ALL_INFO = "SELECT fieldID, fieldName, description, image, categoryFieldID, UserID, LocationID, cityID, status "
            + "FROM tblFields WHERE fieldID like ? ";

    public Field getFieldByID(String fieldID) throws SQLException {
        Field field = new Field();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_INFO);
                ptm.setString(1, fieldID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String getFieldID = rs.getString("FieldID");
                    String fieldName = rs.getString("FieldName");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    
                    String categoryFieldID = rs.getString("categoryFieldID");
                    FieldCategoryDAO fieldCategoryDAO = new FieldCategoryDAO();
                    FieldCategory fieldCategory = fieldCategoryDAO.getFieldCategoryByID(categoryFieldID);
                    
                    String UserID = rs.getString("UserID");
                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.getUserByID(UserID);
                    
                    String locationID = rs.getString("locationID");
                    LocationDAO locationDAO = new LocationDAO();
                    Location location = locationDAO.getLocationByID(locationID);
                    
                    String cityID = rs.getString("cityID");
                    CityDAO cityDAO = new CityDAO();
                    City city = cityDAO.getCityByID(cityID);
                    
                    String status = rs.getString("status");
                    
                    field = new Field(fieldID, fieldName, description, image, fieldCategory, user, location, city, status);
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
        return field;
    }
}
