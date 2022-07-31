package dao;

import dto.District;
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

public class FieldDAO {
    
    private static final String INSERT_BOOKING = "INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES (?, ?, ?, ?, 'Pending')";

    private static final String GET_ALL_INFO_BY_ID = "SELECT fieldID, fieldName, description, image, categoryFieldID, price, UserID, LocationID, districtID, status "
            + "FROM tblFields WHERE fieldID like ? ";
    private static final String PRINT_FIELD_DETAIL_BY_ID = "SELECT fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, districtId, status FROM tblFields WHERE fieldId = ?";
    private static final String COUNT_SEARCH_FIELD_BY_USER = "SELECT COUNT(*) as totalField FROM tblFields WHERE fieldName like ? AND districtId like ? AND status <> 'In-Active' AND status <> 'Request'";
    private static final String SEARCH_FIELD_BY_USER = "WITH x AS (SELECT ROW_NUMBER() over (order by fieldId ASC) as r, fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, districtId, status FROM tblFields WHERE fieldName like ? AND districtId like ? AND status <> 'In-Active' AND status <> 'Request') SELECT * FROM x WHERE r BETWEEN ? * 9 - 8 AND ? * 9";

    private static final String COUNT_ALL_FIELD_BY_ADMIN = "SELECT COUNT(*) as totalField FROM tblFields";
    private static final String COUNT_ALL_FIELD_BY_OWNER = "SELECT COUNT(*) as totalField FROM tblFields WHERE userId = ?";
    private static final String COUNT_ALL_FIELD_BY_USER = "SELECT COUNT(*) as totalField FROM tblFields WHERE status <> 'In-Active' AND status <> 'Request'";

    private static final String PRINT_ALL_FIELD_BY_ADMIN = "SELECT fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, districtId, status FROM tblFields ORDER BY fieldId OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
    private static final String PRINT_ALL_FIELD_BY_OWNER_PAGING = "SELECT fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, districtId, status FROM tblFields WHERE userId = ? ORDER BY fieldId OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
    private static final String PRINT_ALL_FIELD_BY_OWNER = "SELECT fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, districtId, status FROM tblFields WHERE userId = ?";
    private static final String PRINT_ALL_FIELD_BY_USER = "SELECT fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, districtId, status FROM tblFields WHERE status <> 'In-Active' AND status <> 'Request' ORDER BY fieldId OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY";
    private static final String PRINT_FIELD_DETAIL_BY_ADMIN = "SELECT fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, districtId, status FROM tblFields WHERE fieldId like ?";
    private static final String UPDATE_STATUS_FIELD_BY_ADMIN = "UPDATE tblFields SET [status] = ? WHERE fieldId = ?";
    private static final String UPDATE_FIELD_BY_OWNER = "UPDATE tblFields SET fieldName = ?, [description] = ?, [image] = ?, categoryFieldId = ?, price = ?, userId = ?, locationId = ?, districtId = ? WHERE fieldId = ?";
    private static final String DELETE_FIELD_BY_ADMIN = "UPDATE tblFields SET [status] = 'In-Active' WHERE fieldId = ?";
    private static final String CHECK_EXIST_FIELD = "SELECT fieldId FROM tblBookingDetail WHERE fieldId = ?";

    private static final String COUNT_SEARCH_FIELD_BY_NAME = "SELECT COUNT(*) as totalField FROM tblFields WHERE fieldName like ? AND status like ?";
    private static final String COUNT_SEARCH_FIELD_BY_CATE = "SELECT COUNT(*) as totalField FROM tblFields f LEFT JOIN tblFieldCategory fc ON f.categoryFieldId = fc.categoryFieldId WHERE fc.categoryFieldName like ? AND f.status like ?";
    private static final String COUNT_SEARCH_FIELD_BY_FIELD_OWNER = "SELECT COUNT(*) as totalField FROM tblFields f LEFT JOIN tblUsers us ON f.userId = us.userId WHERE us.fullName like ? AND f.status like ?";
    private static final String COUNT_SEARCH_FIELD_BY_DISTRICT = "SELECT COUNT(*) as totalField FROM tblFields f LEFT JOIN tblDistrict ci ON f.districtId = ci.districtId WHERE ci.districtName like ? AND f.status like ?";

