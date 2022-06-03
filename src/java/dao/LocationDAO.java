/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author NITRO 5
 */
public class LocationDAO {

    private static final String GET_ALL_INFO = "SELECT locationID, locationName, status "
            + "FROM tblLocation WHERE locationID like ?";

    public Location getLocationByID(String locationID) throws SQLException {
        Location location = new Location();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_INFO);
                ptm.setString(1, locationID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String getLocationID = rs.getString("locationID");
                    String locationName = rs.getString("locationName");
                    String status = rs.getString("status");
                    location = new Location(getLocationID, locationName, status);
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
        return location;
    }
}
