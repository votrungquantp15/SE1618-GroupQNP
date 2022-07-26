/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import dao.FieldDAO;
import dao.FoodDAO;
import dao.FoodDetailDAO;
import dto.Field;
import dto.Food;
import dto.FoodDetail;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ROG STRIX
 */
@WebServlet(name = "AddFoodToFieldController", urlPatterns = {"/AddFoodToFieldController"})
public class AddFoodToFieldController extends HttpServlet {

    public static final String ERROR = "addFoodToField.jsp";
    public static final String SUCCESS = "addFoodToField.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
                        
            HttpSession session = request.getSession();
            FoodDetailDAO fdao = new FoodDetailDAO();
            String foodDetailId = fdao.foodDetailIDForManager();

            String foodId = request.getParameter("foodId");
            FoodDAO foodDAO = new FoodDAO();
            Food food = foodDAO.getFoodByID(foodId);
            
            String id_of_field = request.getParameter("fieldId");
            FieldDAO fieldDAO = new FieldDAO();
            Field field = fieldDAO.getFieldByID(id_of_field);
            
            double price = Double.parseDouble(request.getParameter("price"));
            boolean check = true;                     

            if (check) {
                FoodDetail fFood = new FoodDetail(foodDetailId, food, field, price, "1");
                boolean checkInsertFieldId = fdao.insertFieldIdOfFood(fFood);
                if (checkInsertFieldId) {
                    url = SUCCESS;
                    session.setAttribute("FOOD_ID", foodId);
                    request.setAttribute("CREATE_SUCCESS", "Thêm thành công");
                } else {
                    request.setAttribute("CREATE_FAIL", "Thêm thất bại");
                }
//                    }
            } else {
                request.setAttribute("CREATE_FAIL", "Thêm thất bại");
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
