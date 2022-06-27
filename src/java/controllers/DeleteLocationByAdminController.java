package controllers;

import dao.LocationDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteLocationByAdminController extends HttpServlet {

    private static final String ERROR = "PrintLocationController";
    private static final String SUCCESS = "PrintLocationController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String locationId = request.getParameter("locationId");
            LocationDAO locationDao = new LocationDAO();
            boolean checkDelete = locationDao.checkDeleteLocation(locationId);
            if (checkDelete == false) {
                boolean check = locationDao.deleteLocation(locationId);
                if (check) {
                    url = SUCCESS;
                    request.setAttribute("DELETE_SUCCESS", "Delete location success!");
                } else {
                    request.setAttribute("DELETE_UNSUCCESS", "Delete location unsuccess! Please try again!");
                }
            } else {
                request.setAttribute("DELETE_UNSUCCESS", "This location being used cannot be deleted! Delete unsuccess!");
            }
        } catch (Exception e) {
            log("Error at DeleteLocationByAdminController: " + e.toString());
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
