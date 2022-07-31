package dao;

import dto.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class RoleDAO {

    private static final String GET_ROLE = "SELECT roleID FROM tblRoles WHERE roleID = ?";
    private static final String GET_ROLE_BY_ID = "SELECT roleID, roleName, status FROM tblRoles WHERE roleID = ?";
    private static final String GET_ALL_ROLE = "SELECT roleID, roleName, status FROM tblRoles";

    public Role getRole(String roleID) throws SQLException {
        Role role = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ROLE);
                ptm.setString(1, roleID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    
                    role = new Role(roleID, "", "");
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
        return role;
    }
    
    public Role getRoleByID(String roleID) throws SQLException{
    
        Role role = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ROLE_BY_ID);
                ptm.setString(1, roleID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String roleName = rs.getString("roleName");
                    String status = rs.getString("status");
                    String id_of_role = rs.getString("roleID");
                    if (id_of_role.equals("AD"))
                        id_of_role = "Admin";
                    else if (id_of_role.equals("MA"))
                        id_of_role = "Manager";
                    else if(id_of_role.equals("US"))
                        id_of_role = "User";
                    role = new Role(id_of_role, roleName, status);
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
        return role;
    }
    
    public Role getRoleByIDForCreate(String roleID) throws SQLException{
    
        Role role = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ROLE_BY_ID);
                ptm.setString(1, roleID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String roleName = rs.getString("roleName");
                    String status = rs.getString("status");
                    String id_of_role = rs.getString("roleID");
                    role = new Role(id_of_role, roleName, status);
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
        return role;
    }    
    
    public List<Role> getAllRole() throws SQLException {
        List<Role> listRole = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_ROLE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getRoleId = rs.getString("roleId");
                    String roleName = rs.getString("roleName");
                    String status = rs.getString("status");
                    if (status == "1")
                        status = "active";
                    else 
                        status = "In-active";
                    listRole.add(new Role(getRoleId, roleName, status));
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
        return listRole;
    }

}
