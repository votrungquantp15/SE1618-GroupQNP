package controllers;

import dao.DistrictDAO;
import dao.FieldDAO;
import dto.District;
import dto.Field;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeShowFieldController extends HttpServlet {

    private static final String HOME_PAGE = "home.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = HOME_PAGE;
        try {
            int count = 0;
            HttpSession session = request.getSession();

            DistrictDAO districtDao = new DistrictDAO();
            List<District> listDistrict = districtDao.getAllDistrict();
            request.setAttribute("LIST_DISTRICT", listDistrict);

            FieldDAO fieldDao = new FieldDAO();
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int endPage = 0;

            List<Field> listField = fieldDao.getListFieldByUser(index);
            if (listField.size() > 0) {
                request.setAttribute("FIELD", listField);
            }
            count = fieldDao.countTotalFieldbyUser();
            endPage = count / 9;
            if (count % 9 != 0) {
                endPage++;
            }
            request.setAttribute("END_PAGE", endPage);
            url = HOME_PAGE;
            session.setAttribute("ACTION_FIELD", "Print");
        } catch (Exception e) {
            log("Error at HomeShowFieldController: " + e.toString());
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
