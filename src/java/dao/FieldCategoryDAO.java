package dao;

import dto.FieldCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class FieldCategoryDAO {

    private static final String GET_ALL_INFO = "SELECT categoryFieldID, categoryFieldName, status "
            + "FROM tblFieldCategory WHERE categoryFieldId like ?";
    private static final String GET_ALL_CATEGORY = "SELECT  categoryFieldID, categoryFieldName, status FROM tblFieldCategory";
    private static final String CHECK_FIELD_CATE_ID = "SELECT categoryFieldID FROM tblFieldCategory WHERE categoryFieldID = ?";
    private static final String SEARCH_FIELD_CATE_BY_ADMIN = "SELECT categoryFieldID, categoryFieldName, status FROM tblFieldCategory WHERE categoryFieldName like ? AND status like ?";
    private static final String DELETE_FIELD_CATE_BY_ADMIN = "UPDATE tblFieldCategory SET [status] = 'false' WHERE categoryFieldID = ?";
    private static final String CHECK_EXIST_FIELD_CATE = "SELECT categoryFieldID FROM tblFields WHERE categoryFieldID = ?";
    
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
                        String statusAfter = changeNumberStatus(status);
                        fieldCategory = new FieldCategory(getCategoryFieldID, categoryFieldName, statusAfter);
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
                    String statusAfter = changeNumberStatus(status);
                    fieldCategory = new FieldCategory(getCategoryFieldID, categoryFieldName, statusAfter);
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
    
    public List<FieldCategory> searchFieldCateByAdmin(String search, String status) throws SQLException {
        List<FieldCategory> listFieldCate = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_FIELD_CATE_BY_ADMIN);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + status + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String fieldCateID = rs.getString("categoryFieldID");
                    String fieldCateName = rs.getString("categoryFieldName");
                    String statusOfFieldCate = rs.getString("status");
                    String statusAfter = changeNumberStatus(statusOfFieldCate);
                    listFieldCate.add(new FieldCategory(fieldCateID, fieldCateName, statusAfter));
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
        return listFieldCate;
    }
    
    public boolean deleteFieldCate(String fieldCateId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_FIELD_CATE_BY_ADMIN);
                ptm.setString(1, fieldCateId);
                check = ptm.executeUpdate() > 0 ? true : false;
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

    public boolean checkExistFieldCate(String fieldCateId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EXIST_FIELD_CATE);
                ptm.setString(1, fieldCateId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
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
    
    public String changeNumberStatus(String status) {
        if (status.equals("1")) {
            status = "Active";
        } else {
            status = "In-active";
        }
        return status;
    }
    
    public String changeStringStatus(String status) {
        if (status.equals("Active")) {
            status = "1";
        } else {
            status = "0";
        }
        return status;
    }
}
