package controllers;

import dao.FeedbackDAO;
import dao.FieldDAO;
import dto.Feedback;
import dto.Field;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserPrintFieldDetailController extends HttpServlet {

    private static final String ERROR = "homeDetail.jsp";
    private static final String SUCCESS = "homeDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String id_of_field = request.getParameter("fieldId");
            FieldDAO daoField = new FieldDAO();
            Field fieldDetail = daoField.getUserFieldDetailByID(id_of_field);
            String fieldId = fieldDetail.getFieldId();
            request.setAttribute("FIELD_DETAIL", fieldDetail);

            FeedbackDAO daoFeedback = new FeedbackDAO();

            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int endPage = 0;
            int count = daoFeedback.countTotalFeedback();

            List<Feedback> listFeedback = daoFeedback.getAllFeedbackByFieldId(fieldId, index);
            if (listFeedback.size() > 0) {
                request.setAttribute("LIST_FEEDBACK", listFeedback);
            }
            url = SUCCESS;
            endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            request.setAttribute("END_PAGE", endPage);
        } catch (Exception e) {
            log("Error at UserPrintFieldDetailController: " + e.toString());
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
