/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

public class CityDAO {
    private static final String GET_ALL_INFO = "SELECT cityID, cityName, status FROM tblCity WHERE cityID like ?";
    
    public City getCityByID(String cityID) throws SQLException {
        City city = new City();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_INFO);
                ptm.setString(1, cityID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String getCityID = rs.getString("cityID");
                    String cityName = rs.getString("cityName");
                    String status = rs.getString("status");
                    city = new City(getCityID, cityName, status);
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
