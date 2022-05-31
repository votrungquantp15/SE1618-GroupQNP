/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author votr
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String SEARCH_HISTORY = "Search";
    private static final String SEARCH_HISTORY_CONTROLLER = "SearchHistoryController";
    private static final String SEARCH_PRODUCT = "SearchProduct";
    private static final String SEARCH_PRODUCT_CONTROLLER = "SearchProductController";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String DELETE = "Delete";
    private static final String DELETE_CONTROLLER = "DeleteController";
    private static final String UPDATE = "Update";
    private static final String UPDATE_CONTROLLER = "UpdateController";
    private static final String CREATE_ACCOUNT_FOR_USER = "CreateAccountForUser";
    private static final String CREATE_CONTROLLER = "CreateController";
    private static final String RESET = "ResetPassword";
    private static final String RESET_PASSWORD = "ResetPasswordController";

    private static final String HOME = "Home";
    private static final String HOME_CONTROLLER = "HomeController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
<<<<<<< HEAD
            switch(action){
                case LOGIN:
                    url = LOGIN_CONTROLLER;
                    break;
                case SEARCH_HISTORY:
                    url = SEARCH_HISTORY_CONTROLLER;
                    break;
                case LOGOUT:
                    url = LOGOUT_CONTROLLER;
                    break;
                case CREATE:
                    url = CREATE_CONTROLLER;
                    break;
                case RESET:
                    url = RESET_PASSWORD;
                    break;
=======
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (HOME.equals(action)) {
                url = HOME_CONTROLLER;
            } else if (SEARCH.equals(action)) {
                url = SEARCH_CONTROLLER;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (DELETE.equals(action)) {
                url = DELETE_CONTROLLER;
            } else if (UPDATE.equals(action)) {
                url = UPDATE_CONTROLLER;
            } else if (CREATE_ACCOUNT_FOR_USER.equals(action)) {
                url = CREATE_CONTROLLER;
            } else if (SEARCH_PRODUCT.equals(action)) {
                url = SEARCH_PRODUCT_CONTROLLER;
            } else if (RESET.equals(action)) {
                url = RESET_PASSWORD;
>>>>>>> e5e48c6e05ccfc8ef627a9e0590dc58cac0e2b77
            }
//            if (LOGIN.equals(action)) {
//                url = LOGIN_CONTROLLER;
//            } else if (HOME.equals(action)) {
//                url = HOME_CONTROLLER;
//            } else if (SEARCH.equals(action)) {
//                url = SEARCH_CONTROLLER;
//            } else if (LOGOUT.equals(action)) {
//                url = LOGOUT_CONTROLLER;
//            } else if (DELETE.equals(action)) {
//                url = DELETE_CONTROLLER;
//            } else if (UPDATE.equals(action)) {
//                url = UPDATE_CONTROLLER;
//            } else if (CREATE.equals(action)) {
//                url = CREATE_CONTROLLER;
//            } else if (SEARCH_PRODUCT.equals(action)) {
//                url = SEARCH_PRODUCT_CONTROLLER;
//            } else if (RESET.equals(action)) {
//                url = RESET_PASSWORD;
//            }
        } catch (Exception e) {
            log("Error at MainController" + e.toString());
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
