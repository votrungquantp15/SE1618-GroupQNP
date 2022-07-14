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
    //Booking field
    private static final String BOOKING = "Booking";
    private static final String BOOKING_CONTROLLER = "CustomerBookingController";
    //add to cart
    private static final String ADD_CART = "AddCart";
    private static final String ADD_CART_CONTROLLER = "CustomerAddCartController";
    //Edit cart item
    private static final String EDIT_CART_ITEM = "EditCartItem";
    private static final String EDIT_CART_ITEM_CONTROLLER = "CustomerEditCartItemController";
    //Edit cart item
    private static final String EDIT_CART_ITEM_PAGE = "EditCartItemPage";
    private static final String EDIT_CART_ITEM_PAGE_CONTROLLER = "CustomerEditCartItemPageController";
    //delete cart item
    private static final String REMOVE_CART_ITEM = "RemoveCartItem";
    private static final String REMOVE_CART_ITEM_CONTROLLER = "CustomerRemoveCartItemController";
    //add to cart
    private static final String VIEW_CART = "ViewCart";
    private static final String VIEW_CART_CONTROLLER = "viewCart.jsp";
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
    private static final String FILTER_FIELD_BY_DISTRICT = "FilterFieldByDistrict";
    private static final String FILTER_FIELD_BY_DISTRICT_CONTROLLER = "FilterFieldByDistrict";

    //Account Editor
    private static final String ACCOUNT_EDITOR = "AccountEditor";
    private static final String ACCOUNT_EDITOR_CONTROLLER = "AccountEditorController";

    //Print list field
    private static final String PRINT_LIST_FIELD = "Print";
    private static final String PRINT_LIST_FIELD_CONTROLLER = "PrintFieldController";

    //Print field detail
    private static final String PRINT_DETAIL_FIELD = "PrintDetail";
    private static final String PRINT_DETAIL_FIELD_CONTROLLER = "PrintFieldDetailController";

    //Print list district
    private static final String PRINT_LIST_DISTRICT = "PrintDistrict";
    private static final String PRINT_LIST_DISTRICT_CONTROLLER = "PrintDistrictController";

    //Print district detail
    private static final String PRINT_DETAIL_DISTRICT = "PrintDistrictDetail";
    private static final String PPRINT_DETAIL_DISTRICT_CONTROLLER = "PrintDistrictDetailController";

    //Delete field
    private static final String DELETE_LIST_FIELD = "DeleteField";
    private static final String DELETE_LIST_FIELD_CONTROLLER = "DeleteFieldController";

    //Update field by admin
    private static final String UPDATE_FIELD = "UpdateField";
    private static final String UPDATE_FIELD_CONTROLLER = "UpdateFieldByAdminController";

    //Search Field By Name 
    private static final String SEARCH_FIELD_BY_USER = "SearchFieldByUser";
    private static final String SEARCH_FIELD_BY_USER_CONTROLLER = "SearchFieldByUserController";

    //Search Field By Admin
    private static final String SEARCH_FIELD_BY_ADMIN = "SearchFieldByAdmin";
    private static final String SEARCH_FIELD_BY_ADMIN_CONTROLLER = "SearchFieldByAdminController";

    //Create field
    private static final String CREATE_FIELD = "CreateField";
    private static final String CREATE_FIELD_BY_ADMIN_CONTROLLER = "CreateFieldController";

    //Print User Field Detail
    private static final String PRINT_USER_FIELD_DETAIL = "UserPrintFieldDetail";
    private static final String PRINT_USER_FIELD_DETAIL_CONTROLLER = "UserPrintFieldDetailController";

    //Search food for manager
    private static final String SEARCH_FOOD_BY_MANAGER = "SearchFoodByManager";
    private static final String SEARCH_FOOD_BY_MANAGER_CONTROLLER = "SearchFoodByManagerController";

    //View Food List for manager
    private static final String VIEW_FOOD_LIST = "ViewFoodList";
    private static final String VIEW_FOOD_LIST_CONTROLLER = "ViewFoodListController";

    //DELETE FOOD BY MANAGER
    private static final String DELETE_FOOD_BY_MANAGER = "DeleteFoodByManager";
    private static final String DELETE_FOOD_BY_MANAGER_CONTROLLER = "DeleteFoodByManagerController";

    //DELETE FOOD BY MANAGER
    private static final String UPDATE_FOOD_BY_MANAGER = "UpdateFoodByManager";
    private static final String UPDATE_FOOD_BY_MANAGER_CONTROLLER = "UpdateFoodByManagerController";

    //FOOD EDITOR
    private static final String FOOD_EDITOR = "FoodEditor";
    private static final String FOOD_EDITOR_CONTROLLER = "FoodEditorController";

    //DELETE FOOD BY MANAGER
    private static final String CREATE_FOOD_BY_MANAGER = "CreateFoodByManager";
    private static final String CREATE_FOOD_BY_MANAGER_CONTROLLER = "CreateFoodByManagerController";

    //Search District By Admin
    private static final String SEARCH_DISTRICT_BY_ADMIN = "SearchDistrictByAdmin";
    private static final String SEARCH_DISTRICT_BY_ADMIN_CONTROLLER = "SearchDistrictByAdminController";

    //Update District By Admin
    private static final String UPDATE_DISTRICT_BY_ADMIN = "UpdateDistrict";
    private static final String UPDATE_DISTRICT_BY_ADMIN_CONTROLLER = "UpdateDistrictByAdminController";

    //Delete District By Admin
    private static final String DELETE_DISTRICT_BY_ADMIN = "DeleteDistrict";
    private static final String DELETE_DISTRICT_BY_ADMIN_CONTROLLER = "DeleteDistrictByAdminController";

    //Delete District By Admin
    private static final String CREATE_DISTRICT_BY_ADMIN = "CreateDistrict";
    private static final String CREATE_DISTRICT_BY_ADMIN_CONTROLLER = "CreateDistrictController";

    //Paging
    private static final String ACCOUNT_LIST = "AccountList";
    private static final String ACCOUNT_LIST_CONTROLLER = "AccountListController";

    //Print Location list By Admin
    private static final String PRINT_LIST_LOCATION = "PrintLocation";
    private static final String PRINT_LIST_LOCATION_CONTROLLER = "PrintLocationController";

    //Search Location By Admin
    private static final String SEARCH_LOCATION_BY_ADMIN = "SearchLocationByAdmin";
    private static final String SEARCH_LOCATION_BY_ADMIN_CONTROLLER = "SearchLocationByAdminController";

    //Print Location By Admin
    private static final String DELETE_LOCATION_BY_ADMIN = "DeleteLocation";
    private static final String DELETE_LOCATION_BY_ADMIN_CONTROLLER = "DeleteLocationByAdminController";

    //Update Location By Admin
    private static final String UPDATE_LOCATION_BY_ADMIN = "UpdateLocation";
    private static final String UPDATE_LOCATION_BY_ADMIN_CONTROLLER = "UpdateLocationByAdminController";

    //Create Location By Admin
    private static final String CREATE_LOCATION_BY_ADMIN = "CreateLocation";
    private static final String CREATE_LOCATION_BY_ADMIN_CONTROLLER = "CreateLocationController";

    //Print list of field category
    private static final String PRINT_FIELD_CATE = "PrintFieldCate";
    private static final String PRINT_FIELD_CATE_CONTROLLER = "PrintFieldCateController";

    //Search field category
    private static final String SEARCH_FIELD_CATE_BY_ADMIN = "SearchFieldCateByAdmin";
    private static final String SEARCH_FIELD_CATE_BY_ADMIN_CONTROLLER = "SearchFieldCateByAdminController";

    //Delete field category
    private static final String DELETE_FIELD_CATE = "DeleteFieldCate";
    private static final String DELETE_FIELD_CATE_CONTROLLER = "DeleteFieldCateController";

    //Update field category
    private static final String UPDATE_FIELD_CATE = "UpdateFieldCate";
    private static final String UPDATE_FIELD_CATE_CONTROLLER = "UpdateFieldCateByAdminController";

    //Create field category
    private static final String CREATE_FIELD_CATE = "CreateFieldCate";
    private static final String CREATE_FIELD_CATE_CONTROLLER = "CreateFieldCateController";

    //Create Manager Account
    private static final String CREATE_MANAGER_ACCOUNT = "CreateManagerAccount";
    private static final String CREATE_MANAGER_ACCOUNT_CONTROLLER = "CreateManagerAccountController";

    //Create Food
    private static final String CREATE_FOOD = "CreateFood";
    private static final String CREATE_FOOD_CONTROLLER = "CreateFoodController";

    //View Food of field Controller
    private static final String VIEW_FOOD_OF_FIELD = "ViewFoodOfField";
    private static final String VIEW_FOOD_OF_FIELD_CONTROLLER = "ViewFoodOfFieldController";

    //Search Food of field 
    private static final String SEARCH_FOOD_OF_FIELD = "SearchFoodOfField";
    private static final String SEARCH_FOOD_OF_FIELD_CONTROLLER = "SearchFoodOfFieldController";

    //Search Food of field 
    private static final String DELETE_FOOD_BY_MANAGER_ON_FIELD = "DeleteFoodByManagerOnField";
    private static final String DELETE_FOOD_BY_MANAGER_ON_FIELD_CONTROLLER = "DeleteFoodByManagerOnFieldController";

    //Search Food of field 
    private static final String CREATE_FOOD_ON_FIELD = "CreateFoodOnField";
    private static final String CREATE_FOOD_ON_FIELD_CONTROLLER = "CreateFoodOnFieldController";
    
    //Payment
    private  static final String USER_PAYMENT = "Payment";
    private static final String USER_PAYMENT_CONTROLLER = "UserPaymentController";

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
                case BOOKING:
                    url = BOOKING_CONTROLLER;
                    break;
                case EDIT_CART_ITEM:
                    url = EDIT_CART_ITEM_CONTROLLER;
                    break;
                case EDIT_CART_ITEM_PAGE:
                    url = EDIT_CART_ITEM_PAGE_CONTROLLER;
                    break;
                case REMOVE_CART_ITEM:
                    url = REMOVE_CART_ITEM_CONTROLLER;
                    break;
                case ADD_CART:
                    url = ADD_CART_CONTROLLER;
                    break;
                case VIEW_CART:
                    url = VIEW_CART_CONTROLLER;
                    break;
                case SEARCH_BOOKING:
                    url = SEARCH_BOOKING_CONTROLLER;
                    break;
                case SEARCH_SLOT:
                    url = SEARCH_SLOT_CONTROLLER;
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
                case SEARCH_FIELD_BY_USER:
                    url = SEARCH_FIELD_BY_USER_CONTROLLER;
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
                case SEARCH_FOOD_BY_MANAGER:
                    url = SEARCH_FOOD_BY_MANAGER_CONTROLLER;
                    break;
                case VIEW_FOOD_LIST:
                    url = VIEW_FOOD_LIST_CONTROLLER;
                    break;
                case DELETE_FOOD_BY_MANAGER:
                    url = DELETE_FOOD_BY_MANAGER_CONTROLLER;
                    break;
                case UPDATE_FOOD_BY_MANAGER:
                    url = UPDATE_FOOD_BY_MANAGER_CONTROLLER;
                    break;
                case FOOD_EDITOR:
                    url = FOOD_EDITOR_CONTROLLER;
                    break;
                case CREATE_FOOD_BY_MANAGER:
                    url = CREATE_FOOD_BY_MANAGER_CONTROLLER;
                    break;
                case PRINT_LIST_DISTRICT:
                    url = PRINT_LIST_DISTRICT_CONTROLLER;
                    break;
                case PRINT_DETAIL_DISTRICT:
                    url = PPRINT_DETAIL_DISTRICT_CONTROLLER;
                    break;
                case SEARCH_DISTRICT_BY_ADMIN:
                    url = SEARCH_DISTRICT_BY_ADMIN_CONTROLLER;
                    break;
                case UPDATE_DISTRICT_BY_ADMIN:
                    url = UPDATE_DISTRICT_BY_ADMIN_CONTROLLER;
                    break;
                case DELETE_DISTRICT_BY_ADMIN:
                    url = DELETE_DISTRICT_BY_ADMIN_CONTROLLER;
                    break;
                case CREATE_DISTRICT_BY_ADMIN:
                    url = CREATE_DISTRICT_BY_ADMIN_CONTROLLER;
                    break;
                case ACCOUNT_LIST:
                    url = ACCOUNT_LIST_CONTROLLER;
                    break;
                case PRINT_LIST_LOCATION:
                    url = PRINT_LIST_LOCATION_CONTROLLER;
                    break;
                case SEARCH_LOCATION_BY_ADMIN:
                    url = SEARCH_LOCATION_BY_ADMIN_CONTROLLER;
                    break;
                case DELETE_LOCATION_BY_ADMIN:
                    url = DELETE_LOCATION_BY_ADMIN_CONTROLLER;
                    break;
                case UPDATE_LOCATION_BY_ADMIN:
                    url = UPDATE_LOCATION_BY_ADMIN_CONTROLLER;
                    break;
                case CREATE_LOCATION_BY_ADMIN:
                    url = CREATE_LOCATION_BY_ADMIN_CONTROLLER;
                    break;
                case PRINT_FIELD_CATE:
                    url = PRINT_FIELD_CATE_CONTROLLER;
                    break;
                case SEARCH_FIELD_CATE_BY_ADMIN:
                    url = SEARCH_FIELD_CATE_BY_ADMIN_CONTROLLER;
                    break;
                case DELETE_FIELD_CATE:
                    url = DELETE_FIELD_CATE_CONTROLLER;
                    break;
                case CREATE_MANAGER_ACCOUNT:
                    url = CREATE_MANAGER_ACCOUNT_CONTROLLER;
                    break;
                case CREATE_FOOD:
                    url = CREATE_FOOD_CONTROLLER;
                    break;
                case UPDATE_FIELD_CATE:
                    url = UPDATE_FIELD_CATE_CONTROLLER;
                    break;
                case CREATE_FIELD_CATE:
                    url = CREATE_FIELD_CATE_CONTROLLER;
                    break;
                case VIEW_FOOD_OF_FIELD:
                    url = VIEW_FOOD_OF_FIELD_CONTROLLER;
                    break;
                case SEARCH_FOOD_OF_FIELD:
                    url = SEARCH_FOOD_OF_FIELD_CONTROLLER;
                    break;
                case DELETE_FOOD_BY_MANAGER_ON_FIELD:
                    url = DELETE_FOOD_BY_MANAGER_ON_FIELD_CONTROLLER;
                    break;
                case CREATE_FOOD_ON_FIELD:
                    url = CREATE_FOOD_ON_FIELD_CONTROLLER;
                    break;
                case USER_PAYMENT:
                    url = USER_PAYMENT_CONTROLLER;
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
