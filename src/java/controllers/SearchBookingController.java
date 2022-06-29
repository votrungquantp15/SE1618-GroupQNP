/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.BookingDAO;
import dao.BookingDetailDAO;
import dao.FieldDAO;
import dto.Booking;
import dto.BookingDetail;
import dto.Field;
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
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);

            if (ADMIN.equals(roleID)) {
                String UserID = "U";

                int count = 0;
                if (datefilter == null) {
                    count = dao.getTotalListBooking(UserID);
                } else {
                    count = dao.getTotalBooking(UserID, startDate, endDate, status);
                }

                int endPage = count / 10;
                if (endPage == 0) {
                    endPage = 1;
                }
                if (count % 10 != 0) {
                    endPage++;
                }
                List<Booking> list = new ArrayList<>();
                if (datefilter == null) {
                    list = dao.getListBooking(UserID, index);
                } else {
                    list = dao.getListBookingByID(UserID, startDate, endDate, status, index);
                }
                request.setAttribute("LIST_BOOKING_HISTORY", list);
                request.setAttribute("END_PAGE", endPage);
                url = SUCCESS_ADMIN;
            } else if (USER.equals(roleID)) {
                String UserID = loginUser.getUserID();
                int count = 0;
                if (datefilter == null) {
                    count = dao.getTotalListBooking(UserID);
                } else {
                    count = dao.getTotalBooking(UserID, startDate, endDate, status);
                }
                int endPage = count / 10;
                if (endPage == 0) {
                    endPage = 1;
                }
                if (count % 10 != 0) {
                    endPage++;
                }

                List<Booking> list = new ArrayList<>();
                if (datefilter == null) {
                    list = dao.getListBooking(UserID, index);
                } else {
                    list = dao.getListBookingByID(UserID, startDate, endDate, status, index);
                }
                request.setAttribute("LIST_BOOKING_HISTORY", list);
                request.setAttribute("END_PAGE", endPage);
                url = SUCCESS_USER;
            } else if (MANAGER.equals(roleID)) {
                int count = 0;
                String UserID = loginUser.getUserID();
                FieldDAO fieldDAO = new FieldDAO();
                List<Field> listField = fieldDAO.getListFieldByUserID(UserID);

                BookingDetailDAO bookingDetailDAO = new BookingDetailDAO();
                List<Booking> list = new ArrayList<>();
                if (!listField.isEmpty()) {
                    if (listField.size() > 0) {
                        for (Field field : listField) {
                            List<BookingDetail> listBookingDetail = bookingDetailDAO.getListBookingDetailByID(field.getFieldId());
                            if (!listBookingDetail.isEmpty()) {
                                if (listBookingDetail.size() > 0) {
                                    for (BookingDetail bookingDetail : listBookingDetail) {
                                        List<Booking> listBooking = new ArrayList<>();
                                        if (datefilter == null) {
                                            listBooking = dao.getListBookingManager(bookingDetail.getBooking().getBookingId(), index);
                                        } else {
                                            listBooking = dao.getListBookingByBookingID(bookingDetail.getBooking().getBookingId(), search, startDate, endDate, status, index);
                                        }
                                        if (!listBooking.isEmpty()) {
                                            if (listBooking.size() > 0) {
                                                for (Booking booking : listBooking) {
                                                    count++;
                                                    list.add(booking);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                int endPage = count / 10;
                if (endPage == 0) {
                    endPage = 1;
                } else if(count % 10 != 0){
                    endPage++;
                }
                request.setAttribute("END_PAGE", endPage);
                request.setAttribute("LIST_BOOKING_HISTORY", list);

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
