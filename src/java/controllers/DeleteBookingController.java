/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.BookingDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NITRO 5
 */
public class DeleteBookingController extends HttpServlet {

    private static final String PLAYED = "Played";
    private static final String CANCELED = "Canceled";
    private static final String DELETE = "Delete";

    private static final String SUCCESS = "SearchBookingController";
    private static final String ERROR = "SearchBookingController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String bookingID = request.getParameter("bookingID");
        String bookingStatus = request.getParameter("bookingStatus");
        try {
            if (!DELETE.equals(bookingStatus)) {
                BookingDAO bookingDAO = new BookingDAO();
                boolean check = bookingDAO.deleteBookingByID(bookingID, bookingStatus);
                if (check == true) {
                    if (PLAYED.equals(bookingStatus) || CANCELED.equals(bookingStatus)) {
                        request.setAttribute("DELETE_SUCCESS", "Delete Booking " + bookingID + " Successfully");
                    } else {
                        request.setAttribute("DELETE_SUCCESS", "Cancel Booking " + bookingID + " Successfully");
                    }
                    url = SUCCESS;
                } else {
                    if (PLAYED.equals(bookingStatus) || CANCELED.equals(bookingStatus)) {
                        request.setAttribute("DELETE_UNSUCCESS", "Delete Booking " + bookingID + " Failed");
                    } else {
                        request.setAttribute("DELETE_UNSUCCESS", "Cancel Booking " + bookingID + " Failed");
                    }
                }
            } else {
                request.setAttribute("DELETE_UNSUCCESS", "Can't delete a booking with status Delete");
            }
        } catch (Exception e) {
            log("Error at DeleteBookingController: " + e.toString());
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
