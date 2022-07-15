package controllers;

import dao.FoodCategoryDAO;
import dao.FoodDAO;
import dao.FoodDetailDAO;
import dao.UserDAO;
import dto.Food;
import dto.FoodCategory;
import dto.FoodDetail;
import dto.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FoodEditorController", urlPatterns = {"/FoodEditorController"})
public class FoodEditorController extends HttpServlet {

    private final static String SUCCESS = "foodEditor.jsp";
    private final static String ERROR = "foodEditor.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String foodId = request.getParameter("foodId");
            String fieldId = request.getParameter("fieldId");           
            FoodDAO dao = new FoodDAO();
            FoodDetailDAO fdao = new FoodDetailDAO();
            List<Food> listFood = dao.searchFoodByIdForManager(foodId);
            List<FoodDetail> listFoodDetail = fdao.getFoodDetailIdByFoodIdAndFieldId(foodId, fieldId);
            if (listFood.size() > 0) {
                request.setAttribute("VIEW_FOOD", listFood);
                request.setAttribute("FOOD_PRICE", listFoodDetail);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at FoodEditorController: " + e.toString());
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
