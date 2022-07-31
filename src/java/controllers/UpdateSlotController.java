package controllers;

import dao.SlotDAO;
import dao.SlotDetailDAO;
import dto.Slot;
import dto.SlotDetail;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateSlotController extends HttpServlet {

    private static final String ERROR = "PrintSlotController";
    private static final String SUCCESS = "PrintSlotController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            SlotDAO slotDao = new SlotDAO();
            SlotDetailDAO slotDetailDao = new SlotDetailDAO();
            boolean checkValidation = true;
            String id_slot = request.getParameter("id_slot");
            String counter = request.getParameter("counter");
            String fieldId = request.getParameter("fieldId");
            request.setAttribute("COUNTER", counter);
            String status = request.getParameter("status");
            String statusAfter = slotDao.changeStringStatus(status);
            SlotDetail slotdetail = slotDetailDao.getSlotDetailBySlotIdAndFieldId(id_slot, fieldId);
            String statusOfSlot = slotdetail.getStatus();
            if (!statusOfSlot.equals(statusAfter)) {
                boolean checkExist = slotDao.checkCanUpdateSlot(id_slot);
                if (checkExist) {
                    request.setAttribute("UPDATE_UNSUCCESS", "This slot being booked cannot be changed status!");
                    request.setAttribute("SHOW_MODAL", "Update");
                    checkValidation = false;
                }
                if (checkValidation) {
                    boolean checkUpdate = slotDao.updateStatusSlot(id_slot, fieldId, statusAfter);
                    if (checkUpdate) {
                        url = SUCCESS;
                        request.setAttribute("UPDATE_SUCCESS", "Update slot success!");
                    } else {
                        request.setAttribute("UPDATE_UNSUCCESS", "Update slot unsuccess! Please try again!");
                        request.setAttribute("SHOW_MODAL", "Update");
                    }
                }
            } else {
                request.setAttribute("UPDATE_UNSUCCESS", "Status was already \"" + slotDao.changeNumberStatus(slotdetail.getStatus()) + "\"");
                request.setAttribute("SHOW_MODAL", "Update");
            }
        } catch (Exception e) {
            log("Error at UpdateSlotController: " + e.toString());
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
