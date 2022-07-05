package controllers;

import dao.LocationDAO;
import dto.Location;
import dto.User;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateLocationByAdminController extends HttpServlet {

    private static final String ERROR = "PrintLocationController";
    private static final String SUCCESS = "PrintLocationController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            LocationDAO locationDao = new LocationDAO();
            String id_location = request.getParameter("id_location");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            boolean checkValidation = true;

            if (user.getRole().getRoleId().equals("MA")) {
                String locationName = request.getParameter("locationName");
                locationName = URLEncoder.encode(locationName, "ISO-8859-1");
                locationName = URLDecoder.decode(locationName, "UTF-8");
                boolean checkExist = locationDao.checkExistLocation(id_location);
                if (checkExist) {
                    request.setAttribute("UPDATE_ERROR", "This location being used cannot be changed!");
                    checkValidation = false;
                } else {
                    if (locationName.trim().length() == 0) {
                        request.setAttribute("UPDATE_ERROR", "Location name cannot be left blank");
                        checkValidation = false;
                    }
                    if (checkValidation) {
                        Location location = new Location(id_location, locationName, null);
                        boolean checkUpdate = locationDao.updateLocationByOwner(location);
                        if (checkUpdate) {
                            url = SUCCESS;
                            request.setAttribute("UPDATE_SUCCESS", "Update location success!");
                        } else {
                            request.setAttribute("UPDATE_UNSUCCESS", "Update location unsuccess! Please try again!");
                        }
                    }
                }
            } else if (user.getRole().getRoleId().equals("AD")) {
                String status = request.getParameter("status");
                Location location = locationDao.getLocationByID(id_location);
                String statusOfLocation = location.getStatus();
                if (!locationDao.changeStringStatus(statusOfLocation).equals(status)) {
                    boolean checkExist = locationDao.checkExistLocation(id_location);
                    if (checkExist) {
                        request.setAttribute("UPDATE_ERROR", "This location being used cannot be changed status!");
                        checkValidation = false;
                    }
                    if (checkValidation) {
                        boolean checkUpdate = locationDao.updateStatusLocation(id_location, status);
                        if (checkUpdate) {
                            url = SUCCESS;
                            request.setAttribute("UPDATE_SUCCESS", "Update location success!");
                        } else {
                            request.setAttribute("UPDATE_UNSUCCESS", "Update location unsuccess! Please try again!");
                        }
                    }
                } else {
                    request.setAttribute("UPDATE_UNSUCCESS", "Status was already \"" + location.getStatus() + "\"");
                }
            }
        } catch (Exception e) {
            log("Error at UpdateLocationByAdminController: " + e.toString());
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
