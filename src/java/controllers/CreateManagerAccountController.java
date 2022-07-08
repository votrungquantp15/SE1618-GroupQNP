/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DistrictDAO;
import dao.RoleDAO;
import dao.UserDAO;
import dto.District;
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
@WebServlet(name = "CreateManagerAccountController", urlPatterns = {"/CreateManagerAccountController"})
public class CreateManagerAccountController extends HttpServlet {

    public static final String ERROR = "createManagerAccount.jsp";
    public static final String SUCCESS = "createManagerAccount.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            
            String districtId = request.getParameter("districtId");
              
            DistrictDAO districtDao = new DistrictDAO();
            List<District> listDistrictName = new ArrayList<>();

            listDistrictName = districtDao.getAllDistrict();

            District district = districtDao.getDistrictByID(districtId);

            request.setAttribute("DISTRICT_NAME", listDistrictName);
            
            UserDAO dao = new UserDAO();
            String userID = dao.userIDForCustomer();
            String fullName = request.getParameter("fullName");
            fullName = URLEncoder.encode(fullName, "ISO-8859-1");
            fullName = URLDecoder.decode(fullName, "UTF-8");
            String pass = request.getParameter("password");
            String accName = request.getParameter("accName");
            String birthDay = request.getParameter("birthday");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            

            boolean check = true;
            
            UserDAO userDao = new UserDAO();
            RoleDAO roleDao = new RoleDAO();
            Role role = roleDao.getRoleByIDForCreate("MA");


            request.setAttribute("DISTRICT_NAME", listDistrictName);

            CustomerError cusError = new CustomerError();
            
            cusError.setEmailError("");
            cusError.setMessageError("");
            cusError.setFullNameError("");
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
            if (accName.length() <= 0 || accName.length() > 20) {
                cusError.setAccNameError("Tài khoản không được để trống và nhỏ hơn 20 kí tự!!");
                check = false;
            }
            if (phone.length() < 9 || phone.length() > 11) {
                cusError.setPhoneError("Số điện thoại ko chấp nhận!!");
                check = false;
            }
            if (check) {
                User cus = new User(userID, fullName, "", district, birthDay, phone, email, accName, pass, role, "1");
                boolean checkInsert = dao.insert(cus);
                if (checkInsert) {
                    url = SUCCESS;
                    request.setAttribute("MANAGER_SUCCESS", "Create new manager's account success!!!");
                } else {
                    cusError.setMessageError("Đăng kí thất bại!!");
                    request.setAttribute("MANAGER_ERROR", "Create new manager's account error!!!");
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
