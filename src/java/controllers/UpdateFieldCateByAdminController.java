package controllers;

import dao.FieldCategoryDAO;
import dto.FieldCategory;
import dto.User;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateFieldCateByAdminController extends HttpServlet {

    private static final String ERROR = "PrintFieldCateController";
    private static final String SUCCESS = "PrintFieldCateController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            FieldCategoryDAO fieldCateDao = new FieldCategoryDAO();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            String id_fieldCate = request.getParameter("id_fieldCate");
            boolean checkValidation = true;

            if (user.getRole().getRoleId().equals("MA")) {
                String fieldCateName = request.getParameter("fieldCateName");
                fieldCateName = URLEncoder.encode(fieldCateName, "ISO-8859-1");
                fieldCateName = URLDecoder.decode(fieldCateName, "UTF-8");
                boolean checkExist = fieldCateDao.checkExistFieldCate(id_fieldCate);
                if (checkExist) {
                    request.setAttribute("UPDATE_ERROR", "This field category being used cannot be changed!");
                    checkValidation = false;
                } else {
                    if (fieldCateName.trim().length() == 0) {
                        request.setAttribute("UPDATE_ERROR", "Field category name cannot be left blank");
                        checkValidation = false;
                    }
                    if (checkValidation) {
                        FieldCategory fieldCate = new FieldCategory(id_fieldCate, fieldCateName, null);
                        boolean checkUpdate = fieldCateDao.updateFieldCateByOwner(fieldCate);
                        if (checkUpdate) {
                            url = SUCCESS;
                            request.setAttribute("UPDATE_SUCCESS", "Update field category success!");
                        } else {
                            request.setAttribute("UPDATE_UNSUCCESS", "Update field category unsuccess! Please try again!");
                        }
                    }
                }
            } else if (user.getRole().getRoleId().equals("AD")) {
                String status = request.getParameter("status");
                FieldCategory fieldCate = fieldCateDao.getFieldCategoryByID(id_fieldCate);
                String statusOfFieldCate = fieldCate.getStatus();
                if (!statusOfFieldCate.equals(status)) {
                    boolean checkExist = fieldCateDao.checkExistFieldCate(id_fieldCate);
                    if (checkExist) {
                        request.setAttribute("UPDATE_ERROR", "This field category being used cannot be changed status!");
                        checkValidation = false;
                    }
                    if (checkValidation) {
                        boolean checkUpdate = fieldCateDao.updateStatusFieldCate(id_fieldCate, status);
                        if (checkUpdate) {
                            url = SUCCESS;
                            request.setAttribute("UPDATE_SUCCESS", "Update field category success!");
                        } else {
                            request.setAttribute("UPDATE_UNSUCCESS", "Update field category unsuccess! Please try again!");
                        }
                    }
                } else {
                    request.setAttribute("UPDATE_UNSUCCESS", "Status was already \"" + fieldCate.getStatus() + "\"");
                }
            }

        } catch (Exception e) {
            log("Error at UpdateFieldCateByAdminController: " + e.toString());
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
