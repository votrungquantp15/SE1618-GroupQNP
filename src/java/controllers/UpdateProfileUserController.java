package controllers;

import dao.UserDAO;
import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProfileUserController extends HttpServlet {

    private final static String UPDATE_PROFILE_USER_SUCCESS = "profileUser.jsp";
    private final static String UPDATE_PROFILE_USER_ERROR = "profileUser.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = UPDATE_PROFILE_USER_ERROR;

        try {
            UserDAO userDAO = new UserDAO();
            boolean check = false;
            User user = null;
            String name = request.getParameter("name");
            name = URLEncoder.encode(name, "ISO-8859-1");
            name = URLDecoder.decode(name, "UTF-8");
            String birthday = request.getParameter("birth");
            String userID = request.getParameter("userID");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            user = new User(userID, name, address, birthday, phone, email, "", "", null, "");

            check = userDAO.updateProfileUser(user);

            if (check == true) {
                request.setAttribute("PROFILE_USER", userDAO.getUserByID(userID));
                url = UPDATE_PROFILE_USER_SUCCESS;
            }
        } catch (Exception e) {
            log("Error at SearchController: " + e.toString());
        } finally {
            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception e) {
                log("Error at SearchController: " + e.toString());
            }
        }
        request.getRequestDispatcher(url).forward(request, response);
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
