package controllers;

import dao.LocationDAO;
import dto.Location;
import dto.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrintLocationController extends HttpServlet {

    private static final String ERROR = "locationManagement.jsp";
    private static final String ADMIN_PAGE = "locationManagement.jsp";
    private static final String OWNER_PAGE = "ownerLocationManagement.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("LOGIN_USER");
            LocationDAO locationDao = new LocationDAO();
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int endPage = 0;
            int count = locationDao.countTotalLocation();
            List<Location> listLocation = locationDao.getAllLocationPaging(index);
            if (listLocation.size() > 0) {
                request.setAttribute("LIST_LOCATION", listLocation);
                if (user.getRole().getRoleId().equals("MA")) {
                    url = OWNER_PAGE;
                } else if (user.getRole().getRoleId().equals("AD")) {
                    url = ADMIN_PAGE;
                }
            }
            endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            request.setAttribute("END_PAGE", endPage);
        } catch (Exception e) {
            log("Error at PrintLocationController: " + e.toString());
        } finally {
            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception e) {
                log("Error at PrintLocationController: " + e.toString());
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
