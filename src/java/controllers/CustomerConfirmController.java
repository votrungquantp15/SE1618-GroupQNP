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
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NITRO 5
 */
public class CustomerConfirmController extends HttpServlet {
    
    private static final String STATUS = "On-Going";
    private static final String ERROR = "checkOut.jsp";
    private static final String SUCCESS = "checkoutSuccess.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean checkDB = false;
            double totalPrice = Double.parseDouble(request.getParameter("total"));
            HttpSession session = request.getSession();
            User loginUser = (User) session.getAttribute("LOGIN_USER");
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null && loginUser != null) {
                BookingDetailDAO bookingDetailDAO = new BookingDetailDAO();
                
                for (BookingDetail detail : cart.getCart().values()) {
                    checkDB = false;
                    String fieldID = detail.getField().getFieldId();
                    String fieldName = detail.getField().getFieldName();
                    String playDate = detail.getPlayDate();
                    String timeStart = detail.getSlotDetail().getSlot().getTimeStart();
                    String timeEnd = detail.getSlotDetail().getSlot().getTimeEnd();
                    String slotDetailID = detail.getSlotDetail().getSlotDetailID();
                    List<BookingDetail> existedListDetail = bookingDetailDAO.getListBookingDetailByID(fieldID, playDate, slotDetailID);
                    if (!existedListDetail.isEmpty()) {
                        request.setAttribute("ADD_FAIL", fieldName + "\nThời gian: " + timeStart + " - " + timeEnd + " đã có người đặt");
                        break;
                    } else {
                        checkDB = true;
                    }
                }
                if (checkDB) {
                    //Insert Booking Table
                    Date todayDate = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(todayDate.getTime());
                    
                    BookingDAO bookingDAO = new BookingDAO();
                    String bookingID = bookingDAO.createBookingID();
                    
                    while (bookingDAO.checkDuplicate(bookingID)) {
                        bookingID = bookingDAO.createBookingID();
                    }
                    Booking booking = new Booking(bookingID, sqlDate.toString(), loginUser, totalPrice, STATUS);
                    boolean checkBooking = bookingDAO.insertBookingTable(booking);
                    if (checkBooking) {
                        //Insert BookingDetail Table
                        boolean checkDetail = false;
                        
                        for (BookingDetail bookingDetail : cart.getCart().values()) {
                            bookingDetail.setBooking(booking);
                            checkDetail = bookingDetailDAO.insertBookingDetailTable(bookingDetail);
                        }
                        if (checkDetail) {
                            url = SUCCESS;
                            session.removeAttribute("CART");
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error at CustomerConfirmController: " + e.toString());
        } finally {
            if (url.equals(ERROR)) {
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendRedirect(url);
            }
            
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
