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
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author NITRO 5
 */
public class FieldDAO {

    private static final String GET_FIELD = "SELECT fieldName FROM tblFields WHERE fieldID like ? ";
    private static final String TAKE_ALL_FIELD = "SELECT fieldId, fieldName, description, image, categoryFieldId, userId, locationId, cityId, status FROM tblFields";
    private static final String UPDATE_STATUS_FIELD = "UPDATE tblFields SET fieldName = ?, description = ?, image = ?, categoryFieldId = ?, userId = ?, locationId = ?, cityId = ?, status = ?, WHERE fieldId = ?";

    public Field getFieldByID(String fieldID) throws SQLException {
        Field field = new Field();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_FIELD);
                ptm.setString(1, fieldID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String fieldName = rs.getString("fieldName");
                    field = new Field(null, fieldName, null, null, null, null, null, null, null);
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
    
    public List<Field> getListProduct() throws SQLException {
        List<Field> listField = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(TAKE_ALL_FIELD);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String fieldId = rs.getString("fieldId");
                    String fieldName = rs.getString("fieldName");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    String id_of_field_category = rs.getString("categoryFieldId");
                    FieldCategoryDAO fieldCate = new FieldCategoryDAO();
                    FieldCategory categoryFieldID = fieldCate.getFieldCategoryId(id_of_field_category);
                    String id_of_user = rs.getString("userId");
                    UserDAO user = new UserDAO();
                    User userID = user.getUserId(id_of_user);
                    String id_of_location = rs.getString("locationId");
                    LocationDAO location = new LocationDAO();
                    Location locationID = location.getLocationId(id_of_location);
                    String id_of_city = rs.getString("cityId");
                    CityDAO city = new CityDAO();
                    City cityID = city.getCityId(id_of_city);
                    String status = rs.getString("status");
                    listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, userID, locationID, cityID, status));
                }
            }
        } catch (Exception e) {
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
        return listField;
    }
    
    public boolean updateStatusField(Field field) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null) {
                ptm = conn.prepareStatement(UPDATE_STATUS_FIELD);
                ptm.setString(1, field.getFieldName());
                ptm.setString(2, field.getDescription());
                ptm.setString(3, field.getImage());
                ptm.setString(4, field.getFieldCate().getFieldCateId());
                ptm.setString(5, field.getUser().getUserID());
                ptm.setString(6, field.getLocation().getLocationId());
                ptm.setString(7, field.getCity().getCityId());
                ptm.setString(8, field.getStatus());
                check = ptm.executeUpdate()>0?true:false;
            }
        } catch (Exception e) {
        } finally {
            if(ptm!= null) ptm.close();
            if(conn!= null) conn.close();
        }
        return check;
    }
}
