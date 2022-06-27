package dao;

import dto.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class LocationDAO {

    private static final String GET_LOCATION_BY_ID = "SELECT locationID, locationName, status "
            + "FROM tblLocation WHERE locationID like ?";
    private static final String GET_ALL_LOCATION = "SELECT locationID, locationName, status FROM tblLocation";
    private static final String CHECK_LOCATION_ID = "SELECT locationID FROM tblLocation WHERE locationID = ?";
    private static final String CHECK_LOCATION_NAME = "SELECT locationName FROM tblLocation WHERE locationName = ?";
    private static final String SEARCH_LOCATION_BY_ADMIN = "SELECT locationID, locationName, status FROM tblLocation WHERE locationName like ? AND status like ?";
    private static final String DELETE_LOCATION_BY_ADMIN = "UPDATE tblLocation SET [status] = 'false' WHERE locationID = ?";
    private static final String CHECK_DELETE_LOCATION = "SELECT locationID FROM tblFields WHERE locationID = ?";
    private static final String UPDATE_STATUS_LOCATION_BY_ADMIN = "UPDATE tblLocation SET locationName = ?, [status] = ? WHERE locationID = ?";
    private static final String CREATE_LOCATION = "INSERT INTO tblLocation(locationID, locationName) VALUES(?,?)";

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
                    if (status.equals("1")) {
                        status = "Active";
                    } else {
                        status = "In-active";
                    }
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
    
    public boolean checkLocationName(String locationName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_LOCATION_NAME);
                ptm.setString(1, locationName);
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
    
    public List<Location> searchCityByAdmin(String search, String status) throws SQLException {
        List<Location> listLocation = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_LOCATION_BY_ADMIN);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + status + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String locationID = rs.getString("locationID");
                    String locationName = rs.getString("locationName");
                    String statusOfLocation = rs.getString("status");
                    listLocation.add(new Location(locationID, locationName, statusOfLocation));
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
    
    public boolean deleteLocation(String locationID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_LOCATION_BY_ADMIN);
                ptm.setString(1, locationID);
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

    public boolean checkDeleteLocation(String locationID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DELETE_LOCATION);
                ptm.setString(1, locationID);
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
    
    public boolean updateStatusLocation(Location location) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STATUS_LOCATION_BY_ADMIN);
                ptm.setString(1, location.getLocationName());
                ptm.setString(2, location.getStatus());
                ptm.setString(3, location.getLocationId());
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
    
    public String handleLocationId() {
        int max = 999999;
        int min = 1;
        int random_double = (int) (Math.random() * (max - min + 1) + min);
        String s = String.valueOf(random_double);
        return "LO" + s;
    }

    public String createLocationId() throws SQLException {
        String locationID = handleLocationId();
        boolean check = false;
        do {
            check = checkLocationId(locationID);
            if (check == false) {
                locationID = handleLocationId();
            }
        } while (check);
        return locationID;
    }
    
    public boolean createLocation(Location location) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null) {
                ptm = conn.prepareStatement(CREATE_LOCATION);
                ptm.setString(1, location.getLocationId());
                ptm.setString(2, location.getLocationName());
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
