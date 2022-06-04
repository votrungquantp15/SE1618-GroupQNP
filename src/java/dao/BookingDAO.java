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

/**
 *
 * @author NITRO 5
 */
public class BookingDAO {
    private static final String GET_BOOKING = "SELECT bookingID, bookingDate, userID, totalPrice, status "
            + "FROM tblBooking WHERE userID like ? ";
    private static final String GET_BOOKING_BY_ID = "SELECT bookingID, bookingDate, userID, totalPrice, status "
            + "FROM tblBooking WHERE bookingID like ? ";
    
    public List<Booking> getListBookingByID(String userID) throws SQLException{
        List<Booking> booking = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BOOKING);
                ptm.setString(1,"%" + userID + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getBookingID = rs.getString("bookingID");
                    String getBookingDate = rs.getString("bookingDate");       
                    String getUserID = rs.getString("userID");
                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.getUserByID(getUserID);
                    double getTotalPrice = rs.getDouble("totalPrice");
                    String status = rs.getString("status");
                    booking.add(new Booking(getBookingID, getBookingDate, user, getTotalPrice, status));
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
    
    public Booking getBookingByID(String bookingID) throws SQLException{
        Booking booking = new Booking();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BOOKING_BY_ID);
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
}
