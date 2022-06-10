package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    //Login 
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    //Search Booking Order History
    private static final String SEARCH_BOOKING = "SearchHistory";
    private static final String SEARCH_BOOKING_CONTROLLER = "SearchBookingController";
    //Cancle, Delete Booking
    private static final String DELETE_BOOKING = "DeleteBooking";
    private static final String DELETE_BOOKING_CONTROLLER = "DeleteBookingController";
    //Show Booking Order Details
    private static final String SEARCH_BOOKING_DETAIL = "SearchBookingDetail";
    private static final String SEARCH_BOOKING_DETAIL_CONTROLLER = "SearchBookingDetailController";
    //Search Account (ADMIN)
    private static final String SEARCH_ACCOUNT_BY_ADMIN = "SearchAccountByAdmin";
    private static final String SEARCH_ACCOUNT_BY_ADMIN_CONTROLLER = "SearchAccountByAdminController";
    //Log Out
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    //Delete Account (ADMIN)
    private static final String DELETE_ACCOUNT_BY_ADMIN = "DeleteAccountByAdmin";
    private static final String DELETE_ACCOUNT_BY_ADMIN_CONTROLLER = "DeleteAccountByAdminController";
    //Update Account (ADMIN)
    private static final String UPDATE_ACCOUNT_BY_ADMIN = "UpdateAccountByAdmin";
    private static final String UPDATE_ACCOUNT_BY_ADMIN_CONTROLLER = "UpdateAccountByAdminController";
    //Create Account
    private static final String CREATE_ACCOUNT_FOR_USER = "CreateAccountForUser";
    private static final String CREATE_CONTROLLER = "CreateAccountForUserController";
    //Change Password
    private static final String RESET_PASSWORD = "ResetPassword";
    private static final String RESET_PASSWORD_CONTROLLER = "ResetPasswordController";
    //
    private static final String PRINT_LIST_FIELD = "Print";
    private static final String PRINT_LIST_FIELD_CONTROLLER = "PrintFieldController";
    //
    private static final String PRINT_DETAIL_FIELD = "PrintDetail";
    private static final String PRINT_DETAIL_FIELD_CONTROLLER = "PrintFieldDetailController";
    //
    private static final String DELETE_LIST_FIELD = "DeleteField";
    private static final String DELETE_LIST_FIELD_CONTROLLER = "DeleteFieldController";
    //
    private static final String UPDATE_FIELD = "UpdateField";
    private static final String UPDATE_FIELD_CONTROLLER = "UpdateFieldByAdminController";
    //Profile User Page
    private static final String PROFILE_USER = "ProfileUser";
    private static final String PROFILE_USER_CONTROLLER = "ProfileUserController";
   //Update Profile User 
    private static final String UPDATE_PROFILE_USER = "UpdateProfileUser";
    private static final String UPDATE_PROFILE_USER_CONTROLLER = "UpdateProfileUserController";
    //Home page
    private static final String HOME = "Home";
    private static final String HOME_CONTROLLER = "HomeController";

    //
    private static final String FILTER_FIELD_BY_CITY = "FilterFieldByCity";
    private static final String FILTER_FIELD_BY_CITY_CONTROLLER = "FilterFieldByCity";
    

    //View Account List
    private static final String VIEW_ACCOUNT_LIST = "ViewAccountList";
    private static final String VIEW_ACCOUNT_LIST_CONTROLLER = "ViewAccountListController";

    //Account Editor
    private static final String ACCOUNT_EDITOR = "AccountEditor";
    private static final String ACCOUNT_EDITOR_CONTROLLER = "AccountEditorController";

    //Search Field By Name 
    private static final String SEARCH_FIELD_BY_NAME = "SearchFieldByName";
    private static final String SEARCH_FIELD_BY_NAME_CONTROLLER = "SearchFieldByNameController";
    
    //Search Field By Admin
    private static final String SEARCH_FIELD_BY_ADMIN = "SearchFieldByAdmin";
    private static final String SEARCH_FIELD_BY_ADMIN_CONTROLLER = "SearchFieldByAdminController";
    
    //Create field
    private static final String CREATE_FIELD = "CreateField";
    private static final String CREATE_FIELD_BY_ADMIN_CONTROLLER = "CreateFieldController";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            
            switch(action){
                case LOGIN:
                    url = LOGIN_CONTROLLER;
                    break;
                case HOME:
                    url = HOME_CONTROLLER;
                    break;
                case SEARCH_BOOKING:
                    url = SEARCH_BOOKING_CONTROLLER;
                    break;
                case SEARCH_BOOKING_DETAIL:
                    url = SEARCH_BOOKING_DETAIL_CONTROLLER;
                    break;
                case LOGOUT:
                    url = LOGOUT_CONTROLLER;
                    break;
                case SEARCH_ACCOUNT_BY_ADMIN:
                    url = SEARCH_ACCOUNT_BY_ADMIN_CONTROLLER;
                    break;
                case DELETE_ACCOUNT_BY_ADMIN:
                    url = DELETE_ACCOUNT_BY_ADMIN_CONTROLLER;
                    break;
                case UPDATE_ACCOUNT_BY_ADMIN:
                    url = UPDATE_ACCOUNT_BY_ADMIN_CONTROLLER;
                    break;
                case CREATE_ACCOUNT_FOR_USER:
                    url = CREATE_CONTROLLER;
                    break;
                case RESET_PASSWORD:
                    url = RESET_PASSWORD_CONTROLLER;
                    break;
                case PRINT_LIST_FIELD:
                    url = PRINT_LIST_FIELD_CONTROLLER;
                    break;
                case UPDATE_FIELD:
                    url = UPDATE_FIELD_CONTROLLER;
                    break;
                case DELETE_LIST_FIELD:
                    url = DELETE_LIST_FIELD_CONTROLLER;
                    break;
                case PRINT_DETAIL_FIELD:
                    url = PRINT_DETAIL_FIELD_CONTROLLER;
                    break;
                case PROFILE_USER:
                    url = PROFILE_USER_CONTROLLER;
                    break;
                case UPDATE_PROFILE_USER:
                    url = UPDATE_PROFILE_USER_CONTROLLER;
                    break;
                case DELETE_BOOKING:
                    url = DELETE_BOOKING_CONTROLLER;
                    break;
                case VIEW_ACCOUNT_LIST:
                    url = VIEW_ACCOUNT_LIST_CONTROLLER;
                    break;
                case SEARCH_FIELD_BY_NAME:
                    url = SEARCH_FIELD_BY_NAME_CONTROLLER;
                    break;
                case ACCOUNT_EDITOR:
                    url = ACCOUNT_EDITOR_CONTROLLER;
                    break;
                case SEARCH_FIELD_BY_ADMIN:
                    url = SEARCH_FIELD_BY_ADMIN_CONTROLLER;
                    break;
                case CREATE_FIELD:
                    url = CREATE_FIELD_BY_ADMIN_CONTROLLER;
                    break;
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
