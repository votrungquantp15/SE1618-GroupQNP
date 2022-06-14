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
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NITRO 5
 */
public class UpdateBookingController extends HttpServlet {

    private static final String SUCCESS = "SearchBookingController";
    private static final String ERROR = "SearchBookingController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String bookingID = request.getParameter("bookingID");
        String bookingStatus = request.getParameter("bookingStatus");

        try {
            BookingDAO bookingDAO = new BookingDAO();
            Booking booking = bookingDAO.getBookingByID(bookingID);

            if (!booking.getStatus().equals(bookingStatus)) {
                BookingDetailDAO bookingDetailDAO = new BookingDetailDAO();
                BookingDetail bookingDetail = bookingDetailDAO.getBookingDetailByID(booking.getBookingId());

                boolean valid = bookingDetailDAO.checkValidDate(bookingStatus, bookingDetail);
                if (valid == true) {
                    boolean check = bookingDAO.updateBookingStatusByID(booking.getBookingId(), bookingStatus);
                    if (check == true) {
                        request.setAttribute("UPDATE_SUCCESS", "Update Booking " + booking.getBookingId() + " Status from " + booking.getStatus() + " to " + bookingStatus + " Successfully");
                        url = SUCCESS;
                    } else {
                        request.setAttribute("UPDATE_UNSUCCESS", "Update Booking " + booking.getBookingId() + " Status from " + booking.getStatus() + " to " + bookingStatus + " Failed");
                    }
                } else {
                    request.setAttribute("UPDATE_UNSUCCESS", "Booking " + booking.getBookingId() + " Status " + bookingStatus + " is not valid");
                }
            } else {
                request.setAttribute("UPDATE_UNSUCCESS", "Booking " + booking.getBookingId() + " Status was already " + booking.getStatus());
            }
        } catch (Exception e) {
            log("Error at SearchBookingController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
