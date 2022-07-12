/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Booking;
import dto.BookingDetail;
import dto.Field;
import dto.FoodDetail;
import dto.SlotDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class BookingDetailDAO {

    private static final String PLAYED = "Played";
    private static final String CANCELED = "Canceled";
    private static final String DELETE = "Delete";
    private static final String ON_GOING = "On-Going";

    private static final String GET_BOOKING_DETAIL = "SELECT bookingDetailID, bookingID, fieldID, slotDetailID, fieldPrice, playDate, status "
            + "FROM tblBookingDetail WHERE bookingID like ?  ";
    private static final String GET_LIST_BOOKING_DETAIL_BY_FIELD_ID = "SELECT bookingDetailID, bookingID, fieldID, slotDetailID, fieldPrice, playDate, status "
            + "FROM tblBookingDetail WHERE fieldID like  ? ";

    private static final String GET_ALL_BOOKING_DETAIL = "SELECT bookingDetailID, bookingID, fieldID, playDate, slotDetailID, fieldPrice, status "
            + "FROM tblBookingDetail";
    private static final String GET_BOOKING_DETAIL_ID = "SELECT bookingDetailI tblBookingDetail WHERE bookingDetailID like ?  ";

    public BookingDetail getBookingDetailByID(String bookingID) throws SQLException {
        BookingDetail bookingDetail = null;
        Connection connect = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            connect = DBUtils.getConnection();
            if (connect != null) {
                ptm = connect.prepareStatement(GET_BOOKING_DETAIL);
                ptm.setString(1, bookingID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String bookingDetailID = rs.getString("bookingDetailID");

                    String getBookingID = rs.getString("bookingId");
                    BookingDAO bookingDAO = new BookingDAO();
                    Booking booking = bookingDAO.getBookingByID(getBookingID);

                    String fieldID = rs.getString("fieldID");
                    FieldDAO fieldDAO = new FieldDAO();
                    Field field = fieldDAO.getFieldByID(fieldID);

                    double fieldPrice = rs.getDouble("fieldPrice");

                    String slotDetailID = rs.getString("slotDetailID");
                    SlotDetailDAO slotDetailDAO = new SlotDetailDAO();
                    SlotDetail slotDetail = slotDetailDAO.getSlotDetailByID(slotDetailID);

                    String playDate = rs.getString("playDate");
                    boolean status = rs.getBoolean("status");
                    bookingDetail = new BookingDetail(bookingDetailID, booking, field, slotDetail, fieldPrice, playDate, status);
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
            if (connect != null) {
                connect.close();
            }
        }
        return bookingDetail;
    }

    public boolean checkValidDate(String bookingStatus, BookingDetail bookingDetail) throws SQLException, ParseException {
        boolean check = false;

        LocalDate currentDate = LocalDateTime.now().toLocalDate();

        String getPlayDate = bookingDetail.getPlayDate();
        LocalDate playDate = LocalDate.parse(getPlayDate);
        int localHour = LocalDateTime.now().getHour();
        if (localHour == 0) {
            localHour = 24;
        }

        String timeEnd = bookingDetail.getSlotDetail().getSlot().getTimeEnd();
        String time[] = timeEnd.split(":");
        int timeEndHour = Integer.parseInt(time[0]);
        if (timeEndHour == 0) {
            timeEndHour = 24;
        }

        if (ON_GOING.equals(bookingStatus)) {
            if (currentDate.isBefore(playDate) || currentDate.isEqual(playDate)) {
                if (localHour < timeEndHour) {
                    check = true;
                }
            }
        } else if (PLAYED.equals(bookingStatus)) {
            if (currentDate.isAfter(playDate) || currentDate.isEqual(playDate)) {
                if (localHour > timeEndHour) {
                    check = true;
                }
            }
        } else if (CANCELED.equals(bookingStatus)
                || DELETE.equals(bookingStatus)) {
            check = true;
        }
        return check;
    }

    public List<BookingDetail> getAllBookingDetail() throws SQLException {
        List<BookingDetail> bookingDetails = new ArrayList<>();
        Connection connect = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            connect = DBUtils.getConnection();
            if (connect != null) {
                ptm = connect.prepareStatement(GET_ALL_BOOKING_DETAIL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookingDetailID = rs.getString("bookingDetailID");

                    String getBookingID = rs.getString("bookingID");
                    BookingDAO bookingDAO = new BookingDAO();
                    Booking booking = bookingDAO.getBookingByID(getBookingID);

                    String fieldID = rs.getString("fieldID");
                    FieldDAO fieldDAO = new FieldDAO();
                    Field field = fieldDAO.getFieldByID(fieldID);

                    double fieldPrice = rs.getDouble("fieldPrice");

                    String slotDetailID = rs.getString("slotDetailID");
                    SlotDetailDAO slotDetailDAO = new SlotDetailDAO();
                    SlotDetail slotDetail = slotDetailDAO.getSlotDetailByID(slotDetailID);

                    String playDate = rs.getString("playDate");
                    boolean status = rs.getBoolean("status");

                    bookingDetails.add(new BookingDetail(bookingDetailID, booking, field, slotDetail, fieldPrice, playDate, status));
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
            if (connect != null) {
                connect.close();
            }
        }

        return bookingDetails;
    }

    public List<BookingDetail> getListBookingDetailByID(String fieldID) throws SQLException {
        List<BookingDetail> bookingDetails = new ArrayList<>();
        Connection connect = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            connect = DBUtils.getConnection();
            if (connect != null) {
                ptm = connect.prepareStatement(GET_LIST_BOOKING_DETAIL_BY_FIELD_ID);
                ptm.setString(1, "%" + fieldID + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookingDetailID = rs.getString("bookingDetailID");

                    String getBookingID = rs.getString("bookingId");
                    BookingDAO bookingDAO = new BookingDAO();
                    Booking booking = bookingDAO.getBookingByID(getBookingID);

                    String getFieldID = rs.getString("fieldID");
                    FieldDAO fieldDAO = new FieldDAO();
                    Field field = fieldDAO.getFieldByID(getFieldID);

                    double fieldPrice = rs.getDouble("fieldPrice");

                    String slotDetailID = rs.getString("slotDetailID");
                    SlotDetailDAO slotDetailDAO = new SlotDetailDAO();
                    SlotDetail slotDetail = slotDetailDAO.getSlotDetailByID(slotDetailID);

                    String playDate = rs.getString("playDate");
                    boolean status = rs.getBoolean("status");
                    bookingDetails.add(new BookingDetail(bookingDetailID, booking, field, slotDetail, fieldPrice, playDate, status));
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
            if (connect != null) {
                connect.close();
            }
        }
        return bookingDetails;

    }
    public String createBookingDetailID(){
        int max = 999999;
        int min = 1;
        int random_double = (int) (Math.random() * (max - min + 1) + min);
        String s = String.valueOf(random_double);
        
        return "BD" + s;
    }
    public boolean checkDuplicate(String bookingDetailID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BOOKING_DETAIL_ID);
                ptm.setString(1, bookingDetailID);
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
