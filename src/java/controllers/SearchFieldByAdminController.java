package controllers;

import dao.CityDAO;
import dao.FieldCategoryDAO;
import dao.FieldDAO;
import dao.UserDAO;
import dto.City;
import dto.Field;
import dto.FieldCategory;
import dto.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchFieldByAdminController extends HttpServlet {

    private static final String SUCCESS = "fieldManagement.jsp";
    private static final String ERROR = "fieldManagement.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String searchBy = request.getParameter("searchBy");
            String fieldName = request.getParameter("searchByAdmin");
            String status = request.getParameter("status");
            FieldDAO fieldDao = new FieldDAO();
            List<Field> listField = fieldDao.searchFieldByAdmin(searchBy, fieldName, status);
            if (listField != null) {
                url = SUCCESS;
                request.setAttribute("LIST_FIELD", listField);
            } else {
                request.setAttribute("SEARCH_FIELD_ERROR", "Couldn't find any fields");
            }
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
