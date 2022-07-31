package controllers;

import dao.FeedbackDAO;
import dto.Feedback;
import dto.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchFeedbackController extends HttpServlet {

    private static final String OWNER_PAGE = "ownerFeedbackManagement.jsp";
    private static final String USER_PAGE = "userFeedbackManagement.jsp";
    private static final String ERROR = "ownerFeedbackManagement.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            String search = request.getParameter("searchFeedback");
            String status = request.getParameter("status");
            FeedbackDAO feedbackDao = new FeedbackDAO();
            List<Feedback> listFeedback = null;
            String userId = user.getUserID();
            if (user.getRole().getRoleId().equals("MA")) {
                listFeedback = feedbackDao.searchFeedbackByOwner(search, status, userId);
                url = OWNER_PAGE;
                if (!listFeedback.isEmpty()) {
                    request.setAttribute("LIST_FEED_BACK", listFeedback);
                } else {
                    request.setAttribute("SEARCH_FEEDBACK_ERROR", "Couldn't find any feedback");
                }
            } else if (user.getRole().getRoleId().equals("US")) {
                listFeedback = feedbackDao.searchFeedbackByCustomer(search, userId);
                url = USER_PAGE;
                if (!listFeedback.isEmpty()) {
                    request.setAttribute("LIST_FEED_BACK", listFeedback);
                } else {
                    request.setAttribute("SEARCH_FEEDBACK_ERROR", "Couldn't find any feedback");
                }
            }
        } catch (Exception e) {
            log("Error at SearchFieldByAdminController: " + e.toString());
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
