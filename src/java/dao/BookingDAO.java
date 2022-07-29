/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Booking;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class BookingDAO {

    private static final String ON_GOING_STATUS = "On-Going";
    private static final String CANCELED_STATUS = "Canceled";
    private static final String DELETE_STATUS = "Delete";
    
    private static final String INSERT_BOOKING = "INSERT INTO tblBooking(bookingId, bookingDate, userId, totalprice, [status]) VALUES (?, ?, ?, ?, 'On-Going')";

    private static final String GET_BOOKING_BY_BOOKING_ID = "SELECT bookingID, bookingDate, userID, totalPrice, status "
            + "FROM tblBooking WHERE bookingID like ? ";
    private static final String DELETE_BOOKING_BY_BOOKING_ID = "UPDATE tblBooking SET status = ? WHERE bookingID like ? ";
    private static final String UPDATE_BOOKING_STATUS_BY_ID = "UPDATE tblBooking SET status = ? WHERE bookingID = ? ";
    private static final String GET_BOOKING_ID = "Select bookingID from tblBooking WHERE  bookingID = ?";

    private static final String COUNT_ALL_BOOKING_WITH_DATE = "SELECT COUNT(*) as totalBooking FROM tblBooking "
            + "WHERE userID = ? AND status like ? AND status not like 'Delete' AND bookingDate BETWEEN ? AND ? ";
    private static final String COUNT_ALL_BOOKING_ADMIN_WITH_DATE = "SELECT COUNT(*) as totalBooking FROM tblBooking WHERE status like ? "
            + "AND bookingDate BETWEEN ? AND ? ";
    private static final String COUNT_ALL_BOOKING_MANAGER = "SELECT COUNT(A.bookingId) as totalBooking FROM tblBooking A, tblBookingDetail B, tblFields C, tblUsers D "
            + "WHERE A.bookingId=B.bookingId AND B.fieldId=C.fieldId AND A.userId=D.userId AND C.userId = ? AND A.status like ? AND D.fullName like ? ";
    private static final String COUNT_ALL_BOOKING_MANAGER_WITH_DATE = "SELECT COUNT(A.bookingId) as totalBooking FROM tblBooking A, tblBookingDetail B, tblFields C, tblUsers D "
            + "WHERE A.bookingId=B.bookingId AND B.fieldId=C.fieldId AND A.userId=D.userId AND C.userId = ? AND A.status like ? AND D.fullName like ? AND A.bookingDate BETWEEN ? AND ? ";

    private static final String PAGING_LIST_ALL_BOOKING = "SELECT * FROM tblBooking WHERE userID = ? AND status like ? AND status not like 'Delete' "
            + "ORDER BY status DESC, bookingDate DESC OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY ";
    private static final String PAGING_LIST_BOOKING_ADMIN = "SELECT * FROM tblBooking WHERE status like ? "
            + "ORDER BY status DESC, bookingDate DESC OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY ";

    private static final String COUNT_LIST_BOOKING = "SELECT COUNT(*) as totalBooking FROM tblBooking "
            + "WHERE userID = ? AND status like ? AND status not like 'Delete' ";
    private static final String COUNT_LIST_BOOKING_ADMIN = "SELECT COUNT(*) as totalBooking FROM tblBooking WHERE status like ? ";

    private static final String PAGING_LIST_BOOKING_WITH_DATE = "SELECT * FROM tblBooking WHERE userID = ? "
            + "AND status like ? AND status not like 'Delete' AND bookingDate BETWEEN ? AND ? "
            + "ORDER BY bookingId OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY ";
    private static final String PAGING_LIST_BOOKING_ADMIN_WITH_DATE = "SELECT * FROM tblBooking WHERE status like ? AND bookingDate BETWEEN ? AND ? "
            + "ORDER BY bookingId OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY ";

    private static final String PAGING_LIST_ALL_BOOKING_MANAGER = "SELECT A.bookingId, A.bookingDate, A.userId, A.totalprice, A.status FROM tblBooking A, tblBookingDetail B, tblFields C, tblUsers D WHERE A.userId=D.userId AND A.bookingId=B.bookingId AND B.fieldId=C.fieldId AND C.userId = ? AND A.status like ? AND D.fullName like ? "
            + "ORDER BY A.status DESC, A.bookingDate DESC OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY ";

    private static final String PAGING_LIST_ALL_BOOKING_MANAGER_WITH_DATE = "SELECT A.bookingId, A.bookingDate, A.userId, A.totalprice, A.status FROM tblBooking A, tblBookingDetail B, tblFields C, tblUsers D WHERE A.userId=D.userId AND A.bookingId=B.bookingId AND B.fieldId=C.fieldId AND C.userId = ? AND A.status like ? AND D.fullName like ? AND A.bookingDate between ? AND ? "
            + "ORDER BY A.status DESC, A.bookingDate DESC OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY ";

    public List<Booking> getListBooking(String userID, int index, String status) throws SQLException {
        List<Booking> booking = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PAGING_LIST_ALL_BOOKING);
                ptm.setString(1, userID);
                ptm.setString(2, "%" + status + "%");
                ptm.setInt(3, (index - 1) * 10);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getBookingID = rs.getString("bookingID");
                    String getBookingDate = rs.getString("bookingDate");
                    String getUserID = rs.getString("userID");
                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.getUserByID(getUserID);
                    double getTotalPrice = rs.getDouble("totalPrice");
                    String getStatus = rs.getString("status");
                    if (!getStatus.equals(DELETE_STATUS)) {
                        booking.add(new Booking(getBookingID, getBookingDate, user, getTotalPrice, getStatus));
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
        return booking;
    }

    public List<Booking> getListBookingAdmin(int index, String status) throws SQLException {
        List<Booking> booking = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PAGING_LIST_BOOKING_ADMIN);
                ptm.setString(1, "%" + status + "%");
                ptm.setInt(2, (index - 1) * 10);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getBookingID = rs.getString("bookingID");
                    String getBookingDate = rs.getString("bookingDate");
                    String userID = rs.getString("userID");
                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.getUserByID(userID);
                    double getTotalPrice = rs.getDouble("totalPrice");
                    String getStatus = rs.getString("status");

                    booking.add(new Booking(getBookingID, getBookingDate, user, getTotalPrice, getStatus));
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
        return booking;
    }

    public List<Booking> getListBookingManager(String userID, String status, String search, int index) throws SQLException {
        List<Booking> booking = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PAGING_LIST_ALL_BOOKING_MANAGER);
                ptm.setString(1, userID);
                ptm.setString(2, "%" + status + "%");
                ptm.setString(3, "%" + search + "%");
                ptm.setInt(4, (index - 1) * 10);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getBookingID = rs.getString("bookingID");
                    String getBookingDate = rs.getString("bookingDate");
                    String getUserID = rs.getString("userID");
                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.getUserByID(getUserID);
                    double getTotalPrice = rs.getDouble("totalPrice");
                    String getStatus = rs.getString("status");
                    booking.add(new Booking(getBookingID, getBookingDate, user, getTotalPrice, getStatus));
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
        return booking;
    }

    public List<Booking> getListBookingWithDate(String userID, String startDate, String endDate, String status, int index) throws SQLException {
        List<Booking> booking = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PAGING_LIST_BOOKING_WITH_DATE);
                ptm.setString(1, userID);
                ptm.setString(2, "%" + status + "%");
                ptm.setString(3, startDate);
                ptm.setString(4, endDate);
                ptm.setInt(5, (index - 1) * 10);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getBookingID = rs.getString("bookingID");
                    String getBookingDate = rs.getString("bookingDate");
                    String getUserID = rs.getString("userID");
                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.getUserByID(getUserID);
                    double getTotalPrice = rs.getDouble("totalPrice");
                    String getStatus = rs.getString("status");
                    if (!getStatus.equals(DELETE_STATUS)) {
                        booking.add(new Booking(getBookingID, getBookingDate, user, getTotalPrice, getStatus));
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
        return booking;
    }

    public List<Booking> getListBookingAdminWithDate(String startDate, String endDate, String status, int index) throws SQLException {
        List<Booking> booking = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PAGING_LIST_BOOKING_ADMIN_WITH_DATE);
                ptm.setString(1, "%" + status + "%");
                ptm.setString(2, startDate);
                ptm.setString(3, endDate);
                ptm.setInt(4, (index - 1) * 10);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getBookingID = rs.getString("bookingID");
                    String getBookingDate = rs.getString("bookingDate");
                    String getUserID = rs.getString("userID");
                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.getUserByID(getUserID);
                    double getTotalPrice = rs.getDouble("totalPrice");
                    String getStatus = rs.getString("status");

                    booking.add(new Booking(getBookingID, getBookingDate, user, getTotalPrice, getStatus));
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
        return booking;
    }

    public List<Booking> getListBookingManagerWithDate(String userID, String status, String search, String startDate, String endDate, int index) throws SQLException {
        List<Booking> booking = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PAGING_LIST_ALL_BOOKING_MANAGER_WITH_DATE);
                ptm.setString(1, userID);
                ptm.setString(2, "%" + status + "%");
                ptm.setString(3, "%" + search + "%");
                ptm.setString(4, startDate);
                ptm.setString(5, endDate);
                ptm.setInt(6, (index - 1) * 10);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookingID = rs.getString("bookingID");
                    String bookingDate = rs.getString("bookingDate");

                    String getUserID = rs.getString("userID");
                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.getUserByID(getUserID);

                    double totalPrice = rs.getDouble("totalPrice");
                    String getStatus = rs.getString("status");

                    booking.add(new Booking(bookingID, bookingDate, user, totalPrice, getStatus));
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
        return booking;
    }

    public Booking getBookingByID(String bookingID) throws SQLException {
        Booking booking = new Booking();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BOOKING_BY_BOOKING_ID);
                ptm.setString(1, bookingID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getBookingID = rs.getString("bookingID");
                    String getBookingDate = rs.getString("bookingDate");
                    String getUserID = rs.getString("userID");
                    UserDAO userDAO = new UserDAO();
                    User User = userDAO.getUserByID(getUserID);
                    double getTotalPrice = rs.getDouble("totalPrice");
                    String status = rs.getString("status");
                    booking = new Booking(getBookingID, getBookingDate, User, getTotalPrice, status);
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
        return booking;
    }

    public int getTotalBookingWithDate(String userID, String startDate, String endDate, String status) throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_BOOKING_WITH_DATE);
                ptm.setString(1, userID);
                ptm.setString(2, "%" + status + "%");
                ptm.setString(3, startDate);
                ptm.setString(4, endDate);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    index = rs.getInt("totalBooking");
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

    public int getTotalBookingAdminWithDate(String startDate, String endDate, String status) throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_BOOKING_ADMIN_WITH_DATE);
                ptm.setString(1, "%" + status + "%");
                ptm.setString(2, startDate);
                ptm.setString(3, endDate);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    index = rs.getInt("totalBooking");
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

    public int getTotalBooking(String userID, String status) throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_LIST_BOOKING);
                ptm.setString(1, userID);
                ptm.setString(2, "%" + status + "%");
                rs = ptm.executeQuery();
                if (rs.next()) {
                    index = rs.getInt("totalBooking");
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

    public int getTotalBookingAdmin(String status) throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_LIST_BOOKING_ADMIN);
                ptm.setString(1, "%" + status + "%");
                rs = ptm.executeQuery();
                if (rs.next()) {
                    index = rs.getInt("totalBooking");
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

    public int getTotalBookingManager(String userID, String status, String search) throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_BOOKING_MANAGER);
                ptm.setString(1, userID);
                ptm.setString(2, "%" + status + "%");
                ptm.setString(3, "%" + search + "%");
                rs = ptm.executeQuery();
                if (rs.next()) {
                    index = rs.getInt("totalBooking");
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

    public int getTotalBookingManagerWithDate(String userID, String startDate, String endDate, String status, String search) throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_BOOKING_MANAGER_WITH_DATE);
                ptm.setString(1, userID);
                ptm.setString(2, "%" + status + "%");
                ptm.setString(3, "%" + search + "%");
                ptm.setString(4, startDate);
                ptm.setString(5, endDate);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    index = rs.getInt("totalBooking");
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

    public boolean deleteBookingByID(String bookingID, String status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_BOOKING_BY_BOOKING_ID);
                status = checkStatus(status);
                ptm.setString(1, status);
                ptm.setString(2, bookingID);
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

    public boolean insertBookingTable(Booking booking) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_BOOKING);
                ptm.setString(1, booking.getBookingId());
                ptm.setString(2, booking.getBookingDate());
                ptm.setString(3, booking.getUser().getUserID());
                ptm.setDouble(4, booking.getTotalPrice());
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

    public boolean updateBookingStatusByID(String bookingID, String bookingStatus) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_BOOKING_STATUS_BY_ID);
                ptm.setString(1, bookingStatus);
                ptm.setString(2, bookingID);
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

    public String checkStatus(String status) {
        if (ON_GOING_STATUS.equals(status)) {
            return CANCELED_STATUS;
        }
        return DELETE_STATUS;
    }

    public String createBookingID() {
        int max = 999999;
        int min = 1;
        int random_double = (int) (Math.random() * (max - min + 1) + min);
        String s = String.valueOf(random_double);
        return "BO" + s;
    }
 
    public boolean checkDuplicate(String bookingID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BOOKING_ID);
                ptm.setString(1, bookingID);
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

    public boolean insertBooking(Booking booking) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_BOOKING);
                ptm.setString(1, booking.getBookingId());
                ptm.setString(2, booking.getBookingDate());
                ptm.setString(3, booking.getUser().getUserID());
                ptm.setDouble(4, booking.getTotalPrice());
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
}
