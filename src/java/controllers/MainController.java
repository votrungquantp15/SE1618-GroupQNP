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
    //Search Slot
    private static final String SEARCH_SLOT = "SearchSlot";
    private static final String SEARCH_SLOT_CONTROLLER = "SearchSlotController";
    //Search Booking Order History
    private static final String SEARCH_BOOKING = "SearchBooking";
    private static final String SEARCH_BOOKING_CONTROLLER = "SearchBookingController";
    //Cancle, Delete Booking
    private static final String DELETE_BOOKING = "DeleteBooking";
    private static final String DELETE_BOOKING_CONTROLLER = "DeleteBookingController";
    //Update Booking
    private static final String UPDATE_BOOKING = "UpdateBooking";
    private static final String UPDATE_BOOKING_CONTROLLER = "UpdateBookingController";
    //Update Slot
    private static final String UPDATE_SLOT = "UpdateSlot";
    private static final String UPDATE_SLOT_CONTROLLER = "UpdateSlotController";
    //Delete Slot
    private static final String DELETE_SLOT = "DeleteSlot";
    private static final String DELETE_SLOT_CONTROLLER = "DeleteSlotController";
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

    //Print list field
    private static final String PRINT_LIST_FIELD = "Print";
    private static final String PRINT_LIST_FIELD_CONTROLLER = "PrintFieldController";

    //Print field detail
    private static final String PRINT_DETAIL_FIELD = "PrintDetail";
    private static final String PRINT_DETAIL_FIELD_CONTROLLER = "PrintFieldDetailController";

    //Print list city
    private static final String PRINT_LIST_CITY = "PrintCity";
    private static final String PRINT_LIST_CITY_CONTROLLER = "PrintCityController";

    //Print city detail
    private static final String PRINT_DETAIL_CITY = "PrintCityDetail";
    private static final String PPRINT_DETAIL_CITY_CONTROLLER = "PrintCityDetailController";

    //Delete field
    private static final String DELETE_LIST_FIELD = "DeleteField";
    private static final String DELETE_LIST_FIELD_CONTROLLER = "DeleteFieldController";

    //Update field
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

    //Print User Field Detail
    private static final String PRINT_USER_FIELD_DETAIL = "UserPrintFieldDetail";
    private static final String PRINT_USER_FIELD_DETAIL_CONTROLLER = "UserPrintFieldDetailController";
    
    //Search City By Admin
    private static final String SEARCH_CITY_BY_ADMIN = "SearchCityByAdmin";
    private static final String SEARCH_CITY_BY_ADMIN_CONTROLLER = "SearchCityByAdminController";
    
    //Update City By Admin
    private static final String UPDATE_CITY_BY_ADMIN = "UpdateCity";
    private static final String UPDATE_CITY_BY_ADMIN_CONTROLLER = "UpdateCityByAdminController";
    
    //Delete City By Admin
    private static final String DELETE_CITY_BY_ADMIN = "DeleteCity";
    private static final String DELETE_CITY_BY_ADMIN_CONTROLLER = "DeleteCityByAdminController";
    
    //Delete City By Admin
    private static final String CREATE_CITY_BY_ADMIN = "CreateCity";
    private static final String CREATE_CITY_BY_ADMIN_CONTROLLER = "CreateCityByAdminController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");

            switch (action) {
                case LOGIN:
                    url = LOGIN_CONTROLLER;
                    break;
                case HOME:
                    url = HOME_CONTROLLER;
                    break;
                case SEARCH_BOOKING:
                    url = SEARCH_BOOKING_CONTROLLER;
                    break;
                case SEARCH_SLOT:
                    url = SEARCH_SLOT_CONTROLLER;
                    break;
                case DELETE_SLOT:
                    url = DELETE_SLOT_CONTROLLER;
                    break;
                case UPDATE_SLOT:
                    url = UPDATE_SLOT_CONTROLLER;
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
                case UPDATE_BOOKING:
                    url = UPDATE_BOOKING_CONTROLLER;
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
                case PRINT_USER_FIELD_DETAIL:
                    url = PRINT_USER_FIELD_DETAIL_CONTROLLER;
                    break;
                case PRINT_LIST_CITY:
                    url = PRINT_LIST_CITY_CONTROLLER;
                    break;
                case PRINT_DETAIL_CITY:
                    url = PPRINT_DETAIL_CITY_CONTROLLER;
                    break;
                case SEARCH_CITY_BY_ADMIN:
                    url = SEARCH_CITY_BY_ADMIN_CONTROLLER;
                    break;
                case UPDATE_CITY_BY_ADMIN:
                    url = UPDATE_CITY_BY_ADMIN_CONTROLLER;
                    break;
                case DELETE_CITY_BY_ADMIN:
                    url = DELETE_CITY_BY_ADMIN_CONTROLLER;
                    break;
                case CREATE_CITY_BY_ADMIN:
                    url = CREATE_CITY_BY_ADMIN_CONTROLLER;
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
