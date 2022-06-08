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

    private static final String GET_LIST_BOOKING = "SELECT bookingID, bookingDate, userID, totalPrice, status "
            + "FROM tblBooking WHERE userID like ? AND bookingID like ? AND status like ? ";
    private static final String GET_BOOKING_BY_BOOKING_ID = "SELECT bookingID, bookingDate, userID, totalPrice, status "
            + "FROM tblBooking WHERE bookingID like ? ";
    private static final String DELETE_BOOKING_BY_BOOKING_ID = "UPDATE tblBooking SET status = ? WHERE bookingID like ? ";

    public List<Booking> getListBookingByID(String userID, String search, String status) throws SQLException {
        List<Booking> booking = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_BOOKING);
                ptm.setString(1, "%" + userID + "%");
                ptm.setString(2, "%" + search + "%");
                ptm.setString(3,"%" + status + "%");
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
  
    public String checkStatus(String status) {
        if (ON_GOING_STATUS.equals(status)) {
            return CANCELED_STATUS;
        }
        return DELETE_STATUS;
    }
}
