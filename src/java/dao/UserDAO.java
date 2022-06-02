package dao;

import dto.Role;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class UserDAO {

    private static final String CHECK_DUPLICATE = "SELECT fullName FROM tblUsers WHERE userID = ?";
    private static final String CREATE = "INSERT INTO tblUsers(userID, fullName, password, accName, address, birthday, phone, email, roleID, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String LOGIN = "SELECT userID, fullName, address, birthday, phone, accName, roleID, status FROM tblUsers WHERE email = ? AND password = ?";
    private static final String CHECK_PASS = "SELECT email, password FROM tblUsers WHERE email = ? AND password = ?";
    private static final String UPDATE_PASS = "UPDATE tblUsers SET password = ? WHERE email = ? AND password = ?";
    private static final String SEARCH_ACCOUNT_FOR_ADMIN = "SELECT userID, fullName, address, birthday, phone, email, accName, password, roleID, status FROM tblUsers";
    private static final String DELETE_USER = "UPDATE tblUsers SET status = 0 WHERE userID = ?";
    private static final String UPDATE_USER = "UPDATE tblUsers SET fullName = ?, address = ?, birthday = ?, phone = ?, email = ?, status = ?  WHERE userID = ?";
    private static final String GET_ALL_INFO = "SELECT userID, fullName, address, birthday, phone, email, accName, roleID, status FROM tblUsers WHERE userID like ?";
    private static final String GET_USERID = "SELECT userId FROM tblUsers WHERE userId = ?";

    public User getAllInfo(String userID) throws SQLException {
        User customer = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(GET_ALL_INFO);
            stm.setString(1, userID);
            rs = stm.executeQuery();
            if (rs.next()) {
                String getUserID = rs.getString("userID");
                String fullName = rs.getString("fullName");
                String id_of_role = rs.getString("roleID");
                RoleDAO role = new RoleDAO();
                Role roleId = role.getRole(id_of_role);
                String address = rs.getString("address");
                String birthday = rs.getString("birthday");
                String phone = rs.getString("phone");
                String accName = rs.getString("accName");
                String email = rs.getString("email");
                String status = rs.getString("status");
                customer = new User(getUserID, fullName, address, birthday, phone, email, accName, "", roleId, status);
                System.out.println(customer.toString());
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return customer;

    }

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, userID);
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

    public boolean insert(User cus) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, cus.getUserID());
                ptm.setString(2, cus.getFullName());
                ptm.setString(3, cus.getPassword());
                ptm.setString(4, cus.getAccName());
                ptm.setString(5, cus.getAddress());
                ptm.setString(6, cus.getBirth());
                ptm.setString(7, cus.getPhone());
                ptm.setString(8, cus.getEmail());
                ptm.setString(9, cus.getRole().getRoleId()); // sai
                ptm.setString(10, cus.getStatus());
                check = ptm.executeUpdate() > 0;
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

    public User checkLogin(String email, String password) throws SQLException {
        User customer = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(LOGIN);
            stm.setString(1, email);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                String userID = rs.getString("userID");
                String fullName = rs.getString("fullName");
                String id_of_role = rs.getString("roleID");
                RoleDAO role = new RoleDAO();
                Role roleId = role.getRole(id_of_role);
                String address = rs.getString("address");
                String birthday = rs.getString("birthday");
                String accName = rs.getString("accName");
                String phone = rs.getString("phone");
                String status = rs.getString("status");

                customer = new User(userID, fullName, address, birthday, phone, email, accName, "", roleId, status);
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return customer;

    }

    public boolean checkPass(String email, String password) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(CHECK_PASS);
            stm.setString(1, email);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean UpdatePass(String email, String oldPass, String newPass) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(UPDATE_PASS);
            stm.setString(1, newPass);
            stm.setString(2, email);
            stm.setString(3, oldPass);
            check = stm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public String handleUserID() {
        int max = 999999;
        int min = 1;
        int random_double = (int) (Math.random() * (max - min + 1) + min);
        String s = String.valueOf(random_double);
        return "U" + s;
    }

    public String userIDForCustomer() throws SQLException {
        String userID = handleUserID();//ramdom userID
        boolean check = false;
        do {
            check = checkDuplicate(userID);//check trùng ID
        } while (check);
        return userID;
    }

    public List<User> SearchAccountForAdmin() throws SQLException {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_ACCOUNT_FOR_ADMIN);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String address = rs.getString("address");
                    String birthday = rs.getString("birthday");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String accName = rs.getString("accName");
                    String password = rs.getString("password");
                    String id_of_role = rs.getString("roleID");
                    RoleDAO role = new RoleDAO();
                    Role roleId = role.getRole(id_of_role);
                    String status = rs.getString("status");

                    list.add(new User(userID, fullName, address, birthday, phone, email, accName, password, roleId, status));
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
        return list;
    }

    public boolean deleteUser(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_USER);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public boolean updateUser(User user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_USER);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getAddress());
                ptm.setString(3, user.getBirth());
                ptm.setString(3, user.getPhone());
                ptm.setString(3, user.getEmail());
                ptm.setString(3, user.getStatus());

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
    
    public User getUserId(String userID) throws SQLException {
        User user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            try {
                if (conn != null) {
                ptm = conn.prepareStatement(GET_USERID);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String id_of_user = rs.getString("userID");
                    user = new User(id_of_user, "", "", "", "", "", "", "", null, "");
                }
            }
            } catch (Exception e) {
                e.printStackTrace();
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
        return user;
    }
}
