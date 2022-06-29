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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrintFieldController extends HttpServlet {

    private static final String USER_PAGE = "home.jsp";
    private static final String ADMIN_PAGE = "fieldManagement.jsp";
    private static final String OWNER_PAGE = "ownerFieldManagement.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = USER_PAGE;
        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("LOGIN_USER");
            
            UserDAO userDao = new UserDAO();
            List<User> listUser = userDao.getAllUser();
            request.setAttribute("LIST_USER", listUser);
            
            FieldCategoryDAO cateDao = new FieldCategoryDAO();
            List<FieldCategory> listCate = cateDao.getAllFieldCategory();
            request.setAttribute("LIST_CATEGORY", listCate);
            
            CityDAO cityDao = new CityDAO();
            List<City> listCity = cityDao.getAllCity();
            request.setAttribute("LIST_CITY", listCity);
            
            LocationDAO locationDao = new LocationDAO();
            List<Location> listLocation = locationDao.getAllLocation();
            request.setAttribute("LIST_LOCATION", listLocation);
            
            FieldDAO dao = new FieldDAO();
            List<Field> listField = dao.getListField();
            if (listField.size() > 0) {
                request.setAttribute("LIST_FIELD", listField);
                if (user.getRole().getRoleId().equals("US")) {
                    url = USER_PAGE;
                } else if (user.getRole().getRoleId().equals("AD")) {
                    url = ADMIN_PAGE;
                } else if (user.getRole().getRoleId().equals("MA")) {
                    url = OWNER_PAGE;
                }
            }
        } catch (Exception e) {
            log("Error at SearchController: " + e.toString());
        } finally {
            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception e) {
                log("Error at PrintFieldController: " + e.toString());
            }
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
