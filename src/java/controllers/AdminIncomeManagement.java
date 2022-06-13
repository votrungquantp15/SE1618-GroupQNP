/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.BookingDetailDAO;
import dto.BookingDetail;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author votru
 */
public class AdminIncomeManagement extends HttpServlet {

    private static final String ERROR = "error.jsp";

    private static final String SEARCHINCOME = "SearchIncome";

    private static final String GET_ALL_INCOME = "GetAllIncome";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        List<BookingDetail> bookingDetails;
        BookingDetailDAO bookingDetailDao = new BookingDetailDAO();

        try {
            String action = request.getParameter("action");
            switch (action) {
                case SEARCHINCOME:

                    String fieldID = request.getParameter("fieldID");
                    String datefilter = request.getParameter("datefilter");

                        bookingDetails = bookingDetailDao.getListBookingDetailByID(fieldID);
                    
                    request.setAttribute("BOOKING_DETAILS", bookingDetails);
                    url = "incomeReportAdmin.jsp";
                    break;

                case GET_ALL_INCOME:                   
                    url = "incomeReportAdmin.jsp";
                    bookingDetails = bookingDetailDao.getAllBookingDetail();
                    request.setAttribute("BOOKING_DETAILS", bookingDetails);
                    url = "incomeReportAdmin.jsp";
                    break;
            }
        } catch (Exception e) {
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
