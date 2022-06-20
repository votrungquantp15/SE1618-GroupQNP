/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.SlotDAO;
import dto.Slot;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NITRO 5
 */
public class DeleteSlotController extends HttpServlet {

    private static final String SUCCESS = "SearchSlotController";
    private static final String ERROR = "SearchSlotController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String slotID = request.getParameter("slotID");
            SlotDAO slotDAO = new SlotDAO();
            Slot slot = slotDAO.getSlotByID(slotID);
            String status = request.getParameter("status");
            if ("True".equals(status)) {
                boolean check = slotDAO.deleteSlotByID(slot.getSlotId(), status);
                if (check == true) {
                    request.setAttribute("DELETE_SUCCESS", "Delete " + slotID + " Successfully");
                    url = SUCCESS;
                } else {
                    request.setAttribute("DELETE_UNSUCCESS", "Delete " + slotID + " Failed");
                }
            } else {
                request.setAttribute("DELETE_UNSUCCESS", slot.getSlotId() + " Already Has Status: " + status );
            }
        } catch (Exception e) {
            log("Error at DeleteSlotController: " + e.toString());
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
