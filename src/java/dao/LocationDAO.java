package dao;

import dto.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

public class LocationDAO {
    
    private static final String GET_LOCATION = "SELECT locationId FROM tblLocation WHERE locationId = ?";
    
    public Location getLocationId(String locationID) throws SQLException {
        Location location = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LOCATION);
                ptm.setString(1, locationID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String id_of_location = rs.getString("locationID");
                    location = new Location(id_of_location, "", "");
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
