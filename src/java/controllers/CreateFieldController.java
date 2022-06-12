package controllers;

import dao.FieldDAO;
import dto.Field;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateFieldController extends HttpServlet {

    private static final String ERROR = "create.jsp";
    private static final String SUCCESS = "ShowController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String fieldName = request.getParameter("fieldName");
            String description = request.getParameter("description");
            String image = request.getParameter("image");
            String categoryFieldId = request.getParameter("categoryFieldId");
            double price = Double.parseDouble(request.getParameter("price"));
            String userId = request.getParameter("userId");
            String locationId = request.getParameter("locationId");
            String cityId = request.getParameter("cityId");
            FieldDAO dao = new FieldDAO();
            boolean checkValidation = true;
            if (fieldName.length() <=  0) {
                request.setAttribute("CREATE_ERROR", "Field name cannot be left blank");
                checkValidation = false;
            }
            
            if (description.length() <= 0) {
                request.setAttribute("CREATE_ERROR", "Description cannot be left blank");
                checkValidation = false;
            }
            
            if (price < 0) {
                request.setAttribute("CREATE_ERROR", "Price must be >= 0");
                checkValidation = false;
            }
            
            if (checkValidation) {
                Field field = new Field(getFieldID, fieldName, description, image, categoryFieldId, price, userId, locationId, cityId, null);
                boolean checkCreate = dao.create(field);
                if (checkCreate) {
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());
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
