/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Booking;
import dto.BookingDetail;
import dto.Field;
import dto.Food;
import dto.FoodDetail;
import dto.Slot;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class BookingDetailDAO {
    
    private static final String GET_BOOKING_DETAIL = "SELECT bookingID, fieldID, slotID, fieldPrice, foodID, foodDetailID, foodPrice, foodQuantity, playDate, status "
            + "FROM tblBookingDetail WHERE bookingID like ? ";
    
    public BookingDetail getBookingDetailByID(String bookingID) throws SQLException {
        BookingDetail bookingDetail = null;
        Connection connect = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            connect = DBUtils.getConnection();
            if(connect!=null){
                ptm = connect.prepareStatement(GET_BOOKING_DETAIL);
                ptm.setString(1, bookingID);
                rs = ptm.executeQuery();
                if(rs.next()){
                    String getBookingID = rs.getString("bookingId");
                    BookingDAO bookingDAO = new BookingDAO();
                    Booking booking = bookingDAO.getBookingByID(getBookingID);
                    
                    String fieldID = rs.getString("fieldID");
                    FieldDAO fieldDAO = new FieldDAO();
                    Field field = fieldDAO.getFieldByID(fieldID);
                    
                    double fieldPrice = rs.getDouble("fieldPrice");
                    
                    String slotID = rs.getString("slotID");
                    SlotDAO slotDAO = new SlotDAO();
                    Slot slot = slotDAO.getSlotByID(slotID);

                    String foodID = rs.getString("foodID");
                    FoodDAO foodDAO = new FoodDAO();
                    Food food = foodDAO.getFoodByID(foodID);
                    
                    String foodDetailID = rs.getString("foodDetailID");
                    FoodDetailDAO foodDetailDAO = new FoodDetailDAO();
                    FoodDetail foodDetail = foodDetailDAO.getFoodDetailByID(foodDetailID);
                    
                    double foodTotalPrice = rs.getDouble("foodPrice");
                    int foodTotalQuantity = rs.getInt("foodQuantity");
                    String playDate = rs.getString("playDate");
                    
                    boolean status = rs.getBoolean("status");
                    bookingDetail = new BookingDetail(booking, field, slot, fieldPrice, food, foodDetail, foodTotalPrice, foodTotalQuantity, playDate, status);
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

}
