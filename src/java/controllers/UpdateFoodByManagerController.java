package controllers;

import dao.FieldDAO;
import dao.FoodCategoryDAO;
import dao.FoodDAO;
import dto.Field;
import dto.FoodCategory;
import dto.Food;
import dto.FoodDetailError;
import dto.FoodError;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateFoodByManagerController", urlPatterns = {"/UpdateFoodByManagerController"})
public class UpdateFoodByManagerController extends HttpServlet {

    public static final String ERROR = "FoodEditorController";
    public static final String SUCCESS = "FoodEditorController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean check = true;
            String foodID = request.getParameter("foodId");
            String foodName = request.getParameter("foodName");
            foodName = URLEncoder.encode(foodName, "ISO-8859-1");
            foodName = URLDecoder.decode(foodName, "UTF-8");       
            String image = request.getParameter("image");
            String id_of_field = request.getParameter("fieldId");
            FieldDAO fieldDAO = new FieldDAO();
            Field field = fieldDAO.getFieldByID(id_of_field);
            String foodCate = request.getParameter("categoryFoodId");
            FoodCategoryDAO fCate = new FoodCategoryDAO();
            FoodCategory f_Cate = fCate.getFoodCategoryByID(foodCate);
            String status = request.getParameter("status");
            FoodDAO dao = new FoodDAO();
            Food food = new Food(foodID, foodName, image, f_Cate, status);
            boolean checkUpdate = dao.updateFood(food);
            
            if (image.trim().length() <= 0) {
                request.setAttribute("FOOD_IMAGE_ERROR", "Đường dẫn không được để trống");
                check = false;
            }
            
            if (foodName.trim().length() <= 0) {
                request.setAttribute("FOOD_NAME_ERROR", "Tên không được bỏ trống");
                check = false;
            }
            
            
            if (checkUpdate && check) {                
                url = SUCCESS;
                request.setAttribute("UPDATE_SUCCESS", "Cập nhật thành công!!!");
            } else {
                request.setAttribute("UPDATE_FAILED", "Cập nhật thất bại!!!");
            }
        } catch (Exception e) {
            log("Error at UpdateFoodByAdminController: " + e.toString());
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
