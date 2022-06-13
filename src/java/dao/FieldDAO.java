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

public class FieldDAO {

    private static final String GET_ALL_INFO = "SELECT fieldID, fieldName, description, image, categoryFieldID, price, UserID, LocationID, cityID, status "
            + "FROM tblFields WHERE fieldID like ? ";
    private static final String PRINT_FIELD_DETAIL_BY_NAME = "SELECT fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, cityId, status FROM tblFields WHERE fieldName like ?";

//    private static final String GET_ALL_INFO = "SELECT fieldID, fieldName, description, image, categoryFieldID, UserID, LocationID, cityID, status "
//            + "FROM tblFields WHERE fieldID like ? ";
    private static final String GET_FIELD = "SELECT fieldName FROM tblFields WHERE fieldID like ?";
    private static final String PRINT_ALL_FIELD_BY_ADMIN = "SELECT fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, cityId, status FROM tblFields";
    private static final String PRINT_FIELD_DETAIL_BY_ADMIN = "SELECT fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, cityId, status FROM tblFields WHERE fieldId like ?";
    private static final String UPDATE_STATUS_FIELD_BY_ADMIN = "UPDATE tblFields SET fieldName = ?, [description] = ?, [image] = ?, categoryFieldId = ?, price = ?, userId = ?, locationId = ?, cityId = ?, [status] = ? WHERE fieldId = ?";
    private static final String DELETE_FIELD_BY_ADMIN = "UPDATE tblFields SET [status] = 'false' WHERE fieldId = ?";
    private static final String CHECK_DELETE_FIELD = "SELECT fieldId FROM tblBookingDetail WHERE fieldId = ?";
    private static final String SEARCH_FIELD_BY_NAME = "SELECT fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, cityId, status FROM tblFields WHERE fieldName like ? AND status like ?";
    private static final String SEARCH_FIELD_BY_FIELD_CATE = "SELECT fieldId, fieldName, description, image, f.categoryFieldId, price, userId, locationId, cityId, f.status, fc.categoryFieldName FROM tblFields f LEFT JOIN tblFieldCategory fc ON f.categoryFieldId = fc.categoryFieldId WHERE fc.categoryFieldName like ? AND f.status like ?";
    private static final String SEARCH_FIELD_BY_FIELD_OWNER = "SELECT fieldId, fieldName, description, image, categoryFieldId, price, f.userId, locationId, f.cityId, f.status FROM tblFields f LEFT JOIN tblUsers us ON f.userId = us.userId WHERE us.fullName like ? AND f.status like ?";
    private static final String SEARCH_FIELD_BY_CITY = "SELECT fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, f.cityId, f.status FROM tblFields f LEFT JOIN tblCity ci ON f.cityId = ci.cityId WHERE ci.cityName like ? AND f.status like ?";
    private static final String CHECK_FIELD_ID = "SELECT fieldId FROM tblFields WHERE fieldId = ?";
    private static final String CREATE_FIELD = "INSERT INTO tblFields(fieldId, fieldName, description, image, categoryFieldId, price, userId, locationId, cityId) VALUES(?,?,?,?,?,?,?,?,?)";
    
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

                    double price = rs.getDouble("price");

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

                    field = new Field(getFieldID, fieldName, description, image, fieldCategory, price, user, location, city, status);

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

    public List<Field> getListField() throws SQLException {
        List<Field> listField = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRINT_ALL_FIELD_BY_ADMIN);
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
                    String id_of_city = rs.getString("cityId");
                    CityDAO city = new CityDAO();
                    City cityID = city.getCityByID(id_of_city);
                    String status = rs.getString("status");
                    if (status.equals("1")) {
                        status = "Active";
                    } else {
                        status = "In-active";
                    }
                    listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, cityID, status));
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
                    String id_of_city = rs.getString("cityId");
                    CityDAO city = new CityDAO();
                    City cityID = city.getCityByID(id_of_city);
                    String status = rs.getString("status");
                    listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, cityID, status));
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
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STATUS_FIELD_BY_ADMIN);
                ptm.setString(1, field.getFieldName());
                ptm.setString(2, field.getDescription());
                ptm.setString(3, field.getImage());
                ptm.setString(4, field.getFieldCate().getFieldCateId());
                ptm.setDouble(5, field.getPrice());
                ptm.setString(6, field.getUser().getUserID());
                ptm.setString(7, field.getLocation().getLocationId());
                ptm.setString(8, field.getCity().getCityId());
                ptm.setString(9, field.getStatus());
                ptm.setString(10, field.getFieldId());
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

    public boolean checkDeleteField(String fieldID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DELETE_FIELD);
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

    public List<Field> getFieldDetailByName(String name) throws SQLException {
        List<Field> listField = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRINT_FIELD_DETAIL_BY_NAME);
                ptm.setString(1, "%" + name + "%");
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
                    String id_of_city = rs.getString("cityId");
                    CityDAO city = new CityDAO();
                    City cityID = city.getCityByID(id_of_city);
                    String status = rs.getString("status");
                    listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, cityID, status));
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

