/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.BookingDAO;
import dto.Booking;
import dto.User;
import java.io.IOException;
import java.util.ArrayList;
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
public class SearchBookingController extends HttpServlet {

    private static final String ADMIN = "AD";
    private static final String USER = "US";
    private static final String MANAGER = "MA";

    private static final String SUCCESS_ADMIN = "bookingHistoryAdmin.jsp";
    private static final String SUCCESS_USER = "bookingHistoryUser.jsp";
    private static final String SUCCESS_MANAGER = "bookingHistoryFieldOwner.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("LOGIN_USER");
        String roleID = loginUser.getRole().getRoleId();
        String url = ERROR;
        try {
            String datefilter = request.getParameter("datefilter");
            String search = request.getParameter("search");
            String status = request.getParameter("status");
            String indexPage = request.getParameter("index");

            String startDate = "";
            String endDate = "";
            if (datefilter != null && !datefilter.isEmpty()) {
                String[] splitDate = datefilter.replaceAll("\\s", "").split("[-]");
                startDate = splitDate[0];
                endDate = splitDate[1];
            }
            BookingDAO dao = new BookingDAO();
            if (indexPage == null || indexPage.equals("0")) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);

            if (ADMIN.equals(roleID)) {
                List<String> listPlayDate = dao.getDateBookingDetailAdminAndOwner();
                if (listPlayDate.size() > 0 || !listPlayDate.isEmpty()) {
                    dao.autoUpdateBookingPlayedStatus(listPlayDate);
                }
                int count = 0;
                if (datefilter == null || datefilter.isEmpty()) {
                    count = dao.getTotalBookingAdmin(status);
                } else {
                    count = dao.getTotalBookingAdminWithDate(startDate, endDate, status);
                }

                int endPage = count / 10;
                if (endPage == 0) {
                    endPage = 1;
                } else if (count % 10 != 0) {
                    endPage++;
                }
                List<Booking> list = new ArrayList<>();
                if (datefilter == null || datefilter.isEmpty()) {
                    list = dao.getListBookingAdmin(index, status);
                } else {
                    list = dao.getListBookingAdminWithDate(startDate, endDate, status, index);
                }
                request.setAttribute("LIST_BOOKING_HISTORY", list);
                request.setAttribute("END_PAGE", endPage);
                url = SUCCESS_ADMIN;
            } else if (USER.equals(roleID)) {
                String UserID = loginUser.getUserID();
                List<String> listPlayDate = dao.getDateBookingDetailCustomer(UserID);
                if (listPlayDate.size() > 0 || !listPlayDate.isEmpty()) {
                    dao.autoUpdateBookingPlayedStatus(listPlayDate);
                }
                int count = 0;
                if (datefilter == null || datefilter.isEmpty()) {
                    count = dao.getTotalBooking(UserID, status);
                } else {
                    count = dao.getTotalBookingWithDate(UserID, startDate, endDate, status);
                }
                int endPage = count / 10;
                if (endPage == 0) {
                    endPage = 1;
                } else if (count % 10 != 0) {
                    endPage++;
                }

                List<Booking> list = new ArrayList<>();
                if (datefilter == null || datefilter.isEmpty()) {
                    list = dao.getListBooking(UserID, index, status);
                } else {
                    list = dao.getListBookingWithDate(UserID, startDate, endDate, status, index);
                }
                request.setAttribute("LIST_BOOKING_HISTORY", list);
                request.setAttribute("END_PAGE", endPage);
                url = SUCCESS_USER;
            } else if (MANAGER.equals(roleID)) {
                List<String> listPlayDate = dao.getDateBookingDetailAdminAndOwner();
                if (listPlayDate.size() > 0 || !listPlayDate.isEmpty()) {
                    dao.autoUpdateBookingPlayedStatus(listPlayDate);
                }
                int count = 0;
                String userID = loginUser.getUserID();
                if (datefilter == null || datefilter.isEmpty()) {
                    count = dao.getTotalBookingManager(userID, status, search);
                } else {
                    count = dao.getTotalBookingManagerWithDate(userID, startDate, endDate, status, search);
                }

                int endPage = count / 10;
                if (endPage == 0) {
                    endPage = 1;
                } else if (count
                        % 10 != 0) {
                    endPage++;
                }
                List<Booking> listBooking = new ArrayList<>();
                if (datefilter == null || datefilter.isEmpty()) {
                    listBooking = dao.getListBookingManager(userID, status, search, index);
                } else {
                    listBooking = dao.getListBookingManagerWithDate(userID, status, search, startDate, endDate, index);
                }

                request.setAttribute("END_PAGE", endPage);
                request.setAttribute("LIST_BOOKING_HISTORY", listBooking);

                url = SUCCESS_MANAGER;
            }
        } catch (Exception e) {
            log("Error at SearchBookingController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("utf-8");
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
