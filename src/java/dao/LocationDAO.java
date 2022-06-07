package dao;

import dto.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author NITRO 5
 */
public class LocationDAO {

    private static final String GET_LOCATION_BY_ID = "SELECT locationID, locationName, status "
            + "FROM tblLocation WHERE locationID like ?";
    private static final String GET_ALL_LOCATION = "SELECT locationID, locationName, status FROM tblLocation";
    private static final String CHECK_LOCATION_ID = "SELECT locationID FROM tblLocation WHERE locationID = ?";

    public Location getLocationByID(String locationID) throws SQLException {
        Location location = null;
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                check = checkLocationId(locationID);
                if (check) {
                    ptm = conn.prepareStatement(GET_LOCATION_BY_ID);
                    ptm.setString(1, locationID);
                    rs = ptm.executeQuery();
                    if (rs.next()) {
                        String getLocationID = rs.getString("locationID");
                        String locationName = rs.getString("locationName");
                        String status = rs.getString("status");
                        location = new Location(getLocationID, locationName, status);
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
        return location;
    }

    public List<Location> getAllLocation() throws SQLException {

        List<Location> listLocation = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_LOCATION);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String locationId = rs.getString("locationID");
                    String locationName = rs.getString("locationName");
                    String status = rs.getString("status");
                    listLocation.add(new Location(locationId, locationName, status));
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
        return listLocation;
    }

    public boolean checkLocationId(String LocationID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_LOCATION_ID);
                ptm.setString(1, LocationID);
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
}
