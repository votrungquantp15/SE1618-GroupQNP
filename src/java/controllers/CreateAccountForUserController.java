/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CityDAO;
import dao.RoleDAO;
import dao.UserDAO;
import dto.City;
import dto.User;
import dto.CustomerError;
import dto.Role;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ROG STRIX
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateAccountForUserController"})
public class CreateAccountForUserController extends HttpServlet {

    public static final String ERROR = "createAccountForUser.jsp";
    public static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            
            String cityId = request.getParameter("cityId");
              
            CityDAO cityDao = new CityDAO();
            List<City> listCityName = new ArrayList<>();

            listCityName = cityDao.getAllCity();

            City city = cityDao.getCityByID(cityId);

            request.setAttribute("CITY_NAME", listCityName);
            
            UserDAO dao = new UserDAO();
            String userID = dao.userIDForCustomer();
            String fullName = request.getParameter("fullName");
            fullName = URLEncoder.encode(fullName, "ISO-8859-1");
            fullName = URLDecoder.decode(fullName, "UTF-8");
            String pass = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String accName = request.getParameter("accName");
            String birthDay = request.getParameter("birthDay");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            

            boolean check = true;
            
            UserDAO userDao = new UserDAO();
            RoleDAO roleDao = new RoleDAO();
            Role role = roleDao.getRoleByIDForCreate("US");


            request.setAttribute("CITY_NAME", listCityName);

            CustomerError cusError = new CustomerError();
            
            cusError.setEmailError("");
            cusError.setMessageError("");
            cusError.setFullNameError("");
            cusError.setConfirmError("");
            cusError.setAccNameError("");
            cusError.setPhoneError("");
            if(userDao.checkEmailExisted(email)){
                cusError.setEmailError("Email đã tồn tại");
                check = false;
            }
            if (fullName.length() <= 0 || fullName.length() > 30) {
                cusError.setFullNameError("Tên không được để trống kí tự!!");
                check = false;
            }
            if (!pass.equals(confirm)) {
                cusError.setConfirmError("2 mật khẩu không trùng nhau!!");
                check = false;
            }
            if (accName.length() <= 0 || accName.length() > 20) {
                cusError.setAccNameError("Tài khoản không được để trống và nhỏ hơn 20 kí tự!!");
                check = false;
            }
            if (phone.length() < 9 || phone.length() > 11) {
                cusError.setPhoneError("Số điện thoại ko chấp nhận!!");
                check = false;
            }
            if (check) {
                User cus = new User(userID, fullName, "", city, birthDay, phone, email, accName, pass, role, "1");
                boolean checkInsert = dao.insert(cus);
                if (checkInsert) {
                    url = SUCCESS;
                    cusError.setMessageError("Đăng kí thành công!!");
                    request.setAttribute("CUSTOMER_ERROR", cusError);
                } else {
                    cusError.setMessageError("Đăng kí thất bại!!");
                    request.setAttribute("CUSTOMER_ERROR", cusError);
                }
//                    }
            } else {
                request.setAttribute("CUSTOMER_ERROR", cusError);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
