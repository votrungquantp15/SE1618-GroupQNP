/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.BookingDAO;
import dao.BookingDetailDAO;
import dao.FieldDAO;
import dao.SlotDetailDAO;
import dto.BookingDetail;
import dto.Field;
import dto.SlotDetail;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author votru
 */
public class UserAddToCart extends HttpServlet {

    private static final String ERROR = "addToCart.jsp";
    private static final String SUCCESS = "addToCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            HttpSession session = request.getSession();

            if (action.equalsIgnoreCase("addToCart")) {
                String id = request.getParameter("id");
                Field field;
                FieldDAO fieldDao = new FieldDAO();
                field = fieldDao.getFieldByID(id);
                HashMap<String, BookingDetail> fieldsCart = (HashMap<String, BookingDetail>) session.getAttribute("FIELDS_CART");
                ArrayList<SlotDetail> slotDetails = (ArrayList<SlotDetail>) session.getAttribute("SLOT_DETAIL");
                SlotDetailDAO slotDetailDao = new SlotDetailDAO();              
                if(slotDetails == null){
                    slotDetails = slotDetailDao.getListSlotFieldByID(id);
                    session.setAttribute("SLOT_DETAIL", slotDetails);
                }
                else{
                    for (SlotDetail slotDetail : slotDetailDao.getListSlotFieldByID(id)) {
                        slotDetails.add(slotDetail);
                        session.setAttribute("SLOT_DETAIL", slotDetails);
                    }
                }

                if (fieldsCart == null) {
                    fieldsCart = new HashMap<>();
                }

                BookingDetailDAO bookingDetailDao = new BookingDetailDAO();
                BookingDAO bookingDao = new BookingDAO();

                BookingDetail bookingDetail;
                String bookingDetailID = bookingDetailDao.createBookingDetailID();
                String bookingID = bookingDao.createBookingID();
                Double fieldPrice = fieldDao.getFieldByID(id).getPrice();

                while (bookingDao.checkDuplicate(bookingID)) {
                    bookingID = bookingDao.createBookingID();
                }

                while (bookingDetailDao.checkDuplicate(bookingDetailID)) {
                    bookingDetailID = bookingDetailDao.createBookingDetailID();
                }
                bookingDetail = new BookingDetail(bookingDetailID, null, fieldDao.getFieldByID(id), null, fieldPrice, null, 0, 0, null, true);

                fieldsCart.put(bookingDetail.getField().getFieldId(), bookingDetail);
                session.setAttribute("FIELDS_CART", fieldsCart);

            } else if (action.equals("bookingField")) {
                String[] slotsID = request.getParameterValues("slotID");
                String playDate = request.getParameter("playDate");
                String fieldID = request.getParameter("fieldID");
                HashMap<String, BookingDetail> fieldsCart = (HashMap<String, BookingDetail>) session.getAttribute("FIELDS_CART");
                if (fieldsCart == null) {
                    fieldsCart = new HashMap<>();
                    for (String slotID : slotsID) {
                        BookingDetailDAO bookingDetailDao = new BookingDetailDAO();
                        BookingDAO bookingDao = new BookingDAO();
                        SlotDetailDAO slotDetailDao = new SlotDetailDAO();
                        FieldDAO fieldDao = new FieldDAO();

                        BookingDetail bookingDetail;
                        String bookingDetailID = bookingDetailDao.createBookingDetailID();
                        String bookingID = bookingDao.createBookingID();
                        Double fieldPrice = fieldDao.getFieldByID(fieldID).getPrice();
                        String foodDetailID = null;
                        Double foodPrice = null;
                        String foodQuantity = null;
//                        Booking booking = bookingDao.

                        while (bookingDao.checkDuplicate(bookingID)) {
                            bookingID = bookingDao.createBookingID();
                        }

                        while (bookingDetailDao.checkDuplicate(bookingDetailID)) {
                            bookingDetailID = bookingDetailDao.createBookingDetailID();
                        }

                        bookingDetail = new BookingDetail(bookingDetailID, null, fieldDao.getFieldByID(fieldID), slotDetailDao.getSlotDetailByID(slotID), fieldPrice, null, 0, 0, playDate, true);

                        fieldsCart.put(bookingDetail.getField().getFieldId(), bookingDetail);

                        session.setAttribute("FIELDS_CART", fieldsCart);
                    }

                }

            }
            else if(action.equals("remove")){
                session.removeAttribute("FIELDS_CART");
            }

//            SlotDetailDAO slotDetailDao = new SlotDetailDAO();
//            ArrayList<String> slotIDs = slotDetailDao.getListSlotFieldByID(id);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at UserPrintFieldDetailController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy

        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getCurrentTimeStamp());
//        ArrayList<SlotDetail> slotDetails;
//        SlotDetailDAO slotDetailDao = new SlotDetailDAO();
//
//        slotDetails = slotDetailDao.getListSlotFieldByID("FI3");
//        System.out.println(slotDetails);
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
