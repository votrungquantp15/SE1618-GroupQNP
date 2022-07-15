/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.BookingDetailDAO;
import dao.FieldDAO;
import dao.SlotDetailDAO;
import dto.BookingDetail;
import dto.Cart;
import dto.Field;
import dto.SlotDetail;
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
public class CustomerAddCartController extends HttpServlet {

    private static final String ERROR = "CustomerBookingController";
    private static final String SUCCESS = "CustomerBookingController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean checkDB = false;
            boolean checkCart = false;
            String slotDetailID = request.getParameter("slotDetailID");
            String playDate = request.getParameter("playDate");
            String fieldID = request.getParameter("fieldID");
            double fieldPrice = Double.parseDouble(request.getParameter("fieldPrice"));

            FieldDAO fieldDAO = new FieldDAO();
            Field field = fieldDAO.getFieldByID(fieldID);

            SlotDetailDAO slotDetailDAO = new SlotDetailDAO();
            SlotDetail slotDetail = slotDetailDAO.getSlotDetailByID(slotDetailID);

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }
            if (slotDetailID != null) {

                BookingDetailDAO bookingDetailDAO = new BookingDetailDAO();
                String bookingDetailID = bookingDetailDAO.createBookingDetailID();

                while (bookingDetailDAO.checkDuplicate(bookingDetailID)) {
                    bookingDetailID = bookingDetailDAO.createBookingDetailID();
                }

                BookingDetail bookingDetail = new BookingDetail(bookingDetailID, null, field, slotDetail, fieldPrice, playDate, true);

                if (cart.getCart() == null) {
                    List<BookingDetail> existedListDetail = bookingDetailDAO.getListBookingDetailByID(fieldID, playDate, slotDetailID);
                    if (!existedListDetail.isEmpty()) {
                        request.setAttribute("ADD_FAIL", "Đã có người đặt");
                    } else {
                        cart.add(bookingDetail);
                        session.setAttribute("CART", cart);
                        request.setAttribute("ADD_SUCCESS", "Đã thêm " + field.getFieldName() + " vào giỏ hàng");
                        url = SUCCESS;
                    }
                } else {
                    List<BookingDetail> existedListDetail = bookingDetailDAO.getListBookingDetailByID(fieldID, playDate, slotDetailID);

                    if (!existedListDetail.isEmpty()) {
                        request.setAttribute("ADD_FAIL", "Đã có người đặt! Vui lòng chọn ngày hoặc thời gian khác");
                    } else {
                        checkDB = true;
                    }
                    if (checkDB == true) {
                        List<BookingDetail> listBookingCart = new ArrayList<>();
                        List<String> keys = new ArrayList<>();
                        for (String key : cart.getCart().keySet()) {
                            keys.add(key);
                        }
                        for (String key : keys) {
                            listBookingCart.add(cart.getCart().get(key));
                        }
                        for (BookingDetail detail2 : listBookingCart) {
                            if (detail2.getField().getFieldId().equals(fieldID) && detail2.getPlayDate().equals(playDate) && detail2.getSlotDetail().getSlotDetailID().equals(slotDetailID)) {
                                request.setAttribute("ADD_FAIL", "Đã tồn tại trong giỏ hàng");
                                checkCart = false;
                                break;
                            } else {
                                checkCart = true;
                            }
                        }
                    }

                    if (checkDB == true && checkCart == true) {
                        cart.add(bookingDetail);
                        session.setAttribute("CART", cart);
                        request.setAttribute("ADD_SUCCESS", "Đã thêm " + field.getFieldName() + " vào giỏ hàng");
                        url = SUCCESS;
                    }
                }
            } else {
                request.setAttribute("ADD_FAIL", "Vui lòng chọn thời gian chơi");
            }

        } catch (Exception e) {
            log("Error at CustomerAddCartController: " + e.toString());
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
