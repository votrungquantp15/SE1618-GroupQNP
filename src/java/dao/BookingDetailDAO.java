/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BookingDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class BookingDetailDAO {

    public List<BookingDetail> bookingHistory(String userID, String search, String address) throws SQLException {
        List<BookingDetail> list = new ArrayList<>();
        Connection connect = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            connect = DBUtils.getConnection();
            if(connect!=null){
                ptm = connect.prepareStatement(SEARCH_BOOKING_HISTORY);
                ptm.setString(1, userID);
                ptm.setString(2,"%" + search + "%");
                ptm.setString(3,"%" + address + "%");
                rs = ptm.executeQuery();
                while(rs.next()){
                    String bookingID = rs.getString("bookingId");
                    String bookingDate = rs.getString("bookingDate");
                    String fieldName = rs.getString("fieldName");
                    double price = rs.getDouble("fieldPrice");
                    list.add(new BookingDetail(bookingID, bookingDate, fieldName, price, true));
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
        return list;
    }

}