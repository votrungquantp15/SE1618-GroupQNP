/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.FoodDAO;
import dao.FoodDetailDAO;
import dao.UserDAO;
import dto.Food;
import dto.FoodDetail;
import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author predator
 */
@WebServlet(name = "ViewFoodOfFieldController", urlPatterns = {"/ViewFoodOfFieldController"})
public class ViewFoodOfFieldController extends HttpServlet {

    private static final String ERROR = "foodManagementEach.jsp";
    private static final String MANAGER = "foodManagementEach.jsp";
    private static final String USER = "foodDetailUser.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();       
            User user = (User) session.getAttribute("LOGIN_USER");
            String fieldId = request.getParameter("fieldId");
            String indexPage = request.getParameter("index");            
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            FoodDAO dao = new FoodDAO();
            FoodDetailDAO fdao = new FoodDetailDAO();
            int count = dao.getTotalFoodEach(fieldId);
            int endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
                       
            List<Food> list = dao.viewFoodListEach(fieldId, index);
            session.setAttribute("FIELD_ID", fieldId);          
            request.setAttribute("VIEW_FOOD_EACH", list);
            ;
            request.setAttribute("END_PAGE_EACH", endPage);
            if (user.getRole().getRoleId().equals("MA")) {
                url = MANAGER;
            } else {
                url = USER;
            }
        } catch (Exception e) {
            log("Error at ViewFoodOfFieldController: " + e.toString());
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