//    public List<Field> getListFieldByAdmin() throws SQLException {
//        List<Field> listField = new ArrayList<>();
//        ResultSet rs = null;
//        try {
//            while (rs.next()) {
//                String fieldId = rs.getString("fieldId");
//                String fieldName = rs.getString("fieldName");
//                String description = rs.getString("description");
//                String image = rs.getString("image");
//                String id_of_field_category = rs.getString("categoryFieldId");
//                FieldCategoryDAO fieldCate = new FieldCategoryDAO();
//                FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
//                double price = rs.getDouble("price");
//                String id_of_user = rs.getString("userId");
//                UserDAO user = new UserDAO();
//                User userID = user.getUserByID(id_of_user);
//                String id_of_location = rs.getString("locationId");
//                LocationDAO location = new LocationDAO();
//                Location locationID = location.getLocationByID(id_of_location);
//                String id_of_city = rs.getString("cityId");
//                CityDAO city = new CityDAO();
//                City cityID = city.getCityByID(id_of_city);
//                String status = rs.getString("status");
//                listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, cityID, status));
//            }
//        } catch (Exception e) {
//        }
//        return listField;
//    }

    public List<Field> searchFieldByAdmin(String searchBy, String search, String status) throws SQLException {
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
                        String id_of_city = rs.getString("cityId");
                        CityDAO city = new CityDAO();
                        City cityID = city.getCityByID(id_of_city);
                        String statusField = rs.getString("status");
                        listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, cityID, statusField));
                    }
                } else if (searchBy.equals("Category")) {
                    ptm = conn.prepareStatement(SEARCH_FIELD_BY_FIELD_CATE);
                    ptm.setString(1, "%" + search + "%");
                    ptm.setString(2, "%" + status + "%");
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
                        String id_of_city = rs.getString("cityId");
                        CityDAO city = new CityDAO();
                        City cityID = city.getCityByID(id_of_city);
                        String statusField = rs.getString("status");
                        listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, cityID, statusField));
                    }
                } else if (searchBy.equals("Field Owner")) {
                    ptm = conn.prepareStatement(SEARCH_FIELD_BY_FIELD_OWNER);
                    ptm.setString(1, "%" + search + "%");
                    ptm.setString(2, "%" + status + "%");
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
                        String id_of_city = rs.getString("cityId");
                        CityDAO city = new CityDAO();
                        City cityID = city.getCityByID(id_of_city);
                        String statusField = rs.getString("status");
                        listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, cityID, statusField));
                    }
                } else if (searchBy.equals("City")) {
                    ptm = conn.prepareStatement(SEARCH_FIELD_BY_CITY);
                    ptm.setString(1, "%" + search + "%");
                    ptm.setString(2, "%" + status + "%");
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
                        String id_of_city = rs.getString("cityId");
                        CityDAO city = new CityDAO();
                        City cityID = city.getCityByID(id_of_city);
                        String statusField = rs.getString("status");
                        listField.add(new Field(fieldId, fieldName, description, image, categoryFieldID, price, userID, locationID, cityID, statusField));
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
            if(conn != null) {
                ptm = conn.prepareStatement(CREATE_FIELD);
                ptm.setString(1, field.getFieldId());
                ptm.setString(2, field.getFieldName());
                ptm.setString(3, field.getDescription());
                ptm.setString(4, field.getImage());
                ptm.setString(5, field.getFieldCate().getFieldCateId());
                ptm.setDouble(6, field.getPrice());
                ptm.setString(7, field.getUser().getUserID());
                ptm.setString(8, field.getLocation().getLocationId());
                ptm.setString(9, field.getCity().getCityId());
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
