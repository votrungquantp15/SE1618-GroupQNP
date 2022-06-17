package dao;

import dto.City;
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
    private static final String CREATE = "INSERT INTO tblUsers(userID, fullName, cityId, birthday, phone, accName,email, roleId, status, password) VALUES (? ,?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String LOGIN = "SELECT userID, fullName, address, cityId, birthday, phone, accName, roleID, status FROM tblUsers WHERE email = ? AND password = ?";
    private static final String CHECK_PASS = "SELECT email, password FROM tblUsers WHERE email = ? AND password = ?";
    private static final String UPDATE_PASS = "UPDATE tblUsers SET password = ? WHERE email = ? AND password = ?";
    private static final String SEARCH_ACCOUNT_BY_NAME_FOR_ADMIN = "SELECT userID, fullName, address, cityId, birthday, phone, email, accName, password, roleId, status FROM tblUsers WHERE fullName LIKE ? ";
    private static final String SEARCH_ACCOUNT_BY_ID_FOR_ADMIN = "SELECT userID, fullName, address, cityId, birthday, phone, email, accName, password, roleId, status FROM tblUsers WHERE userID LIKE ? ";
    private static final String DELETE_USER = "UPDATE tblUsers SET status = 0 WHERE userID = ?";
    private static final String UPDATE_USER = "UPDATE tblUsers SET fullName = ?, address = ?, cityId = ?, birthday = ?, phone = ?, email = ?, accName = ?, password = ?, roleId = ?, status = ?  WHERE userID = ?";
    private static final String GET_USER_BY_ID = "SELECT userID, fullName, address, cityId, birthday, phone, email, accName, status, roleID FROM tblUsers WHERE userID = ?";

    private static final String GET_ALL_USER = "SELECT userID, fullName, address, cityId, birthday, phone, email, accName, status, roleID FROM tblUsers";
    private static final String CHECK_USER_ID = "SELECT userID FROM tblUsers WHERE userID = ?";
    // Update Profile User
    private static final String UPDATE_PROFILE_USER = "UPDATE tblUsers SET  fullName = ?, birthday = ?, phone = ?, email = ?, address = ?  WHERE userID = ?";

    private static final String VIEW_ACCOUNT_LIST = "SELECT userID, fullName, address, cityId, birthday, phone, email, accName, password, roleId, status FROM tblUsers";
    
    // check email tồn tại
    private static final String  CHECK_EMAIL_EXISTED = "SELECT email FROM tblUsers WHERE email = ?" ;

    // Update Profile User
    public User getUserByID(String userID) throws SQLException {

        User user = null;
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            check = checkUserId(userID);
            if (check) {
                stm = conn.prepareStatement(GET_USER_BY_ID);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String getUserID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String id = rs.getString("roleID");
                    RoleDAO roleDAO = new RoleDAO();
                    Role role = roleDAO.getRoleByID(id);
                    String address = rs.getString("address");
                    String cityId = rs.getString("cityId");
                    CityDAO cityDao = new CityDAO();
                    City city = cityDao.getCityByID(cityId);
                    String birthday = rs.getString("birthday");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String accName = rs.getString("accName");
                    String status = rs.getString("status");
                    user = new User(getUserID, fullName, address, city, birthday, phone, email, accName, "", role, status);
                }
            }
        } catch (Exception e) {
            e.getMessage();
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
        return user;
    }

    public boolean checkUserId(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_USER_ID);
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

    public List<User> getAllUser() throws SQLException {
        List<User> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(GET_ALL_USER);
            rs = stm.executeQuery();
            while (rs.next()) {
                String getUserID = rs.getString("userID");
                String fullName = rs.getString("fullName");
                String id = rs.getString("roleID");
                RoleDAO roleDAO = new RoleDAO();
                Role role = roleDAO.getRoleByID(id);
                String address = rs.getString("address");
                String cityId = rs.getString("cityId");
                CityDAO cityDao = new CityDAO();
                City city = cityDao.getCityByID(cityId);
                String birthday = rs.getString("birthday");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String accName = rs.getString("accName");
                String status = rs.getString("status");
                listUser.add(new User(getUserID, fullName, address, city, birthday, phone, email, accName, "", role, status));
            }
        } catch (Exception e) {
            e.getMessage();
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
        return listUser;
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
                ptm.setString(3, cus.getCity().getCityId());
                ptm.setString(4, cus.getBirth());
                ptm.setString(5, cus.getPhone());
                ptm.setString(6, cus.getAccName());
                ptm.setString(7, cus.getEmail());
                ptm.setString(8, cus.getRole().getRoleId()); // sai
                ptm.setString(9, cus.getStatus());
                ptm.setString(10, cus.getPassword());
                String a1 = cus.getUserID();
                String a2 =cus.getFullName();
                String a3 = cus.getCity().getCityId();
                String a4 = cus.getBirth();
                String a5 = cus.getPhone();
                String a6 = cus.getAccName();
                String a7 =cus.getEmail();
                String a8 = cus.getRole().getRoleId();
                String a9 = cus.getStatus();
                String a10 = cus.getPassword();
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
        User user = null;
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
                String id = rs.getString("roleID");
                RoleDAO roleDAO = new RoleDAO();
                Role role = roleDAO.getRole(id);
                String address = rs.getString("address");
                String cityId = rs.getString("cityId");
                CityDAO cityDao = new CityDAO();
                City city = cityDao.getCityByID(cityId);
                String birthday = rs.getString("birthday");
                String accName = rs.getString("accName");
                String phone = rs.getString("phone");
                String status = rs.getString("status");
                user = new User(userID, fullName, address, city, birthday, phone, email, accName, "", role, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return user;

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
            if (check == false) {
                userID = handleUserID();
            }
        } while (check);
        return userID;
    }

    public List<User> searchAccountByNameForAdmin(String search) throws SQLException {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            try {
                if (conn != null) {
                    ptm = conn.prepareStatement(SEARCH_ACCOUNT_BY_NAME_FOR_ADMIN);
                    ptm.setString(1, "%" + search + "%");
                    rs = ptm.executeQuery();

                    while (rs.next()) {
                        String userID = rs.getString("userID");
                        String fullName = rs.getString("fullName");
                        String address = rs.getString("address");
                        String cityId = rs.getString("cityId");
                        CityDAO cityDao = new CityDAO();
                        City city = cityDao.getCityByID(cityId);
                        String birthday = rs.getString("birthday");
                        String phone = rs.getString("phone");
                        String email = rs.getString("email");
                        String accName = rs.getString("accName");
                        String password = rs.getString("password");
                        String id_of_role = rs.getString("roleID");
                        RoleDAO role = new RoleDAO();
                        Role roleID = role.getRoleByID(id_of_role);
                        String status = rs.getString("status");
                        if (status.equals("1")) {
                            status = "active";
                        } else {
                            status = "in-active";
                        }

                        list.add(new User(userID, fullName, address, city, birthday, phone, email, accName, password, roleID, status));
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
        return list;
    }

    public List<User> searchAccountByIDForAdmin(String search) throws SQLException {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            try {
                if (conn != null) {
                    ptm = conn.prepareStatement(SEARCH_ACCOUNT_BY_ID_FOR_ADMIN);
                    ptm.setString(1, "%" + search + "%");
                    rs = ptm.executeQuery();

                    while (rs.next()) {
                        String userID = rs.getString("userID");
                        String fullName = rs.getString("fullName");
                        String address = rs.getString("address");
                        String cityId = rs.getString("cityId");
                        CityDAO cityDao = new CityDAO();
                        City city = cityDao.getCityByID(cityId);
                        String birthday = rs.getString("birthday");
                        String phone = rs.getString("phone");
                        String email = rs.getString("email");
                        String accName = rs.getString("accName");
                        String password = rs.getString("password");
                        String id_of_role = rs.getString("roleId");
                        RoleDAO role = new RoleDAO();
                        Role roleID = role.getRole(id_of_role);
                        String status = rs.getString("status");

                        list.add(new User(userID, fullName, address, city, birthday, phone, email, accName, password, roleID, status));
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
                ptm.setString(3, user.getCity().getCityId());
                ptm.setString(4, user.getBirth());
                ptm.setString(5, user.getPhone());
                ptm.setString(6, user.getEmail());
                ptm.setString(7, user.getAccName());
                ptm.setString(8, user.getPassword());
                ptm.setString(9, user.getRole().getRoleId());
                ptm.setString(10, user.getStatus());
                ptm.setString(11, user.getUserID());

                check = ptm.executeUpdate() > 0 ? true : false;
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

    public boolean updateProfileUser(User user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            try {
                if (conn != null) {
                    ptm = conn.prepareStatement(UPDATE_PROFILE_USER);
                    ptm.setString(1, user.getFullName());
                    ptm.setString(2, user.getBirth());
                    ptm.setString(3, user.getPhone());
                    ptm.setString(4, user.getEmail());
                    ptm.setString(5, user.getAddress());
                    ptm.setString(6, user.getUserID());
                    check = ptm.executeUpdate() > 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
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

    public List<User> viewAccountList() throws SQLException {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(VIEW_ACCOUNT_LIST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String address = rs.getString("address");
                    String cityId = rs.getString("cityId");
                    CityDAO cityDao = new CityDAO();
                    City city = cityDao.getCityByID(cityId);
                    String birthday = rs.getString("birthday");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String accName = rs.getString("accName");
                    String password = rs.getString("password");
                    String id_of_role = rs.getString("roleID");

                    RoleDAO role = new RoleDAO();
                    Role roleID = role.getRoleByID(id_of_role);

                    String status = rs.getString("status");
                    if (status.equals("1")) {
                        status = "Active";
                    } else {
                        status = "In-active";
                    }

                    list.add(new User(userID, fullName, address, city, birthday, phone, email, accName, password, roleID, status));
                }
            }
            ///aa

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
    
    public boolean checkEmailExisted (String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EMAIL_EXISTED);
                ptm.setString(1, email);
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