    private static final String SEARCH_FIELD_BY_NAME = "WITH x AS (SELECT ROW_NUMBER() over (order by fieldId ASC) as r, fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, districtId, status FROM tblFields WHERE fieldName like ? AND status like ?) SELECT * FROM x WHERE r BETWEEN ? * 5 - 4 AND ? * 5";
    private static final String SEARCH_FIELD_BY_FIELD_CATE = "WITH x AS (SELECT ROW_NUMBER() over (order by fieldId ASC) as r, fieldId, fieldName, description, image, f.categoryFieldId, price, userId, locationId, districtId, f.status, fc.categoryFieldName FROM tblFields f LEFT JOIN tblFieldCategory fc ON f.categoryFieldId = fc.categoryFieldId WHERE fc.categoryFieldName like ? AND f.status like ?) SELECT * FROM x WHERE r BETWEEN ? * 5 - 4 AND ? * 5";
    private static final String SEARCH_FIELD_BY_FIELD_OWNER = "WITH x AS (SELECT ROW_NUMBER() over (order by fieldId ASC) as r, fieldId, fieldName, description, image, categoryFieldId, price, f.userId, locationId, f.districtId, f.status FROM tblFields f LEFT JOIN tblUsers us ON f.userId = us.userId WHERE us.fullName like ? AND f.status like ?) SELECT * FROM x WHERE r BETWEEN ? * 5 - 4 AND ? * 5";
    private static final String SEARCH_FIELD_BY_CITY = "WITH x AS (SELECT ROW_NUMBER() over (order by fieldId ASC) as r, fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, f.districtId, f.status FROM tblFields f LEFT JOIN tblDistrict ci ON f.districtId = ci.districtId WHERE ci.districtName like ? AND f.status like ?) SELECT * FROM x WHERE r BETWEEN ? * 5 - 4 AND ? * 5";

    private static final String COUNT_SEARCH_FIELD_OWNER_BY_NAME = "SELECT COUNT(*) as totalField FROM tblFields WHERE fieldName like ? AND status like ? AND userId = ?";
    private static final String COUNT_SEARCH_FIELD_OWNER_BY_CATE = "SELECT COUNT(*) as totalField FROM tblFields f LEFT JOIN tblFieldCategory fc ON f.categoryFieldId = fc.categoryFieldId WHERE fc.categoryFieldName like ? AND f.status like ? AND userId = ?";
    private static final String COUNT_SEARCH_FIELD_OWNER_BY_DISTRICT = "SELECT COUNT(*) as totalField FROM tblFields f LEFT JOIN tblDistrict ci ON f.districtId = ci.districtId WHERE ci.districtName like ? AND f.status like ? AND userId = ?";

    private static final String SEARCH_FIELD_OWNER_BY_NAME = "WITH x AS (SELECT ROW_NUMBER() over (order by fieldId ASC) as r, fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, districtId, status FROM tblFields WHERE fieldName like ? AND status like ? AND userId = ?) SELECT * FROM x WHERE r BETWEEN ? * 5 - 4 AND ? * 5";
    private static final String SEARCH_FIELD_OWNER_BY_FIELD_CATE = "WITH x AS (SELECT ROW_NUMBER() over (order by fieldId ASC) as r, fieldId, fieldName, description, image, f.categoryFieldId, price, userId, locationId, districtId, f.status, fc.categoryFieldName FROM tblFields f LEFT JOIN tblFieldCategory fc ON f.categoryFieldId = fc.categoryFieldId WHERE fc.categoryFieldName like ? AND f.status like ? AND f.userId = ?) SELECT * FROM x WHERE r BETWEEN ? * 5 - 4 AND ? * 5";
    private static final String SEARCH_FIELD_OWNER_BY_CITY = "WITH x AS (SELECT ROW_NUMBER() over (order by fieldId ASC) as r, fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, f.districtId, f.status FROM tblFields f LEFT JOIN tblDistrict ci ON f.districtId = ci.districtId WHERE ci.districtName like ? AND f.status like ? AND f.userId = ?) SELECT * FROM x WHERE r BETWEEN ? * 5 - 4 AND ? * 5";

    private static final String CHECK_FIELD_ID = "SELECT fieldId FROM tblFields WHERE fieldId = ?";
    private static final String CREATE_FIELD = "INSERT INTO tblFields(fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, districtId) VALUES(?, ?, ?,?, ?, ?, ?, ?, ?)";

    public Field getFieldByID(String fieldID) throws SQLException {
        Field field = new Field();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_INFO_BY_ID);
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

