package controllers;

import dao.FieldDAO;
import dao.SlotDAO;
import dto.Field;
import dto.Slot;
import dto.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrintSlotController extends HttpServlet {

    private static final String ERROR = "ownerSlotManagement.jsp";
    private static final String OWNER_PAGE = "ownerSlotManagement.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            SlotDAO slotDao = new SlotDAO();
            FieldDAO fieldDao = new FieldDAO();
            
            String fieldId = request.getParameter("fieldId");
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int endPage = 0;
            
            int count = slotDao.countTotalSlotByField(fieldId);
            
            List<Slot> listSlot = slotDao.getSlotByFieldPaging(fieldId, index);
            Field field = fieldDao.getFieldByID(fieldId);
            
            if (listSlot.size() > 0) {
                request.setAttribute("LIST_SLOT", listSlot);
                request.setAttribute("FIELD", field);
                url = OWNER_PAGE;
            }
            endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            request.setAttribute("END_PAGE", endPage);
            session.setAttribute("ACTION_SLOT", "Print");
        } catch (Exception e) {
            log("Error at PrintSlotController: " + e.toString());
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
