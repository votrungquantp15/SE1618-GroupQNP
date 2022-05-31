/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;

/**
 *
 * @author NITRO 5
 */
public class BookingHistoryDTO {

    private String bookingID;
    private Date bookingDate;
    private String fieldName;
    private double price;
    private boolean status;

    public BookingHistoryDTO() {
        this.bookingID = "";
        this.bookingDate = null;
        this.fieldName = "";
        this.price = 0.0;
        this.status = false;
    }

    public BookingHistoryDTO(String bookingID, Date bookingDate, String fieldName, double price, boolean status) {
        this.bookingID = bookingID;
        this.bookingDate = bookingDate;
        this.fieldName = fieldName;
        this.price = price;
        this.status = status;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
