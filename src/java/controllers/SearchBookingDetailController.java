/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.BookingDetailDAO;
import dto.BookingDetail;
import dto.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchBookingDetailController extends HttpServlet {

    private static final String ADMIN = "AD";
    private static final String USER = "US";

    private static final String SUCCESS_ADMIN = "bookingDetailAdmin.jsp";
    private static final String SUCCESS_USER = "bookingDetailUser.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("LOGIN_USER");
        String roleID = loginUser.getRole().getRoleId();
        String url = ERROR;
        try {
            String bookingID = request.getParameter("bookingID");
            if (ADMIN.equals(roleID)) {
                BookingDetailDAO dao = new BookingDetailDAO();
                BookingDetail getBookingDetail = dao.getBookingDetailByID(bookingID);
                request.setAttribute("BOOKING_DETAIL", getBookingDetail);
                url = SUCCESS_ADMIN;
            } else if (USER.equals(roleID)) {
                BookingDetailDAO dao = new BookingDetailDAO();
                BookingDetail getBookingDetail = dao.getBookingDetailByID(bookingID);
                request.setAttribute("BOOKING_DETAIL", getBookingDetail);
                url = SUCCESS_USER;
            }
        } catch (Exception e) {
            log("Error at SearchController: " + e.toString());
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
