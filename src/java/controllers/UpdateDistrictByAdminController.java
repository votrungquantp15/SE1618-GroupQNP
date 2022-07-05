package controllers;

import dao.DistrictDAO;
import dto.District;
import dto.User;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateDistrictByAdminController extends HttpServlet {

    private static final String ERROR = "PrintDistrictController";
    private static final String SUCCESS = "PrintDistrictController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            DistrictDAO districtDao = new DistrictDAO();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            String id_district = request.getParameter("id_district");
            boolean checkValidation = true;

            if (user.getRole().getRoleId().equals("MA")) {
                String districtName = request.getParameter("districtName");
                districtName = URLEncoder.encode(districtName, "ISO-8859-1");
                districtName = URLDecoder.decode(districtName, "UTF-8");
                boolean checkExist = districtDao.checkExistDistrict(id_district);
                if (checkExist) {
                    request.setAttribute("UPDATE_ERROR", "This district being used cannot be changed!");
                    checkValidation = false;
                } else {
                    if (districtName.trim().length() == 0) {
                        request.setAttribute("UPDATE_ERROR", "District name cannot be left blank");
                        checkValidation = false;
                    }
                    if (checkValidation) {
                        District district = new District(id_district, districtName, null);
                        boolean checkUpdate = districtDao.updateDistrictByOwner(district);
                        if (checkUpdate) {
                            url = SUCCESS;
                            request.setAttribute("UPDATE_SUCCESS", "Update district success!");
                        } else {
                            request.setAttribute("UPDATE_UNSUCCESS", "Update district unsuccess! Please try again!");
                        }
                    }
                }
            } else if (user.getRole().getRoleId().equals("AD")) {
                String status = request.getParameter("status");
                District district = districtDao.getDistrictByID(id_district);
                String statusOfDistrict = district.getStatus();
                if (!districtDao.changeStringStatus(statusOfDistrict).equals(status)) {
                    boolean checkExist = districtDao.checkExistDistrict(id_district);
                    if (checkExist) {
                        request.setAttribute("UPDATE_ERROR", "This district being used cannot be changed status!");
                        checkValidation = false;
                    }
                    if (checkValidation) {
                        boolean checkUpdate = districtDao.updateStatusDistrict(id_district, status);
                        if (checkUpdate) {
                            url = SUCCESS;
                            request.setAttribute("UPDATE_SUCCESS", "Update district success!");
                        } else {
                            request.setAttribute("UPDATE_UNSUCCESS", "Update district unsuccess! Please try again!");
                        }
                    }
                } else {
                    request.setAttribute("UPDATE_UNSUCCESS", "Status was already \"" + district.getStatus() + "\"");
                }
            }

        } catch (Exception e) {
            log("Error at UpdateDistrictByAdminController: " + e.toString());
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
