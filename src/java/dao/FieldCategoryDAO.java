/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.FieldCategory;
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
public class FieldCategoryDAO {

    private static final String GET_ALL_INFO = "SELECT categoryFieldID, categoryFieldName, status "
            + "FROM tblFieldCategory WHERE categoryFieldId like ?";
    private static final String GET_ALL_CATEGORY = "SELECT  categoryFieldID, categoryFieldName, status FROM tblFieldCategory";
    private static final String CHECK_FIELD_CATE_ID = "SELECT categoryFieldID FROM tblFieldCategory WHERE categoryFieldID = ?";

    public FieldCategory getFieldCategoryByID(String categoryFieldID) throws SQLException {
        FieldCategory fieldCategory = null;
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                check = checkFieldCateId(categoryFieldID);
                if (check) {
                    ptm = conn.prepareStatement(GET_ALL_INFO);
                    ptm.setString(1, categoryFieldID);
                    rs = ptm.executeQuery();
                    if (rs.next()) {
                        String getCategoryFieldID = rs.getString("categoryFieldID");
                        String categoryFieldName = rs.getString("categoryFieldName");
                        String status = rs.getString("status");
                        fieldCategory = new FieldCategory(getCategoryFieldID, categoryFieldName, status);
                    }
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
        return fieldCategory;
    }

    public List<FieldCategory> getAllFieldCategory() throws SQLException {
        List<FieldCategory> listFieldCategorys = new ArrayList<>();
        FieldCategory fieldCategory = null;

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_CATEGORY);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getCategoryFieldID = rs.getString("categoryFieldID");
                    String categoryFieldName = rs.getString("categoryFieldName");
                    String status = rs.getString("status");
                    fieldCategory = new FieldCategory(getCategoryFieldID, categoryFieldName, status);
                    listFieldCategorys.add(fieldCategory);
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
        return listFieldCategorys;
    }

    public boolean checkFieldCateId(String categoryFieldID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_FIELD_CATE_ID);
                ptm.setString(1, categoryFieldID);
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
}
