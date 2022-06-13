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
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateFieldController extends HttpServlet {

    private static final String ERROR = "PrintFieldController";
    private static final String SUCCESS = "PrintFieldController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            FieldDAO fieldDao = new FieldDAO();
            String fieldID = fieldDao.createFieldId();
            String fieldName = request.getParameter("fieldName");
            fieldName = URLEncoder.encode(fieldName, "ISO-8859-1");
            fieldName = URLDecoder.decode(fieldName, "UTF-8");
            String description = request.getParameter("description");
            description = URLEncoder.encode(description, "ISO-8859-1");
            description = URLDecoder.decode(description, "UTF-8");
            String image = request.getParameter("image");
            String id_of_field_category = request.getParameter("categoryFieldId");
            FieldCategoryDAO fieldCate = new FieldCategoryDAO();
            FieldCategory categoryFieldID = fieldCate.getFieldCategoryByID(id_of_field_category);
            double price = Double.parseDouble(request.getParameter("price"));
            String id_of_user = request.getParameter("userId");
            UserDAO user = new UserDAO();
            User userID = user.getUserByID(id_of_user);
            String id_of_location = request.getParameter("locationId");
            id_of_location = URLEncoder.encode(id_of_location, "ISO-8859-1");
            id_of_location = URLDecoder.decode(id_of_location, "UTF-8");
            LocationDAO location = new LocationDAO();
            Location locationID = location.getLocationByID(id_of_location);
            String id_of_city = request.getParameter("cityId");
            id_of_city = URLEncoder.encode(id_of_city, "ISO-8859-1");
            id_of_city = URLDecoder.decode(id_of_city, "UTF-8");
            CityDAO city = new CityDAO();
            City cityID = city.getCityByID(id_of_city);

            boolean checkValidation = true;
            if (fieldName.length() <= 0 || fieldName.length() >= 15) {
                request.setAttribute("CREATE_ERROR", "Field name cannot be left blank and must be < 15");
                checkValidation = false;
            }

            if (description.length() <= 0) {
                request.setAttribute("CREATE_ERROR", "Description cannot be left blank");
                checkValidation = false;
            }

            if (price < 0) {
                request.setAttribute("CREATE_ERROR", "Price must be >= 0");
                checkValidation = false;
            }

            if (checkValidation) {
                Field field = new Field(fieldID, fieldName, description, image, categoryFieldID, price, userID, locationID, cityID, null);
                boolean checkCreate = fieldDao.createField(field);
                if (checkCreate) {
                    url = SUCCESS;
                }
                request.setAttribute("CREATE_SUCCESS", "Create field success!");
            } else {
                if (categoryFieldID == null) {
                    request.setAttribute("CREATE_CATE_ERROR", "CategoryId is not exist!");
                } else if (userID == null) {
                    request.setAttribute("CREATE_USER_ERROR", "UserId is not exist!");
                } else if (locationID == null) {
                    request.setAttribute("CREATE_LOCATION_ERROR", "LocationId is not exist!");
                } else if (cityID == null) {
                    request.setAttribute("CREATE_CITY_ERROR", "CityId is not exist!");
                }
                request.setAttribute("CREATE_UNSUCCESS", "Create field unsuccess! Please try again!");
            }
        } catch (Exception e) {
            log("Error at CreateFieldController: " + e.toString());
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
