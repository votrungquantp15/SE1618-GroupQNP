package controllers;

import dao.DistrictDAO;
import dao.FieldCategoryDAO;
import dao.FieldDAO;
import dao.LocationDAO;
import dto.District;
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

public class PrintFieldDetailController extends HttpServlet {

    private static final String ERROR = "fieldDetailManagement.jsp";
    private static final String ADMIN_PAGE = "fieldDetailManagement.jsp";
    private static final String OWNER_PAGE = "ownerFieldDetailManagement.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            request.setAttribute("USER_ID", user.getUserID());
            
            String id_of_field = request.getParameter("fieldId");
            FieldDAO fieldDao = new FieldDAO();
            Field listField = fieldDao.getFieldByID(id_of_field);
            request.setAttribute("FIELD_DETAIL", listField);
            
            FieldCategoryDAO cateDao = new FieldCategoryDAO();
            List<FieldCategory> listCate = cateDao.getAllFieldCategory();
            request.setAttribute("LIST_CATEGORY", listCate);
            
            DistrictDAO districtDao = new DistrictDAO();
            List<District> listDistrict = districtDao.getAllDistrict();
            request.setAttribute("LIST_DISTRICT", listDistrict);
            
            LocationDAO locationDao = new LocationDAO();
            List<Location> listLocation = locationDao.getAllLocation();
            request.setAttribute("LIST_LOCATION", listLocation);
            
            if (user.getRole().getRoleId().equals("MA")) {
                url = OWNER_PAGE;
            } else if (user.getRole().getRoleId().equals("AD")) {
                url = ADMIN_PAGE;
            }
        } catch (Exception e) {
            log("Error at PrintFieldDetailController: " + e.toString());
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
