package controllers;

import dao.DistrictDAO;
import dao.FieldDAO;
import dto.District;
import dto.Field;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchFieldByUserController extends HttpServlet {

    private static final String SEARCH_SUCCES = "home.jsp";
    private static final String SEARCH_ERROR = "home.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SEARCH_ERROR;
        try {
            HttpSession session = request.getSession();
            FieldDAO fieldDao = new FieldDAO();
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            String districtId = request.getParameter("districtId");
            String fieldName = request.getParameter("fieldName");
            List<Field> listFields = new ArrayList<>();
            listFields = fieldDao.searchUserFieldDetailByName(fieldName, districtId, index);
            
            int count = fieldDao.countSearchTotalFieldByUser(fieldName, districtId);
            int endPage = 0;
            endPage = count / 9;
            if (count % 9 != 0) {
                endPage++;
            }
            
            DistrictDAO districtDao = new DistrictDAO();
            District district = districtDao.getDistrictByID(districtId);
            List<District> listDistrict = districtDao.getAllDistrict();
            request.setAttribute("DISTRICT", district);
            request.setAttribute("LIST_DISTRICT", listDistrict);

            if (listFields.size() != 0) {
                //setAttribute Fields
                url = SEARCH_SUCCES;

                request.setAttribute("FIELD", listFields);
            } else {
                request.setAttribute("FIELD_NOT_FOUND", "Không tìm thấy sân bóng");
            }
            request.setAttribute("END_PAGE", endPage);
            request.setAttribute("FIELD_NAME", fieldName);
            request.setAttribute("DISTRICT_ID", districtId);
            session.setAttribute("ACTION_FIELD", "Search");
        } catch (Exception e) {
            log("Error at SearchFieldByUserController: " + e.toString());
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