                    double price = rs.getDouble("price");

                    String UserID = rs.getString("UserID");
                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.getUserByID(UserID);

                    String locationID = rs.getString("locationID");
                    LocationDAO locationDAO = new LocationDAO();
                    Location location = locationDAO.getLocationByID(locationID);

                    String districtID = rs.getString("districtID");
                    DistrictDAO districtDAO = new DistrictDAO();
                    District district = districtDAO.getDistrictByID(districtID);

                    String status = rs.getString("status");
                    field = new Field(getFieldID, fieldName, description, image, fieldCategory, price, user, location, district, status);
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

    public List<Field> getListField(int index) throws SQLException {
        List<Field> listField = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRINT_ALL_FIELD_BY_ADMIN);
                ptm.setInt(1, (index - 1) * 5);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String fieldId = rs.getString("fieldId");
                    String fieldName = rs.getString("fieldName");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    String id_of_field_category = rs.getString("categoryFieldId");
                    FieldCategoryDAO fieldCate = new FieldCategoryDAO();
                    FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
                    double price = rs.getDouble("price");
                    String id_of_user = rs.getString("userId");
                    UserDAO user = new UserDAO();
                    User userID = user.getUserByID(id_of_user);
                    String id_of_location = rs.getString("locationId");
                    LocationDAO location = new LocationDAO();
                    Location locationID = location.getLocationByID(id_of_location);
                    String id_of_district = rs.getString("districtId");
                    DistrictDAO district = new DistrictDAO();
                    District districtID = district.getDistrictByID(id_of_district);
                    String status = rs.getString("status");
                    listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, districtID, status));
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

    public List<Field> getListOwnerFieldPaging(int index, String userId) throws SQLException {
        List<Field> listField = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRINT_ALL_FIELD_BY_OWNER_PAGING);
                ptm.setString(1, userId);
                ptm.setInt(2, (index - 1) * 5);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String fieldId = rs.getString("fieldId");
                    String fieldName = rs.getString("fieldName");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    String id_of_field_category = rs.getString("categoryFieldId");
                    FieldCategoryDAO fieldCate = new FieldCategoryDAO();
                    FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
                    double price = rs.getDouble("price");
                    String id_of_user = rs.getString("userId");
                    UserDAO user = new UserDAO();
                    User userID = user.getUserByID(id_of_user);
                    String id_of_location = rs.getString("locationId");
                    LocationDAO location = new LocationDAO();
                    Location locationID = location.getLocationByID(id_of_location);
                    String id_of_district = rs.getString("districtId");
                    DistrictDAO district = new DistrictDAO();
                    District districtID = district.getDistrictByID(id_of_district);
                    String status = rs.getString("status");
                    listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, districtID, status));
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
    
    public List<Field> getListOwnerField(String userId) throws SQLException {
        List<Field> listField = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRINT_ALL_FIELD_BY_OWNER);
                ptm.setString(1, userId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String fieldId = rs.getString("fieldId");
                    String fieldName = rs.getString("fieldName");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    String id_of_field_category = rs.getString("categoryFieldId");
                    FieldCategoryDAO fieldCate = new FieldCategoryDAO();
                    FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
                    double price = rs.getDouble("price");
                    String id_of_user = rs.getString("userId");
                    UserDAO user = new UserDAO();
                    User userID = user.getUserByID(id_of_user);
                    String id_of_location = rs.getString("locationId");
                    LocationDAO location = new LocationDAO();
                    Location locationID = location.getLocationByID(id_of_location);
                    String id_of_district = rs.getString("districtId");
                    DistrictDAO district = new DistrictDAO();
                    District districtID = district.getDistrictByID(id_of_district);
                    String status = rs.getString("status");
                    listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, districtID, status));
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

    public List<Field> getListFieldByUser(int index) throws SQLException {
        List<Field> listField = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRINT_ALL_FIELD_BY_USER);
                ptm.setInt(1, (index - 1) * 9);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String fieldId = rs.getString("fieldId");
                    String fieldName = rs.getString("fieldName");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    String id_of_field_category = rs.getString("categoryFieldId");
                    FieldCategoryDAO fieldCate = new FieldCategoryDAO();
                    FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
                    double price = rs.getDouble("price");
                    String id_of_user = rs.getString("userId");
                    UserDAO user = new UserDAO();
                    User userID = user.getUserByID(id_of_user);
                    String id_of_location = rs.getString("locationId");
                    LocationDAO location = new LocationDAO();
                    Location locationID = location.getLocationByID(id_of_location);
                    String id_of_district = rs.getString("districtId");
                    DistrictDAO district = new DistrictDAO();
                    District districtID = district.getDistrictByID(id_of_district);
                    listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, districtID, ""));
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

    public List<Field> getFieldDetailById(String idField) throws SQLException {
        List<Field> listField = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRINT_FIELD_DETAIL_BY_ADMIN);
                ptm.setString(1, idField);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String fieldId = rs.getString("fieldId");
                    String fieldName = rs.getString("fieldName");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    String id_of_field_category = rs.getString("categoryFieldId");
                    FieldCategoryDAO fieldCate = new FieldCategoryDAO();
                    FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
                    double price = rs.getDouble("price");
                    String id_of_user = rs.getString("userId");
                    UserDAO user = new UserDAO();
                    User userID = user.getUserByID(id_of_user);
                    String id_of_location = rs.getString("locationId");
                    LocationDAO location = new LocationDAO();
                    Location locationID = location.getLocationByID(id_of_location);
                    String id_of_district = rs.getString("districtId");
                    DistrictDAO district = new DistrictDAO();
                    District districtID = district.getDistrictByID(id_of_district);
                    String status = rs.getString("status");
                    listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, districtID, status));
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

    public boolean updateStatusField(String fieldId, String status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STATUS_FIELD_BY_ADMIN);
                ptm.setString(1, status);
                ptm.setString(2, fieldId);
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

    public boolean updateFieldByOwner(Field field) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_FIELD_BY_OWNER);
                ptm.setString(1, field.getFieldName());
                ptm.setString(2, field.getDescription());
                ptm.setString(3, field.getImage());
                ptm.setString(4, field.getFieldCate().getFieldCateId());
                ptm.setDouble(5, field.getPrice());
                ptm.setString(6, field.getUser().getUserID());
                ptm.setString(7, field.getLocation().getLocationId());
                ptm.setString(8, field.getDistrict().getDistrictId());
                ptm.setString(9, field.getFieldId());
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

    public boolean deleteField(String fieldID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_FIELD_BY_ADMIN);
                ptm.setString(1, fieldID);
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

    public boolean checkExist(String fieldID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EXIST_FIELD);
                ptm.setString(1, fieldID);
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

    public List<Field> searchUserFieldDetailByName(String name, String districtId, int index) throws SQLException {
        List<Field> listField = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_FIELD_BY_USER);
                ptm.setString(1, "%" + name + "%");
                ptm.setString(2, "%" + districtId + "%");
                ptm.setInt(3, index);
                ptm.setInt(4, index);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String fieldId = rs.getString("fieldId");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    String fieldName = rs.getString("fieldName");
                    String id_of_field_category = rs.getString("categoryFieldId");
                    FieldCategoryDAO fieldCate = new FieldCategoryDAO();
                    FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
                    double price = rs.getDouble("price");
                    String id_of_user = rs.getString("userId");
                    UserDAO user = new UserDAO();
                    User userID = user.getUserByID(id_of_user);
                    String id_of_location = rs.getString("locationId");
                    LocationDAO location = new LocationDAO();
                    Location locationID = location.getLocationByID(id_of_location);
                    String id_of_district = rs.getString("districtId");
                    DistrictDAO district = new DistrictDAO();
                    District districtID = district.getDistrictByID(id_of_district);
                    String status = rs.getString("status");
                    listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, districtID, status));
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

    public Field getUserFieldDetailByID(String id_of_field) throws SQLException {
        Field fieldDetail = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRINT_FIELD_DETAIL_BY_ID);
                ptm.setString(1, id_of_field);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String fieldId = rs.getString("fieldId");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    String fieldName = rs.getString("fieldName");
                    String id_of_field_category = rs.getString("categoryFieldId");
                    FieldCategoryDAO fieldCate = new FieldCategoryDAO();
                    FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
                    double price = rs.getDouble("price");
                    String id_of_user = rs.getString("userId");
                    UserDAO user = new UserDAO();
                    User userID = user.getUserByID(id_of_user);
                    String id_of_location = rs.getString("locationId");
                    LocationDAO location = new LocationDAO();
                    Location locationID = location.getLocationByID(id_of_location);
                    String id_of_district = rs.getString("districtId");
                    DistrictDAO district = new DistrictDAO();
                    District districtID = district.getDistrictByID(id_of_district);
                    String status = rs.getString("status");
                    fieldDetail = new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, districtID, status);
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
        return fieldDetail;
    }

    public List<Field> searchFieldByAdmin(String searchBy, String search, String status, int index) throws SQLException {
        List<Field> listField = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (searchBy.equals("Name")) {
                    ptm = conn.prepareStatement(SEARCH_FIELD_BY_NAME);
                    ptm.setString(1, "%" + search + "%");
                    ptm.setString(2, "%" + status + "%");
                    ptm.setInt(3, index);
                    ptm.setInt(4, index);
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        String fieldId = rs.getString("fieldId");
                        String fieldName = rs.getString("fieldName");
                        String description = rs.getString("description");
                        String image = rs.getString("image");
                        String id_of_field_category = rs.getString("categoryFieldId");
                        FieldCategoryDAO fieldCate = new FieldCategoryDAO();
                        FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
                        double price = rs.getDouble("price");
                        String id_of_user = rs.getString("userId");
                        UserDAO user = new UserDAO();
                        User userID = user.getUserByID(id_of_user);
                        String id_of_location = rs.getString("locationId");
                        LocationDAO location = new LocationDAO();
                        Location locationID = location.getLocationByID(id_of_location);
                        String id_of_district = rs.getString("districtId");
                        DistrictDAO district = new DistrictDAO();
                        District districtID = district.getDistrictByID(id_of_district);
                        String statusField = rs.getString("status");
                        listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, districtID, statusField));
                    }
                } else if (searchBy.equals("Category")) {
                    ptm = conn.prepareStatement(SEARCH_FIELD_BY_FIELD_CATE);
                    ptm.setString(1, "%" + search + "%");
                    ptm.setString(2, "%" + status + "%");
                    ptm.setInt(3, index);
                    ptm.setInt(4, index);
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        String fieldId = rs.getString("fieldId");
                        String fieldName = rs.getString("fieldName");
                        String description = rs.getString("description");
                        String image = rs.getString("image");
                        String id_of_field_category = rs.getString("categoryFieldId");
                        FieldCategoryDAO fieldCate = new FieldCategoryDAO();
                        FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
                        double price = rs.getDouble("price");
                        String id_of_user = rs.getString("userId");
                        UserDAO user = new UserDAO();
                        User userID = user.getUserByID(id_of_user);
                        String id_of_location = rs.getString("locationId");
                        LocationDAO location = new LocationDAO();
                        Location locationID = location.getLocationByID(id_of_location);
                        String id_of_district = rs.getString("districtId");
                        DistrictDAO district = new DistrictDAO();
                        District districtID = district.getDistrictByID(id_of_district);
                        String statusField = rs.getString("status");
                        listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, districtID, statusField));
                    }
                } else if (searchBy.equals("Field Owner")) {
                    ptm = conn.prepareStatement(SEARCH_FIELD_BY_FIELD_OWNER);
                    ptm.setString(1, "%" + search + "%");
                    ptm.setString(2, "%" + status + "%");
                    ptm.setInt(3, index);
                    ptm.setInt(4, index);
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        String fieldId = rs.getString("fieldId");
                        String fieldName = rs.getString("fieldName");
                        String description = rs.getString("description");
                        String image = rs.getString("image");
                        String id_of_field_category = rs.getString("categoryFieldId");
                        FieldCategoryDAO fieldCate = new FieldCategoryDAO();
                        FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
                        double price = rs.getDouble("price");
                        String id_of_user = rs.getString("userId");
                        UserDAO user = new UserDAO();
                        User userID = user.getUserByID(id_of_user);
                        String id_of_location = rs.getString("locationId");
                        LocationDAO location = new LocationDAO();
                        Location locationID = location.getLocationByID(id_of_location);
                        String id_of_district = rs.getString("districtId");
                        DistrictDAO district = new DistrictDAO();
                        District districtID = district.getDistrictByID(id_of_district);
                        String statusField = rs.getString("status");
                        listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, districtID, statusField));
                    }
                } else if (searchBy.equals("District")) {
                    ptm = conn.prepareStatement(SEARCH_FIELD_BY_CITY);
                    ptm.setString(1, "%" + search + "%");
                    ptm.setString(2, "%" + status + "%");
                    ptm.setInt(3, index);
                    ptm.setInt(4, index);
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        String fieldId = rs.getString("fieldId");
                        String fieldName = rs.getString("fieldName");
                        String description = rs.getString("description");
                        String image = rs.getString("image");
                        String id_of_field_category = rs.getString("categoryFieldId");
                        FieldCategoryDAO fieldCate = new FieldCategoryDAO();
                        FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
                        double price = rs.getDouble("price");
                        String id_of_user = rs.getString("userId");
                        UserDAO user = new UserDAO();
                        User userID = user.getUserByID(id_of_user);
                        String id_of_location = rs.getString("locationId");
                        LocationDAO location = new LocationDAO();
                        Location locationID = location.getLocationByID(id_of_location);
                        String id_of_district = rs.getString("districtId");
                        DistrictDAO district = new DistrictDAO();
                        District districtID = district.getDistrictByID(id_of_district);
                        String statusField = rs.getString("status");
                        listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, districtID, statusField));
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
        return listField;
    }

    public List<Field> searchFieldByOwner(String searchBy, String search, String status, String userId, int index) throws SQLException {
        List<Field> listField = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (searchBy.equals("Name")) {
                    ptm = conn.prepareStatement(SEARCH_FIELD_OWNER_BY_NAME);
                    ptm.setString(1, "%" + search + "%");
                    ptm.setString(2, "%" + status + "%");
                    ptm.setString(3, userId);
                    ptm.setInt(4, index);
                    ptm.setInt(5, index);
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        String fieldId = rs.getString("fieldId");
                        String fieldName = rs.getString("fieldName");
                        String description = rs.getString("description");
                        String image = rs.getString("image");
                        String id_of_field_category = rs.getString("categoryFieldId");
                        FieldCategoryDAO fieldCate = new FieldCategoryDAO();
                        FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
                        double price = rs.getDouble("price");
                        String id_of_user = rs.getString("userId");
                        UserDAO user = new UserDAO();
                        User userID = user.getUserByID(id_of_user);
                        String id_of_location = rs.getString("locationId");
                        LocationDAO location = new LocationDAO();
                        Location locationID = location.getLocationByID(id_of_location);
                        String id_of_district = rs.getString("districtId");
                        DistrictDAO district = new DistrictDAO();
                        District districtID = district.getDistrictByID(id_of_district);
                        String statusField = rs.getString("status");
                        listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, districtID, statusField));
                    }
                } else if (searchBy.equals("Category")) {
                    ptm = conn.prepareStatement(SEARCH_FIELD_OWNER_BY_FIELD_CATE);
                    ptm.setString(1, "%" + search + "%");
                    ptm.setString(2, "%" + status + "%");
                    ptm.setString(3, userId);
                    ptm.setInt(4, index);
                    ptm.setInt(5, index);
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        String fieldId = rs.getString("fieldId");
                        String fieldName = rs.getString("fieldName");
                        String description = rs.getString("description");
                        String image = rs.getString("image");
                        String id_of_field_category = rs.getString("categoryFieldId");
                        FieldCategoryDAO fieldCate = new FieldCategoryDAO();
                        FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
                        double price = rs.getDouble("price");
                        String id_of_user = rs.getString("userId");
                        UserDAO user = new UserDAO();
                        User userID = user.getUserByID(id_of_user);
                        String id_of_location = rs.getString("locationId");
                        LocationDAO location = new LocationDAO();
                        Location locationID = location.getLocationByID(id_of_location);
                        String id_of_district = rs.getString("districtId");
                        DistrictDAO district = new DistrictDAO();
                        District districtID = district.getDistrictByID(id_of_district);
                        String statusField = rs.getString("status");
                        listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, districtID, statusField));
                    }
                } else if (searchBy.equals("District")) {
                    ptm = conn.prepareStatement(SEARCH_FIELD_OWNER_BY_CITY);
                    ptm.setString(1, "%" + search + "%");
                    ptm.setString(2, "%" + status + "%");
                    ptm.setString(3, userId);
                    ptm.setInt(4, index);
                    ptm.setInt(5, index);
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        String fieldId = rs.getString("fieldId");
                        String fieldName = rs.getString("fieldName");
                        String description = rs.getString("description");
                        String image = rs.getString("image");
                        String id_of_field_category = rs.getString("categoryFieldId");
                        FieldCategoryDAO fieldCate = new FieldCategoryDAO();
                        FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
                        double price = rs.getDouble("price");
                        String id_of_user = rs.getString("userId");
                        UserDAO user = new UserDAO();
                        User userID = user.getUserByID(id_of_user);
                        String id_of_location = rs.getString("locationId");
                        LocationDAO location = new LocationDAO();
                        Location locationID = location.getLocationByID(id_of_location);
                        String id_of_district = rs.getString("districtId");
                        DistrictDAO district = new DistrictDAO();
                        District districtID = district.getDistrictByID(id_of_district);
                        String statusField = rs.getString("status");
                        listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, districtID, statusField));
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
        return listField;
    }

    public boolean checkFieldId(String fieldID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_FIELD_ID);
                ptm.setString(1, fieldID);
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

    public String handleFieldId() {
        int max = 999999;
        int min = 1;
        int random_double = (int) (Math.random() * (max - min + 1) + min);
        String s = String.valueOf(random_double);
        return "FI" + s;
    }

    public String createFieldId() throws SQLException {
        String fieldID = handleFieldId();
        boolean check = false;
        do {
            check = checkFieldId(fieldID);
            if (check == false) {
                fieldID = handleFieldId();
            }
        } while (check);
        return fieldID;
    }

    public boolean createField(Field field) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_FIELD);
                ptm.setString(1, field.getFieldId());
                ptm.setString(2, field.getFieldName());
                ptm.setString(3, field.getDescription());
                ptm.setString(4, field.getImage());
                ptm.setString(5, field.getFieldCate().getFieldCateId());
                ptm.setDouble(6, field.getPrice());
                ptm.setString(7, field.getUser().getUserID());
                ptm.setString(8, field.getLocation().getLocationId());
                ptm.setString(9, field.getDistrict().getDistrictId());
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

    public int countTotalFieldByAdmin() throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_FIELD_BY_ADMIN);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    index = rs.getInt("totalField");
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

    public int countTotalFieldbyFieldOwner(String userId) throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_FIELD_BY_OWNER);
                ptm.setString(1, userId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    index = rs.getInt("totalField");
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

    public int countTotalFieldbyUser() throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_FIELD_BY_USER);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    index = rs.getInt("totalField");
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

    public int countSearchTotalField(String searchBy, String search, String status) throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (searchBy.equals("Name")) {
                ptm = conn.prepareStatement(COUNT_SEARCH_FIELD_BY_NAME);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + status + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    index = rs.getInt("totalField");
                }
            } else if (searchBy.equals("Category")) {
                ptm = conn.prepareStatement(COUNT_SEARCH_FIELD_BY_CATE);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + status + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    index = rs.getInt("totalField");
                }
            } else if (searchBy.equals("Field Owner")) {
                ptm = conn.prepareStatement(COUNT_SEARCH_FIELD_BY_FIELD_OWNER);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + status + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    index = rs.getInt("totalField");
                }
            } else if (searchBy.equals("District")) {
                ptm = conn.prepareStatement(COUNT_SEARCH_FIELD_BY_DISTRICT);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + status + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    index = rs.getInt("totalField");
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

    public int countSearchTotalFieldByOwner(String searchBy, String search, String status, String userId) throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (searchBy.equals("Name")) {
                ptm = conn.prepareStatement(COUNT_SEARCH_FIELD_OWNER_BY_NAME);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + status + "%");
                ptm.setString(3, userId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    index = rs.getInt("totalField");
                }
            } else if (searchBy.equals("Category")) {
                ptm = conn.prepareStatement(COUNT_SEARCH_FIELD_OWNER_BY_CATE);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + status + "%");
                ptm.setString(3, userId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    index = rs.getInt("totalField");
                }
            } else if (searchBy.equals("District")) {
                ptm = conn.prepareStatement(COUNT_SEARCH_FIELD_OWNER_BY_DISTRICT);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + status + "%");
                ptm.setString(3, userId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    index = rs.getInt("totalField");
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

    public int countSearchTotalFieldByUser(String fieldName, String district) throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(COUNT_SEARCH_FIELD_BY_USER);
            ptm.setString(1, "%" + fieldName + "%");
            ptm.setString(2, "%" + district + "%");
            rs = ptm.executeQuery();
            while (rs.next()) {
                index = rs.getInt("totalField");
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

    public boolean isNumeric(String price) {
        if (price == null || price.equals("") || price.trim().length() == 0) {
            return false;
        }
        try {
            double checkPrice = Double.parseDouble(price);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }
}
