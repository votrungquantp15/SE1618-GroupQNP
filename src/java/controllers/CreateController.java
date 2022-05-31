/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CustomerDAO;
import dto.Customer;
import dto.CustomerError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ROG STRIX
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {
    public static final String ERROR="create.jsp";
    public static final String SUCCESS="login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR;
        try {
            String userID= request.getParameter("userID");
            String fullName= request.getParameter("fullName");
            String pass=request.getParameter("password");
            String confrim=request.getParameter("comfirm");
            String accName=request.getParameter("accName");
            String address=request.getParameter("address");
            String birthDay =request.getParameter("birthDay");
            String phone =request.getParameter("phone");
            String email=request.getParameter("email");
            boolean check = true;
            CustomerError cusError = new CustomerError();
            if(userID.length()<1 || userID.length()>10){
                cusError.setUserIDError("Sai UserID");
                check = false;
            }
            if(fullName.length()<2||fullName.length()>30){
                cusError.setFullNameError("Ten qua ngan hoac qua dai");
                check=false;
            }
            if(check){
                CustomerDAO dao = new CustomerDAO();
                Customer cus = new Customer(userID, fullName, address, birthDay, phone, email, accName, pass, "US", "1");
                boolean checkDup = dao.checkDuplicate(userID);
                    if(checkDup){
                        cusError.setUserIDError("Trung ID roi kia!!");
                        request.setAttribute("CUSTOMER_ERROR", cusError);
                    }else{
                        boolean checkInsert = dao.insert(cus);
                        if(checkInsert){
                             url = SUCCESS;
                        }else{
                            cusError.setMessageError("Khong the insert duoc!!");
                            request.setAttribute("CUSTOMER_ERROR", cusError);
                        }
                    }
            }else{
                request.setAttribute("CUSTOMER_ERROR", cusError);
            }
            
        } catch (Exception e) {
            log("Error at CreateController");
        }finally{
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
