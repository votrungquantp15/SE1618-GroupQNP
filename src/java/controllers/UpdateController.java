/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author predator
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    public static final String ERROR = "SearchAccountByAdminController";
    public static final String SUCCESS = "SearchAccountByAdminController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String fullname = request.getParameter("fullname");
            String address = request.getParameter("address");
            String birthday = request.getParameter("birthday");
            String phone = request.getParameter("quantity");
            String email = request.getParameter("email");
            String accName = request.getParameter("accName");
            String password = request.getParameter("password");
            String roleID = request.getParameter("roleID");
            String status = request.getParameter("status");
            UserDAO dao = new UserDAO();
            UserDTO user = new UserDTO(userID, fullname, address, birthday, phone, email, accName, password, roleID, status);
            boolean checkUpdate = dao.update(user);
            if (checkUpdate) {
                UserDTO listProduct = (UserDTO) request.getAttribute("userList");
                if (listUser != null) {
                    if (listUser.getUserID().equals(UserID)) {
                        if (!listUser.getUserName().equals(UserName)) {
                            listUser.setUserName(userName);
                            request.setAttribute("userList", listUser);
                        }
                    }
                }
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at UpdateController: " + e.toString());
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
