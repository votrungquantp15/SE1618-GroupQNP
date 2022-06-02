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
    private static final String SEARCH_HISTORY = "SearchHistory";
    private static final String SEARCH_HISTORY_CONTROLLER = "SearchHistoryController";
    private static final String SEARCH_PRODUCT = "SearchProduct";
    private static final String SEARCH_PRODUCT_CONTROLLER = "SearchProductController";
    private static final String SEARCH_ACCOUNT_BY_ADMIN = "SearchAccountByAdmin";
    private static final String SEARCH_ACCOUNT_BY_ADMIN_CONTROLLER = "SearchAccountByAdminController";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String DELETE_ACCOUNT_BY_ADMIN = "DeleteAccountByAdmin";
    private static final String DELETE_ACCOUNT_BY_ADMIN_CONTROLLER = "DeleteAccountByAdminController";
    private static final String UPDATE_ACCOUNT_BY_ADMIN = "UpdateAccountByAdmin";
    private static final String UPDATE_ACCOUNT_BY_ADMIN_CONTROLLER = "UpdateAccountByAdminController";
    private static final String VIEW_ACCOUNT_LIST = "ViewAccountList";
    private static final String VIEW_ACCOUNT_LIST_CONTROLLER = "ViewAccountListController";
    private static final String CREATE_ACCOUNT_FOR_USER = "CreateAccountForUser";
    private static final String CREATE_CONTROLLER = "CreateAccountForUserController";
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
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (HOME.equals(action)) {
                url = HOME_CONTROLLER;
            } else if (SEARCH_HISTORY.equals(action)) {
                url = SEARCH_HISTORY_CONTROLLER;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (SEARCH_ACCOUNT_BY_ADMIN.equals(action)) {
                url = SEARCH_ACCOUNT_BY_ADMIN_CONTROLLER;
            } else if (DELETE_ACCOUNT_BY_ADMIN.equals(action)) {
                url = DELETE_ACCOUNT_BY_ADMIN_CONTROLLER;
            } else if (UPDATE_ACCOUNT_BY_ADMIN.equals(action)) {
                url = UPDATE_ACCOUNT_BY_ADMIN_CONTROLLER;
            } else if (CREATE_ACCOUNT_FOR_USER.equals(action)) {
                url = CREATE_CONTROLLER;
            } else if (SEARCH_PRODUCT.equals(action)) {
                url = SEARCH_PRODUCT_CONTROLLER;
            } else if (VIEW_ACCOUNT_LIST.equals(action)) {
                url = VIEW_ACCOUNT_LIST_CONTROLLER;
            } else if (RESET.equals(action)) {
                url = RESET_PASSWORD;
            }
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
