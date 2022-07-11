package controllers;

import dao.DistrictDAO;
import dto.District;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrintDistrictController extends HttpServlet {

    private static final String ERROR = "districtManagement.jsp";
    private static final String ADMIN_PAGE = "districtManagement.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            DistrictDAO districtDao = new DistrictDAO();
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int endPage = 0;
            int count = districtDao.countTotalDistrict();
            List<District> listDistrict = districtDao.getAllDistrictPaging(index);
            if (listDistrict.size() > 0) {
                request.setAttribute("LIST_DISTRICT", listDistrict);
                    url = ADMIN_PAGE;
            }
            endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            request.setAttribute("END_PAGE", endPage);
        } catch (Exception e) {
            log("Error at PrintDistrictController: " + e.toString());
        } finally {
            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception e) {
                log("Error at PrintDistrictController: " + e.toString());
            }
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
