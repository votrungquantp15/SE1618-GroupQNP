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
    private static final String GET_ALL_FIELD_CATEGORY = "SELECT  categoryFieldID, categoryFieldName, status FROM tblFieldCategory";
    private static final String CHECK_FIELD_CATE_ID = "SELECT categoryFieldID FROM tblFieldCategory WHERE categoryFieldID = ?";
    private static final String CHECK_FIELD_CATE_NAME = "SELECT categoryFieldName FROM tblFieldCategory WHERE categoryFieldName = ?";
    private static final String SEARCH_FIELD_CATE_BY_ADMIN = "SELECT categoryFieldID, categoryFieldName, status FROM tblFieldCategory WHERE categoryFieldName like ? AND status like ?";
    private static final String DELETE_FIELD_CATE_BY_ADMIN = "UPDATE tblFieldCategory SET [status] = 'In-Active' WHERE categoryFieldID = ?";
    private static final String CHECK_EXIST_FIELD_CATE = "SELECT categoryFieldID FROM tblFields WHERE categoryFieldID = ?";
    private static final String UPDATE_STATUS_FIELD_CATE_BY_ADMIN = "UPDATE tblFieldCategory SET [status] = ? WHERE categoryFieldID = ?";
    private static final String UPDATE_FIELD_CATE_BY_OWNER = "UPDATE tblFieldCategory SET categoryFieldName = ? WHERE categoryFieldID = ?";
    private static final String CREATE_FIELD_CATE = "INSERT INTO tblFieldCategory(categoryFieldID, categoryFieldName) VALUES(?,?)";
    
    private static final String COUNT_ALL_FIELD_CATE = "SELECT COUNT(*) as totalFieldCate FROM tblFieldCategory";
    private static final String GET_ALL_FIELD_CATE_PAGING = "SELECT categoryFieldID, categoryFieldName, status FROM tblFieldCategory ORDER BY categoryFieldID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
    
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
                ptm = conn.prepareStatement(GET_ALL_FIELD_CATEGORY);
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
    
    public List<FieldCategory> getAllFieldCategoryPaging(int index) throws SQLException {
        List<FieldCategory> listFieldCategorys = new ArrayList<>();
        FieldCategory fieldCategory = null;

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_FIELD_CATE_PAGING);
                ptm.setInt(1, (index - 1) * 5);
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
    
    public boolean checkFieldCateName(String categoryFieldName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_FIELD_CATE_NAME);
                ptm.setString(1, categoryFieldName);
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
                    listFieldCate.add(new FieldCategory(fieldCateID, fieldCateName, statusOfFieldCate));
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
    
    public boolean updateStatusFieldCate(String fieldCateId, String status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STATUS_FIELD_CATE_BY_ADMIN);
                ptm.setString(1, status);
                ptm.setString(2, fieldCateId);
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
    
    public boolean updateFieldCateByOwner(FieldCategory fieldCate) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_FIELD_CATE_BY_OWNER);
                ptm.setString(1, fieldCate.getFieldCateName());
                ptm.setString(2, fieldCate.getFieldCateId());
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
    
    public String handleFieldCateId() {
        int max = 999999;
        int min = 1;
        int random_double = (int) (Math.random() * (max - min + 1) + min);
        String s = String.valueOf(random_double);
        return "FC" + s;
    }

    public String createFieldCateId() throws SQLException {
        String fieldCateId = handleFieldCateId();
        boolean check = false;
        do {
            check = checkFieldCateId(fieldCateId);
            if (check == false) {
                fieldCateId = handleFieldCateId();
            }
        } while (check);
        return fieldCateId;
    }

    public boolean createFieldCate(FieldCategory fieldCate) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_FIELD_CATE);
                ptm.setString(1, fieldCate.getFieldCateId());
                ptm.setString(2, fieldCate.getFieldCateName());
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
    
    public int countTotalFieldCate() throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_FIELD_CATE);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    index = rs.getInt("totalFieldCate");
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
        return index;
    }
}
