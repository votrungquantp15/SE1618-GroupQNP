package controllers;

import dao.FieldDAO;
import dao.UserDAO;
import dto.Field;
import dto.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {

    public static final String ERROR = "login.jsp";
    public static final String USER_PAGE = "PrintFieldController";
    public static final String ADMIN_PAGE = "adminDashboard.jsp";
    public static final String MANAGER_PAGE = "fieldOwnerDashboard.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UserDAO dao = new UserDAO();
            User cus = dao.checkLogin(email, password);

//            //Get category 
//            List<FieldCategory> listFieldCategorys = new ArrayList<>();
//            FieldCategoryDAO fieldCategoryDAO = new FieldCategoryDAO();
//            listFieldCategorys = fieldCategoryDAO.getAllFieldCategory();
//
//            //Get district
//            List<District> listDistricts = new ArrayList<>();
//            DistrictDAO districtDao = new DistrictDAO();
//            listDistricts = districtDao.getAllDistrict();
            //Get price 
            List<Field> listFields = new ArrayList<>();
            FieldDAO fieldDao = new FieldDAO();
            listFields = fieldDao.getListFieldByUser();

            if (cus != null) {
                session.setAttribute("LOGIN_USER", cus);
                String roleID = cus.getRole().getRoleId();
                //setAttribute districts

                //setAttribute Fields
                request.setAttribute("FIELD", listFields);

                //setAttribute category
                if (roleID.equals("US")) {
                    url = USER_PAGE;
                } else if (roleID.equals("AD")) {
                    url = ADMIN_PAGE;
                } else if (roleID.equals("MA")) {
                    url = MANAGER_PAGE;
                } else {
                    request.setAttribute("ERROR_MESSAGE", "Wrong Role!");
                }
            } else {
                request.setAttribute("ERROR_MESSAGE", "Wrong Email or Password!");
            }
        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
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
