package controllers;

import dao.FeedbackDAO;
import dao.FieldDAO;
import dto.Feedback;
import dto.Field;
import dto.User;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateFeedbackController extends HttpServlet {

    private static final String ERROR = "UserPrintFieldDetailController";
    private static final String SUCCESS = "UserPrintFieldDetailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            FeedbackDAO feedbackDao = new FeedbackDAO();
            String fieldCateId = feedbackDao.createFeedbackId();

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            String userId = user.getUserID();

            String content = request.getParameter("content");
            content = URLEncoder.encode(content, "ISO-8859-1");
            content = URLDecoder.decode(content, "UTF-8");

            String fieldId = request.getParameter("fieldId");
            FieldDAO fieldDao = new FieldDAO();
            Field field = fieldDao.getFieldByID(fieldId);

            boolean checkCanFeedback = false;
            boolean checkValidation = true;
            if (feedbackDao.checkCanFeedback(userId, fieldId)) {
                checkCanFeedback = true;
            }
            if (checkCanFeedback) {
                if (content.trim().length() == 0) {
                    request.setAttribute("CREATE_CONTENT_ERROR", "Nội dung không được để trống");
                    checkValidation = false;
                }
                if (content.length() > 500) {
                    request.setAttribute("CREATE_CONTENT_ERROR", "Nội dung không được dài quá 500 kí tự");
                    checkValidation = false;
                }
                if (checkValidation) {
                    Feedback feedback = new Feedback(fieldCateId, content, user, field, null);
                    boolean checkCreate = feedbackDao.createFeedback(feedback);
                    if (checkCreate) {
                        url = SUCCESS;
                        request.setAttribute("CREATE_SUCCESS", "Gửi đánh giá thành công");
                    }
                } else {
                    request.setAttribute("CREATE_UNSUCCESS", "Gửi đánh giá không thành công! Xin hãy thử lại");
                }
            } else {
                request.setAttribute("CREATE_UNSUCCESS", "Hãy đặt sân rồi mới có thể đánh giá!");
            }

        } catch (Exception e) {
            log("Error at CreateFeedbackController: " + e.toString());
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
