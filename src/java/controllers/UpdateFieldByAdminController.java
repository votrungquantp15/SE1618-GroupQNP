package controllers;

import dao.CityDAO;
import dao.FieldCategoryDAO;
import dao.FieldDAO;
import dao.LocationDAO;
import dao.UserDAO;
import dto.City;
import dto.Field;
import dto.FieldCategory;
import dto.Location;
import dto.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFieldByAdminController extends HttpServlet {

    public static final String ERROR = "PrintFieldController";
    public static final String SUCCESS = "PrintFieldController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String fieldID = request.getParameter("fieldId");
            String fieldName = request.getParameter("fieldName");
            String description = request.getParameter("description");
            String image = request.getParameter("image");
            String id_of_field_category = request.getParameter("categoryFieldId");
            FieldCategoryDAO fieldCate = new FieldCategoryDAO();
            FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
            String id_of_user = request.getParameter("userId");
            UserDAO user = new UserDAO();
            User userID = user.getUserByID(id_of_user);
            String id_of_location = request.getParameter("locationId");
            LocationDAO location = new LocationDAO();
            Location locationID = location.getLocationByID(id_of_location);
            String id_of_city = request.getParameter("cityId");
            CityDAO city = new CityDAO();
            City cityID = city.getCityByID(id_of_city);
            String status = request.getParameter("status");
            FieldDAO dao = new FieldDAO();
            Field field = new Field(fieldID, fieldName, description, image, categoryFieldID, userID, locationID, cityID, status);
            boolean checkUpdate = dao.updateStatusField(field);
            if (checkUpdate) {
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at UpdateFieldByAdminController: " + e.toString());
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
