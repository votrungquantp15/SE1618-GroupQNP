package dao;

import dto.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

public class CityDAO {
    
    private static final String GET_CITY= "SELECT cityID FROM tblCity WHERE cityID = ?";
    
    public City getCityId(String cityID) throws SQLException {
        City city = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CITY);
                ptm.setString(1, cityID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String id_of_city = rs.getString("cityID");
                    city = new City(id_of_city, "", "");
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
}
