package dao;

import dto.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class CityDAO {

    private static final String GET_ALL_INFO = "SELECT cityID, cityName, status FROM tblCity WHERE cityID = ?";
    private static final String GET_ALL_CITY = "SELECT cityID, cityName, status FROM tblCity";
    private static final String CHECK_CITY_ID = "SELECT cityID FROM tblCity WHERE cityID = ?";
    private static final String CHECK_CITY_NAME = "SELECT cityName FROM tblCity WHERE cityName = ?";
    private static final String SEARCH_CITY_BY_ADMIN = "SELECT cityID, cityName, status FROM tblCity WHERE cityName like ? AND status like ?";
    private static final String UPDATE_STATUS_CITY_BY_ADMIN = "UPDATE tblCity SET [status] = ? WHERE cityID = ?";
    private static final String UPDATE_CITY_BY_OWNER = "UPDATE tblCity SET cityName = ? WHERE cityID = ?";
    private static final String DELETE_CITY_BY_ADMIN = "UPDATE tblCity SET [status] = 'false' WHERE cityID = ?";
    private static final String CHECK_EXIST_CITY = "SELECT cityID FROM tblFields WHERE cityID = ?";
    private static final String CREATE_CITY = "INSERT INTO tblCity(cityID, cityName) VALUES(?,?)";

    public City getCityByID(String cityID) throws SQLException {
        City city = null;
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                check = checkCityId(cityID);
                if (check) {
                    ptm = conn.prepareStatement(GET_ALL_INFO);
                    ptm.setString(1, cityID);
                    rs = ptm.executeQuery();
                    if (rs.next()) {
                        String getCityID = rs.getString("cityID");
                        String cityName = rs.getString("cityName");
                        String status = rs.getString("status");
                        String statusAfter = changeNumberStatus(status);
                        city = new City(getCityID, cityName, statusAfter);
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
        return city;
    }

    public List<City> getAllCity() throws SQLException {
        List<City> listCity = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_CITY);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getCityID = rs.getString("cityID");
                    String cityName = rs.getString("cityName");
                    String status = rs.getString("status");
                    String statusAfter = changeNumberStatus(status);
                    listCity.add(new City(getCityID, cityName, statusAfter));
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
        return listCity;
    }

    public boolean checkCityId(String cityID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_CITY_ID);
                ptm.setString(1, cityID);
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

    public boolean checkCityName(String cityName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_CITY_NAME);
                ptm.setString(1, cityName);
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

    public List<City> searchCityByAdmin(String search, String status) throws SQLException {
        List<City> listCity = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_CITY_BY_ADMIN);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + status + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String cityID = rs.getString("cityID");
                    String cityName = rs.getString("cityName");
                    String statusOfCity = rs.getString("status");
                    String statusAfter = changeNumberStatus(statusOfCity);
                    listCity.add(new City(cityID, cityName, statusAfter));
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
        return listCity;
    }

    public boolean updateStatusCity(String cityId, String status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STATUS_CITY_BY_ADMIN);
                ptm.setString(1, status);
                ptm.setString(2, cityId);
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
    
    public boolean updateCityByOwner(City city) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_CITY_BY_OWNER);
                ptm.setString(1, city.getCityName());
                ptm.setString(2, city.getCityId());
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

    public boolean deleteCity(String cityID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_CITY_BY_ADMIN);
                ptm.setString(1, cityID);
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

    public boolean checkExistCity(String cityID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EXIST_CITY);
                ptm.setString(1, cityID);
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

    public String handleCityId() {
        int max = 999999;
        int min = 1;
        int random_double = (int) (Math.random() * (max - min + 1) + min);
        String s = String.valueOf(random_double);
        return "CT" + s;
    }

    public String createCityId() throws SQLException {
        String cityID = handleCityId();
        boolean check = false;
        do {
            check = checkCityId(cityID);
            if (check == false) {
                cityID = handleCityId();
            }
        } while (check);
        return cityID;
    }

    public boolean createCity(City city) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_CITY);
                ptm.setString(1, city.getCityId());
                ptm.setString(2, city.getCityName());
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
