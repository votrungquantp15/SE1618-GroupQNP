/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.BookingDAO;
import dao.BookingDetailDAO;
import dto.Booking;
import dto.BookingDetail;
import dto.Cart;
import dto.User;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author votru
 */
public class UserPaymentController extends HttpServlet {

    private static final String SUCCESS = "PrintFieldController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String url = SUCCESS;

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("CART");
        BookingDAO bookingDAO = new BookingDAO();
        ArrayList<BookingDetail> listBookingDetaillCart = new ArrayList<>();
        BookingDetailDAO bookingDetailDAO = new BookingDetailDAO();
        ArrayList<String> keys = new ArrayList<>();
        boolean check = true;
        for (String key : cart.getCart().keySet()) {
            keys.add(key);
        }
        for (String key : keys) {
            listBookingDetaillCart.add(cart.getCart().get(key));
        }
        
        String newBookingID = bookingDAO.createBookingID();
            while (bookingDAO.checkDuplicate(newBookingID)) {
                newBookingID = bookingDAO.createBookingID();
            }
        double total = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String now =dateFormat.format(date);
        
        User user = (User) session.getAttribute("LOGIN_USER");
        for (BookingDetail bookingDetail : listBookingDetaillCart) {
            
            total += bookingDetail.getFieldPrice();
        }
        Booking booking = new Booking(newBookingID, now, user, total, "");
        check = bookingDAO.insertBooking(booking);
        if(check){
            for (BookingDetail bookingDetail : listBookingDetaillCart) {
                bookingDetail.setBooking(booking);
                check = bookingDetailDAO.insertBookingDetail(bookingDetail);
            }
        }
        if(check){
            session.removeAttribute("CART");
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
