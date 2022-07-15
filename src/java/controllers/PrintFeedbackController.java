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

public class PrintFeedbackController extends HttpServlet {

    private static final String USER_PAGE = "userFeedbackManagement.jsp";
    private static final String OWNER_PAGE = "ownerFeedbackManagement.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = USER_PAGE;
        try {
            int count = 0;
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            request.setAttribute("USER_NAME", user.getFullName());

            FeedbackDAO daoFeedback = new FeedbackDAO();
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int endPage = 0;
            if (user.getRole().getRoleId().equals("US")) {
                String userId = user.getUserID();
                List<Feedback> listFeedback = daoFeedback.getFeedbackByUserId(index, userId);
                if (listFeedback.size() > 0) {
                    request.setAttribute("LIST_FEED_BACK", listFeedback);
                    url = USER_PAGE;
                }
                count = daoFeedback.countTotalFeedbackByUser(userId);
                endPage = count / 5;
                if (count % 5 != 0) {
                    endPage++;
                }
                request.setAttribute("END_PAGE", endPage);
            } else if (user.getRole().getRoleId().equals("MA")) {
                List<Feedback> listFeedback = daoFeedback.getAllFeedback(index);
                if (listFeedback.size() > 0) {
                    request.setAttribute("LIST_FEED_BACK", listFeedback);
                }
                count = daoFeedback.countTotalFeedback();
                endPage = count / 5;
                if (count % 5 != 0) {
                    endPage++;
                }
                request.setAttribute("END_PAGE", endPage);
                url = OWNER_PAGE;
                session.setAttribute("ACTION_FEEDBACK", "Print");
            }
        } catch (Exception e) {
            log("Error at PrintFeedbackController: " + e.toString());
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
