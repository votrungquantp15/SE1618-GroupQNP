package controllers;

import dao.CityDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCityByAdminController extends HttpServlet {

    private static final String ERROR = "PrintCityController";
    private static final String SUCCESS = "PrintCityController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String cityId = request.getParameter("cityId");
            CityDAO cityDao = new CityDAO();
            boolean checkDelete = cityDao.checkExistCity(cityId);
            if (checkDelete == false) {
                boolean check = cityDao.deleteCity(cityId);
                if (check) {
                    url = SUCCESS;
                    request.setAttribute("DELETE_SUCCESS", "Delete city success!");
                } else {
                    request.setAttribute("DELETE_UNSUCCESS", "Delete city unsuccess! Please try again!");
                }
            } else {
                request.setAttribute("DELETE_UNSUCCESS", "This city being used cannot be deleted! Delete unsuccess!");
            }
        } catch (Exception e) {
            log("Error at DeleteCityByAdminController: " + e.toString());
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
