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

    private static final String GET_BOOKING_BY_BOOKING_ID = "SELECT bookingID, bookingDate, userID, totalPrice, status "
            + "FROM tblBooking WHERE bookingID like ? ";
    private static final String DELETE_BOOKING_BY_BOOKING_ID = "UPDATE tblBooking SET status = ? WHERE bookingID like ? ";
    private static final String UPDATE_BOOKING_STATUS_BY_ID = "UPDATE tblBooking SET status = ? WHERE bookingID like ? ";
    
    private static final String COUNT_ALL_BOOKING = "SELECT COUNT(*) as totalBooking FROM tblBooking WHERE userID like ? AND bookingID like ? AND status like ? AND bookingDate between ? and ? ";

    private static final String PAGING_LIST_BOOKING = "SELECT * FROM tblBooking WHERE userID like ? AND status like ? ORDER BY bookingId OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY ";
    private static final String PAGING_LIST_BOOKING_MANAGER = "SELECT * FROM tblBooking WHERE bookingID like ? AND status like ? ORDER BY bookingId OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY ";

    public List<Booking> getListBookingByID(String userID, int[] searchDate, String status, int index) throws SQLException {
        List<Booking> booking = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PAGING_LIST_BOOKING);
                ptm.setString(1, "%" + userID + "%");
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
                    
                    String[] splitDate = getBookingDate.split("[/]");
                    int[] date = new int[3];
                    for (int i = 0; i < splitDate.length; i++) {
                        date[i] = Integer.parseInt(splitDate[i]);
                    }
                    
                    if(date[0] >= searchDate[2] && date[0] <= searchDate[5]){
                        if(date[1] >= searchDate[0] && date[1] <= searchDate[3]){
                            if(date[2] >= searchDate[1] && date[2] <= searchDate[4]){
                                booking.add(new Booking(getBookingID, getBookingDate, user, getTotalPrice, getStatus));
                            }
                        }
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

    public List<Booking> getListBookingByBookingID(String bookingID, String search, String status, int index) throws SQLException {
        List<Booking> booking = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PAGING_LIST_BOOKING_MANAGER);
                ptm.setString(1, "%" + bookingID + "%");
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
                    if (user.getFullName().contains(search) || search == null) {
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

    public int getTotalBooking(String userID, int[] searchDate, String status) throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_BOOKING);
                ptm.setString(1, "%" + userID + "%");
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
}
