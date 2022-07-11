package controllers;

import dao.SlotDAO;
import dto.Slot;
import dto.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchSlotController extends HttpServlet {

    private static final String ADMIN_PAGE = "slotManagementAdmin.jsp";
    private static final String OWNER_PAGE = "ownerSlotManagement.jsp";
    private static final String ERROR = "slotManagementAdmin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String indexPage = request.getParameter("index");
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            String search = request.getParameter("search");
            SlotDAO slotDAO = new SlotDAO();
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int count = slotDAO.getTotalSlot(search);
            int endPage = count / 12;
            if (endPage == 0) {
                endPage = 1;
            }
            if (count % 12 != 0) {
                endPage++;
            }

            List<Slot> list = slotDAO.getListSlotByID(search, index);
            request.setAttribute("END_PAGE", endPage);
            request.setAttribute("LIST_SLOT", list);
            if (user.getRole().getRoleId().equals("MA")) {
                url = OWNER_PAGE;
            } else if (user.getRole().getRoleId().equals("AD")) {
                url = ADMIN_PAGE;
            }
        } catch (Exception e) {
            log("Error at SearchSlotController: " + e.toString());
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
