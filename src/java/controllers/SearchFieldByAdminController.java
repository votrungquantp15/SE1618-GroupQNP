package controllers;

import dao.FieldDAO;
import dto.Field;
import dto.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchFieldByAdminController extends HttpServlet {

    private static final String ADMIN_PAGE = "fieldManagement.jsp";
    private static final String OWNER_PAGE = "ownerFieldManagement.jsp";
    private static final String ERROR = "fieldManagement.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            String searchBy = request.getParameter("searchBy");
            String search = request.getParameter("searchByAdmin");
            String status = request.getParameter("status");
            FieldDAO fieldDao = new FieldDAO();
            List<Field> listField = null;
            int count = 0;
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            if (user.getRole().getRoleId().equals("MA")) {
                String userId = user.getUserID();
                count =  fieldDao.countSearchTotalFieldByOwner(searchBy, search, status, userId);
                listField = fieldDao.searchFieldByOwner(searchBy, search, status, userId, index);
                url = OWNER_PAGE;
                if (!listField.isEmpty()) {
                    request.setAttribute("LIST_FIELD", listField);
                } else {
                    request.setAttribute("SEARCH_FIELD_ERROR", "Couldn't find any fields");
                }
            } else if (user.getRole().getRoleId().equals("AD")) {
                count =  fieldDao.countSearchTotalField(searchBy, search, status);
                listField = fieldDao.searchFieldByAdmin(searchBy, search, status, index);
                url = ADMIN_PAGE;
                if (!listField.isEmpty()) {
                    request.setAttribute("LIST_FIELD", listField);
                } else {
                    request.setAttribute("SEARCH_FIELD_ERROR", "Couldn't find any fields");
                }
            }
            int endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            request.setAttribute("SEARCH_BY", searchBy);
            request.setAttribute("SEARCH", search);
            request.setAttribute("STATUS", status);
            request.setAttribute("END_PAGE", endPage);
            session.setAttribute("ACTION_FIELD", "Search");
        } catch (Exception e) {
            log("Error at SearchFieldByAdminController: " + e.toString());
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
