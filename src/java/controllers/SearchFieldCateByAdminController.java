package controllers;

import dao.FieldCategoryDAO;
import dto.FieldCategory;
import dto.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchFieldCateByAdminController extends HttpServlet {

    private static final String ADMIN_PAGE = "fieldCategoryManagement.jsp";
    private static final String OWNER_PAGE = "ownerFieldCategoryManagement.jsp";
    private static final String ERROR = "fieldCategoryManagement.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("LOGIN_USER");
            String fieldCateName = request.getParameter("searchByAdmin");
            String status = request.getParameter("status");
            FieldCategoryDAO fieldCateDao = new FieldCategoryDAO();
            List<FieldCategory> listFieldCate = fieldCateDao.searchFieldCateByAdmin(fieldCateName, status);
            if (!listFieldCate.isEmpty()) {
                request.setAttribute("LIST_FIELD_CATE", listFieldCate);
                if (user.getRole().getRoleId().equals("MA")) {
                    url = OWNER_PAGE;
                } else if (user.getRole().getRoleId().equals("AD")) {
                    url = ADMIN_PAGE;
                }
            } else {
                request.setAttribute("SEARCH_CITY_ERROR", "Couldn't find any citys");
            }
        } catch (Exception e) {
            log("Error at SearchFieldCateByAdminController: " + e.toString());
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
