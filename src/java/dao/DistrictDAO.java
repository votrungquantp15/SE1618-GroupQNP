package dao;

import dto.District;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class DistrictDAO {

    private static final String GET_ALL_INFO = "SELECT districtID, districtName, status FROM tblDistrict WHERE districtID = ?";
    private static final String GET_ALL_CITY = "SELECT districtID, districtName, status FROM tblDistrict";
    private static final String CHECK_CITY_ID = "SELECT districtID FROM tblDistrict WHERE districtID = ?";
    private static final String CHECK_CITY_NAME = "SELECT districtName FROM tblDistrict WHERE districtName = ?";
    private static final String SEARCH_CITY_BY_ADMIN = "SELECT districtID, districtName, status FROM tblDistrict WHERE districtName like ? AND status like ?";
    private static final String UPDATE_STATUS_CITY_BY_ADMIN = "UPDATE tblDistrict SET [status] = ? WHERE districtID = ?";
    private static final String UPDATE_CITY_BY_OWNER = "UPDATE tblDistrict SET districtName = ? WHERE districtID = ?";
    private static final String DELETE_CITY_BY_ADMIN = "UPDATE tblDistrict SET [status] = 'In-Active' WHERE districtID = ?";
    private static final String CHECK_EXIST_CITY = "SELECT districtID FROM tblFields WHERE districtID = ?";
    private static final String CREATE_CITY = "INSERT INTO tblDistrict(districtID, districtName) VALUES(?,?)";
    
    private static final String COUNT_ALL_DISTRICT = "SELECT COUNT(*) as totalDistrict FROM tblDistrict";
    private static final String GET_ALL_CITY_PAGING = "SELECT districtID, districtName, status FROM tblDistrict ORDER BY districtID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";

    public District getDistrictByID(String districtID) throws SQLException {
        District district = null;
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                check = checkDistrictId(districtID);
                if (check) {
                    ptm = conn.prepareStatement(GET_ALL_INFO);
                    ptm.setString(1, districtID);
                    rs = ptm.executeQuery();
                    if (rs.next()) {
                        String getDistrictID = rs.getString("districtID");
                        String districtName = rs.getString("districtName");
                        String status = rs.getString("status");
                        district = new District(getDistrictID, districtName, status);
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
        return district;
    }

    public List<District> getAllDistrict() throws SQLException {
        List<District> listDistrict = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_CITY);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getDistrictID = rs.getString("districtID");
                    String districtName = rs.getString("districtName");
                    String status = rs.getString("status");
                    listDistrict.add(new District(getDistrictID, districtName, status));
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
        return listDistrict;
    }
    
    public List<District> getAllDistrictPaging(int index) throws SQLException {
        List<District> listDistrict = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_CITY_PAGING);
                ptm.setInt(1, (index - 1) * 5);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getDistrictID = rs.getString("districtID");
                    String districtName = rs.getString("districtName");
                    String status = rs.getString("status");
                    listDistrict.add(new District(getDistrictID, districtName, status));
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
        return listDistrict;
    }
    
    public boolean checkDistrictId(String districtID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_CITY_ID);
                ptm.setString(1, districtID);
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

    public boolean checkDistrictName(String districtName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_CITY_NAME);
                ptm.setString(1, districtName);
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

    public List<District> searchDistrictByAdmin(String search, String status) throws SQLException {
        List<District> listDistrict = new ArrayList<>();
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
                    String districtID = rs.getString("districtID");
                    String districtName = rs.getString("districtName");
                    String statusOfDistrict = rs.getString("status");
                    listDistrict.add(new District(districtID, districtName, statusOfDistrict));
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
        return listDistrict;
    }

    public boolean updateStatusDistrict(String districtId, String status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STATUS_CITY_BY_ADMIN);
                ptm.setString(1, status);
                ptm.setString(2, districtId);
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
    
    public boolean updateDistrictByOwner(District district) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_CITY_BY_OWNER);
                ptm.setString(1, district.getDistrictName());
                ptm.setString(2, district.getDistrictId());
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

    public boolean deleteDistrict(String districtID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_CITY_BY_ADMIN);
                ptm.setString(1, districtID);
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

    public boolean checkExistDistrict(String districtID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EXIST_CITY);
                ptm.setString(1, districtID);
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

    public String handleDistrictId() {
        int max = 999999;
        int min = 1;
        int random_double = (int) (Math.random() * (max - min + 1) + min);
        String s = String.valueOf(random_double);
        return "DI" + s;
    }

    public String createDistrictId() throws SQLException {
        String districtID = handleDistrictId();
        boolean check = false;
        do {
            check = checkDistrictId(districtID);
            if (check == false) {
                districtID = handleDistrictId();
            }
        } while (check);
        return districtID;
    }

    public boolean createDistrict(District district) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_CITY);
                ptm.setString(1, district.getDistrictId());
                ptm.setString(2, district.getDistrictName());
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
    
    public int countTotalDistrict() throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_DISTRICT);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    index = rs.getInt("totalDistrict");
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
