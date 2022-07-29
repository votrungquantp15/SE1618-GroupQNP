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
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            String action = request.getParameter("action");
            switch (action) {
                case SEARCHINCOME:

                    String fieldID = request.getParameter("fieldID");
                    String datefilter = request.getParameter("datefilter");

                    bookingDetails = bookingDetailDao.getListBookingDetailByFieldID(fieldID);

                    request.setAttribute("BOOKING_DETAILS", bookingDetails);
                    url = "incomeReportAdmin.jsp";
                    break;

                case GET_ALL_INCOME:
                    String indexPage = request.getParameter("index");
                    if (indexPage == null) {
                        indexPage = "1";
                    }
                    int index = Integer.parseInt(indexPage);
                    int endPage = 0;
                    int count = bookingDetailDao.countTotalBookingDetail();
                    bookingDetails = bookingDetailDao.getAllBookingDetailPaging(index);
                    request.setAttribute("BOOKING_DETAILS", bookingDetails);
                    if (user.getRole().getRoleId().equals("MA")) {
                        url = "incomeReportOwner.jsp";
                    } else if (user.getRole().getRoleId().equals("AD")) {
                        url = "incomeReportAdmin.jsp";
                    }
                    endPage = count / 10;
                    if (count % 10 != 0) {
                        endPage++;
                    }
                    request.setAttribute("END_PAGE", endPage);
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
