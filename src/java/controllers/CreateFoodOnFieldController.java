/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import dao.FieldDAO;
import dao.FoodCategoryDAO;
import dao.FoodDAO;
import dao.FoodDetailDAO;
import dao.RoleDAO;
import dao.UserDAO;
import dto.User;
import dto.CustomerError;
import dto.Field;
import dto.Food;
import dto.FoodCategory;
import dto.FoodDetail;
import dto.FoodError;
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
@WebServlet(name = "CreateFoodOnFieldController", urlPatterns = {"/CreateFoodOnFieldController"})
public class CreateFoodOnFieldController extends HttpServlet {

    public static final String ERROR = "createFoodOnField.jsp";
    public static final String SUCCESS = "createFoodOnField.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            
            String categoryFoodId = request.getParameter("categoryFoodId");
              
            FoodCategoryDAO fCateDAO = new FoodCategoryDAO();
            List<FoodCategory> listFoodCategoryName = new ArrayList<>();

            listFoodCategoryName = fCateDAO.getAllFoodCategory();

            FoodCategory foodCategory = fCateDAO.getFoodCategoryByID(categoryFoodId);

            request.setAttribute("FOOD_CATEGORY_NAME", listFoodCategoryName);
            
            FoodDAO dao = new FoodDAO();
            FoodDetailDAO fdao = new FoodDetailDAO();
            String foodDetailId = fdao.foodDetailIDForManager();

            String foodId = dao.foodIDForManager();            
            
            String id_of_field = request.getParameter("fieldId");
            FieldDAO fieldDAO = new FieldDAO();
            Field field = fieldDAO.getFieldByID(id_of_field);
            
            double price = Double.parseDouble(request.getParameter("price"));
            String foodName = request.getParameter("foodName");
            foodName = URLEncoder.encode(foodName, "ISO-8859-1");
            foodName = URLDecoder.decode(foodName, "UTF-8");
            String image = request.getParameter("image");
            boolean check = true;           
            
            request.setAttribute("FOOD_CATEGORY_NAME", listFoodCategoryName);

            FoodError foodError = new FoodError();          
            foodError.setFoodNameError("");            
            if (foodName.length() <= 0 || foodName.length() > 30) {
                foodError.setFoodNameError("Tên phải nằm trong khoảng từ 1 - 30 kí tự ");
                check = false;
            }

            if (check) {
                Food food = new Food(foodId, foodName, image, foodCategory, "1");               
                boolean checkInsert = dao.insertFood(food);
                Food foodCreate = dao.getFoodByID(foodId);
                FoodDetail fFood = new FoodDetail(foodDetailId, foodCreate, field, price, "1");
                boolean checkInsertFieldId = fdao.insertFieldIdOfFood(fFood);
                if (checkInsert && checkInsertFieldId) {
                    url = SUCCESS;
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
