/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Field;
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

    private static final String GET_FIELD = "SELECT fieldName FROM tblFields WHERE fieldID like ? ";

    public Field getField(String fieldID) throws SQLException {
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
}
