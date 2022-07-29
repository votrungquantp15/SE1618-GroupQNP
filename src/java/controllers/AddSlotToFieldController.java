package controllers;

import dao.FieldDAO;
import dao.SlotDAO;
import dao.SlotDetailDAO;
import dto.Field;
import dto.Slot;
import dto.SlotDetail;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddSlotToFieldController extends HttpServlet {

    private static final String ERROR = "PrintFieldDetailController";
    private static final String SUCCESS = "PrintFieldDetailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            SlotDetailDAO slotDetailDao = new SlotDetailDAO();
            String slotDetailId = slotDetailDao.createSlotDetailId();
            String slotId = request.getParameter("idSlot");
            SlotDAO slotDao = new SlotDAO();
            Slot slot = slotDao.getSlotByID(slotId);

            String fieldId = request.getParameter("fieldId");
            FieldDAO fieldDao = new FieldDAO();
            Field field = fieldDao.getFieldByID(fieldId);

            SlotDetail slotDetail = new SlotDetail(slotDetailId, slot, field, null);
            boolean checkDuplicate = slotDetailDao.checkSlotId(slotId, fieldId);
            if (checkDuplicate) {
                request.setAttribute("ADD_SLOT_UNSUCCESS", "Slot has already exist! Add slot unsuccess!");
            } else {
                boolean checkCreate = slotDetailDao.createSlotDetail(slotDetail);
                if (checkCreate) {
                    request.setAttribute("ADD_SLOT_SUCCESS", "Add slot success!");
                    url = SUCCESS;
                } else {
                    request.setAttribute("ADD_SLOT_UNSUCCESS", "Add slot unsuccess!");
                }
            }
            request.setAttribute("SHOW_MODAL", "2");
        } catch (Exception e) {
            log("Error at AddSlotToFieldController: " + e.toString());
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
