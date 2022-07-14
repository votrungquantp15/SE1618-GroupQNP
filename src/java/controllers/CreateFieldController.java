package controllers;

import dao.DistrictDAO;
import dao.FieldCategoryDAO;
import dao.FieldDAO;
import dao.LocationDAO;
import dao.UserDAO;
import dto.District;
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
            boolean checkValidation = true;
            double priceOfField = 0;
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
            String price = request.getParameter("price");
            if (fieldDao.isNumeric(price) == false) {
                request.setAttribute("PRICE_ERROR", "Price must be a number and cannot be blank");
                checkValidation = false;
            } else {
                priceOfField = Double.parseDouble(price);
            }
            String userName = request.getParameter("userName");
            userName = URLEncoder.encode(userName, "ISO-8859-1");
            userName = URLDecoder.decode(userName, "UTF-8");
            UserDAO user = new UserDAO();
            User userAfter = user.getUserByName(userName);
            String id_of_location = request.getParameter("locationId");
            id_of_location = URLEncoder.encode(id_of_location, "ISO-8859-1");
            id_of_location = URLDecoder.decode(id_of_location, "UTF-8");
            LocationDAO location = new LocationDAO();
            Location locationID = location.getLocationByID(id_of_location);
            String id_of_district = request.getParameter("districtId");
            id_of_district = URLEncoder.encode(id_of_district, "ISO-8859-1");
            id_of_district = URLDecoder.decode(id_of_district, "UTF-8");
            DistrictDAO district = new DistrictDAO();
            District districtID = district.getDistrictByID(id_of_district);

            if (fieldName.trim().length() == 0 || fieldName.length() > 30) {
                request.setAttribute("NAME_ERROR", "Field name cannot be left blank and word length must be <= 30");
                checkValidation = false;
            }

            if (image.trim().length() == 0) {
                request.setAttribute("IMAGE_ERROR", "Image cannot be left blank");
                checkValidation = false;
            }

            if (priceOfField < 0) {
                request.setAttribute("PRICE_ERROR", "Price must be >= 0");
                checkValidation = false;
            }

            if (checkValidation) {
                Field field = new Field(fieldID, fieldName, description, image, categoryFieldID, priceOfField, userAfter, locationID, districtID, null);
                boolean checkCreate = fieldDao.createField(field);
                if (checkCreate) {
                    url = SUCCESS;
                }
                request.setAttribute("CREATE_SUCCESS", "Create field success!");
            } else {
                request.setAttribute("CREATE_UNSUCCESS", "Create field unsuccess! Please try again!");
                request.setAttribute("SHOW_MODAL", "1");
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
