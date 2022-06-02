package dao;

import dto.FieldCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

public class FieldCategoryDAO {

    private static final String GET_FIELD_CATE = "SELECT categoryFieldId FROM tblFieldCategory WHERE categoryFieldId = ?";

    public FieldCategory getFieldCategoryId(String categoryFieldId) throws SQLException {
        FieldCategory fieldCate = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_FIELD_CATE);
                ptm.setString(1, categoryFieldId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String id_of_field_cate = rs.getString("categoryFieldId");;
                    fieldCate = new FieldCategory(id_of_field_cate, "", "");
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
        return fieldCate;
    }
}
