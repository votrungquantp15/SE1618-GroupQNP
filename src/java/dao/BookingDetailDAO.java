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
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class BookingDetailDAO {

    private static final String PLAYED = "Played";
    private static final String CANCELED = "Canceled";
    private static final String DELETE = "Delete";
    private static final String ON_GOING = "On-Going";

    private static final String GET_BOOKING_DETAIL = "SELECT bookingDetailID, bookingID, fieldID, slotDetailID, fieldPrice, foodDetailID, foodPrice, foodQuantity, playDate, status "
            + "FROM tblBookingDetail WHERE bookingID like ?  ";
     private static final String GET_LIST_BOOKING_DETAIL_BY_ID = "SELECT bookingDetailID, bookingID, fieldID, slotDetailID, fieldPrice, foodDetailID, foodPrice, foodQuantity, playDate, status "
            + "FROM tblBookingDetail WHERE fieldID like  ? ";

    private static final String GET_ALL_BOOKING_DETAIL = "SELECT bookingDetailID, bookingID, fieldID, playDate, slotDetailID, fieldPrice, foodDetailID, foodPrice, foodQuantity, status "
            + "FROM tblBookingDetail";

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

                    String foodDetailID = rs.getString("foodDetailID");
                    FoodDetailDAO foodDetailDAO = new FoodDetailDAO();
                    FoodDetail foodDetail = foodDetailDAO.getFoodDetailByID(foodDetailID);

                    double foodTotalPrice = rs.getDouble("foodPrice");
                    int foodTotalQuantity = rs.getInt("foodQuantity");
                    String playDate = rs.getString("playDate");
                    boolean status = rs.getBoolean("status");
                    bookingDetail = new BookingDetail(bookingDetailID, booking, field, slotDetail, fieldPrice, foodDetail, foodTotalPrice, foodTotalQuantity, playDate, status);
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
        
//        String timeEnd = bookingDetail.getSlotDetail().getSlot().getTimeEnd();
//        String time[] = timeEnd.split(":");
//        int timeEndHour = Integer.parseInt(time[0]);
        if (ON_GOING.equals(bookingStatus)) {
            if (currentDate.isBefore(playDate)) {
                check = true;
            }
        } else if (PLAYED.equals(bookingStatus)) {
            if (currentDate.isAfter(playDate)) {
                check = true;
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
                    
                    double foodTotalPrice;
                    int foodTotalQuantity;
                    FoodDetail foodDetail = null;
                    String foodDetailID = rs.getString("foodDetailID");
                    if(foodDetailID != null){
                        FoodDetailDAO foodDetailDAO = new FoodDetailDAO();
                        foodDetail = foodDetailDAO.getFoodDetailByID(foodDetailID);
                        foodTotalPrice = rs.getDouble("foodPrice");
                        foodTotalQuantity = rs.getInt("foodQuantity");
                    }
                    else{
                        foodTotalPrice = 0;
                        foodTotalQuantity = 0;
                    }
                   

                    String playDate = rs.getString("playDate");
                    boolean status = rs.getBoolean("status");

                    bookingDetails.add(new BookingDetail(bookingDetailID, booking, field, slotDetail, fieldPrice, foodDetail, foodTotalPrice, foodTotalQuantity, playDate, status));
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
    
    public List<BookingDetail> getListBookingDetailByID(String id) throws SQLException {
        List<BookingDetail> bookingDetails = new ArrayList<>();
        Connection connect = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            connect = DBUtils.getConnection();
            if (connect != null) {
                ptm = connect.prepareStatement(GET_LIST_BOOKING_DETAIL_BY_ID);
                ptm.setString(1,"%" + id + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
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

                    String foodDetailID = rs.getString("foodDetailID");
                    FoodDetailDAO foodDetailDAO = new FoodDetailDAO();
                    FoodDetail foodDetail = foodDetailDAO.getFoodDetailByID(foodDetailID);

                    double foodTotalPrice = rs.getDouble("foodPrice");
                    int foodTotalQuantity = rs.getInt("foodQuantity");
                    String playDate = rs.getString("playDate");
                    boolean status = rs.getBoolean("status");
                    bookingDetails.add(new BookingDetail(bookingDetailID, booking, field, slotDetail, fieldPrice, foodDetail, foodTotalPrice, foodTotalQuantity, playDate, status));
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
}
