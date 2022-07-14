package controllers;

import dao.FieldCategoryDAO;
import dto.FieldCategory;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateFieldCateController extends HttpServlet {

    private static final String ERROR = "PrintFieldCateController";
    private static final String SUCCESS = "PrintFieldCateController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            FieldCategoryDAO fieldCateDao = new FieldCategoryDAO();
            String fieldCateId = fieldCateDao.createFieldCateId();
            String fieldCateName = request.getParameter("fieldCateName");
            fieldCateName = URLEncoder.encode(fieldCateName, "ISO-8859-1");
            fieldCateName = URLDecoder.decode(fieldCateName, "UTF-8");

            boolean checkValidation = true;
            if (fieldCateName.trim().length() == 0) {
                request.setAttribute("CREATE_ERROR", "Field category name cannot be left blank");
                checkValidation = false;
            } else if (fieldCateDao.checkFieldCateName(fieldCateName)) {
                request.setAttribute("CREATE_ERROR", "Field category name is already exist");
                checkValidation = false;
            }
            if (checkValidation) {
                FieldCategory fieldCate = new FieldCategory(fieldCateId, fieldCateName, null);
                boolean checkCreate = fieldCateDao.createFieldCate(fieldCate);
                if (checkCreate) {
                    url = SUCCESS;
                }
                request.setAttribute("CREATE_SUCCESS", "Create field category success!");
            } else {
                request.setAttribute("CREATE_UNSUCCESS", "Create field category unsuccess! Please try again!");
                request.setAttribute("SHOW_MODAL", "Create");
            }
        } catch (Exception e) {
            log("Error at CreateFieldCateController: " + e.toString());
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
