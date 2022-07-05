package controllers;

import dao.DistrictDAO;
import dto.District;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateDistrictController extends HttpServlet {

    private static final String ERROR = "PrintDistrictController";
    private static final String SUCCESS = "PrintDistrictController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            DistrictDAO districtDao = new DistrictDAO();
            String districtID = districtDao.createDistrictId();
            String districtName = request.getParameter("districtName");
            districtName = URLEncoder.encode(districtName, "ISO-8859-1");
            districtName = URLDecoder.decode(districtName, "UTF-8");

            boolean checkValidation = true;
            if (districtName.trim().length() == 0) {
                request.setAttribute("CREATE_ERROR", "District name cannot be left blank");
                checkValidation = false;
            } else if (districtDao.checkDistrictName(districtName)) {
                request.setAttribute("CREATE_ERROR", "District name is already exist");
                checkValidation = false;
            }
            if (checkValidation) {
                District district = new District(districtID, districtName, null);
                boolean checkCreate = districtDao.createDistrict(district);
                if (checkCreate) {
                    url = SUCCESS;
                }
                request.setAttribute("CREATE_SUCCESS", "Create district success!");
            } else {
                request.setAttribute("CREATE_UNSUCCESS", "Create district unsuccess! Please try again!");
            }
        } catch (Exception e) {
            log("Error at CreateDistrictController: " + e.toString());
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
