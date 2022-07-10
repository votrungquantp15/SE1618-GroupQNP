package controllers;

import static controllers.AccountListController.SUCCESS;
import dao.FoodDAO;
import dao.UserDAO;
import dto.Food;
import dto.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ViewFoodListController", urlPatterns = {"/ViewFoodListController"})
public class ViewFoodListController extends HttpServlet {

    private static final String ERROR = "foodManagement.jsp";
    private static final String SUCCESS = "foodManagement.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        
        try {
            String indexPage = request.getParameter("index");
            if(indexPage == null)
                indexPage="1";
            int index = Integer.parseInt(indexPage);
            FoodDAO dao = new FoodDAO();
            int count = dao.getTotalFood();
            int endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            List<Food> listFood = dao.pagingFood(index);
            List<Food> list = dao.viewFoodList();
            request.setAttribute("VIEW_FOOD", list);
            request.setAttribute("VIEW_FOOD", listFood);
            request.setAttribute("END_PAGE", endPage);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at ViewFoodListController: " + e.toString());
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
