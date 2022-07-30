/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.BookingDetailDAO;
import dao.SlotDetailDAO;
import dto.BookingDetail;
import dto.Cart;
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
public class CustomerEditCartItemController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String bookingDetailID = request.getParameter("bookingDetailID");
            String playDate = request.getParameter("playDate");
            String slotDetailID = request.getParameter("slotDetailID");
            boolean checkDB = false;
            boolean checkCart = false;
            HttpSession session = request.getSession();
            if (session != null) {
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    if (cart.getCart().containsKey(bookingDetailID)) {
                        BookingDetail bookingDetail = cart.getCart().get(bookingDetailID);
                        String fieldID = bookingDetail.getField().getFieldId();

                        BookingDetailDAO bookingDetailDAO = new BookingDetailDAO();

                        List<BookingDetail> existedListDetail = bookingDetailDAO.getListBookingDetailByID(fieldID, playDate, slotDetailID);
                        if (!existedListDetail.isEmpty()) {
                            request.setAttribute("EDIT_FAIL", "Đã có người đặt! Vui lòng chọn ngày hoặc thời gian khác");
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
                                    request.setAttribute("EDIT_FAIL", "Đã tồn tại trong giỏ! Vui lòng chọn ngày hoặc thời gian khác");
                                    checkCart = false;
                                    break;
                                } else {
                                    checkCart = true;
                                }
                            }
                        }
                        if (checkDB == true && checkCart == true) {
                            String fieldName = bookingDetail.getField().getFieldName();
                            SlotDetailDAO slotDetailDAO = new SlotDetailDAO();
                            if (slotDetailID == null || slotDetailID.isEmpty()) {
                                slotDetailID = cart.getCart().get(bookingDetailID).getSlotDetail().getSlotDetailID();
                            }
                            SlotDetail slotDetail = slotDetailDAO.getSlotDetailByID(slotDetailID);

                            bookingDetail.setSlotDetail(slotDetail);
                            bookingDetail.setPlayDate(playDate);
                            cart.edit(bookingDetailID, bookingDetail);
                            request.setAttribute("EDIT_SUCCESS", "Cập nhật " + fieldName + " trong giỏ hàng thành công");
                            request.setAttribute("CART", cart);
                            url = SUCCESS;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error at CustomerEditCartItemController: " + e.toString());
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
