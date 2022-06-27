package controllers;

import dao.CityDAO;
import dto.City;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCityController extends HttpServlet {

    private static final String ERROR = "PrintCityController";
    private static final String SUCCESS = "PrintCityController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            CityDAO cityDao = new CityDAO();
            String cityID = cityDao.createCityId();
            String cityName = request.getParameter("cityName");
            cityName = URLEncoder.encode(cityName, "ISO-8859-1");
            cityName = URLDecoder.decode(cityName, "UTF-8");

            boolean checkValidation = true;
            if (cityName.trim().length() == 0) {
                request.setAttribute("CREATE_ERROR", "City name cannot be left blank");
                checkValidation = false;
            } else if (cityDao.checkCityName(cityName)) {
                request.setAttribute("CREATE_ERROR", "City name is already exist");
                checkValidation = false;
            }
            if (checkValidation) {
                City city = new City(cityID, cityName, null);
                boolean checkCreate = cityDao.createCity(city);
                if (checkCreate) {
                    url = SUCCESS;
                }
                request.setAttribute("CREATE_SUCCESS", "Create city success!");
            } else {
                request.setAttribute("CREATE_UNSUCCESS", "Create city unsuccess! Please try again!");
            }
        } catch (Exception e) {
            log("Error at CreateCityController: " + e.toString());
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
