package controllers;

import dao.LocationDAO;
import dto.Location;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateLocationController extends HttpServlet {

    private static final String ERROR = "PrintLocationController";
    private static final String SUCCESS = "PrintLocationController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            LocationDAO locationDao = new LocationDAO();
            String locationID = locationDao.createLocationId();
            String locationName = request.getParameter("locationName");
            locationName = URLEncoder.encode(locationName, "ISO-8859-1");
            locationName = URLDecoder.decode(locationName, "UTF-8");
            boolean checkValidation = true;
            if (locationName.trim().length() == 0) {
                request.setAttribute("CREATE_ERROR", "Location name cannot be left blank");
                checkValidation = false;
            } else if (locationDao.checkLocationName(locationName)) {
                request.setAttribute("CREATE_ERROR", "Location name is already exist");
                checkValidation = false;
            }
            if (checkValidation) {
                Location location = new Location(locationID, locationName, null);
                boolean checkCreate = locationDao.createLocation(location);
                if (checkCreate) {
                    url = SUCCESS;
                }
                request.setAttribute("CREATE_SUCCESS", "Create location success!");
            } else {
                request.setAttribute("CREATE_UNSUCCESS", "Create location unsuccess! Please try again!");
                request.setAttribute("SHOW_MODAL", "Create");
            }
        } catch (Exception e) {
            log("Error at CreateLocationController: " + e.toString());
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
