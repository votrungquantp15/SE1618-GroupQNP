package controllers;

import dao.DistrictDAO;
import dao.FieldCategoryDAO;
import dao.FieldDAO;
import dao.LocationDAO;
import dto.District;
import dto.Field;
import dto.FieldCategory;
import dto.Location;
import dto.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrintFieldController extends HttpServlet {

    private static final String USER_PAGE = "home.jsp";
    private static final String ADMIN_PAGE = "fieldManagement.jsp";
    private static final String OWNER_PAGE = "ownerFieldManagement.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = USER_PAGE;
        try {
            int count = 0;
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER");
            request.setAttribute("USER_NAME", user.getFullName());

            FieldCategoryDAO cateDao = new FieldCategoryDAO();
            List<FieldCategory> listCate = cateDao.getAllFieldCategory();
            request.setAttribute("LIST_CATEGORY", listCate);

            DistrictDAO districtDao = new DistrictDAO();
            List<District> listDistrict = districtDao.getAllDistrict();
            request.setAttribute("LIST_DISTRICT", listDistrict);

            LocationDAO locationDao = new LocationDAO();
            List<Location> listLocation = locationDao.getAllLocation();
            request.setAttribute("LIST_LOCATION", listLocation);

            FieldDAO fieldDao = new FieldDAO();
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int endPage = 0;
                    
            if (user.getRole().getRoleId().equals("MA")) {
                String userId = user.getUserID();
                count = fieldDao.countTotalFieldbyFieldOwner(userId);
                List<Field> listField = fieldDao.getListOwnerFieldPaging(index, userId);
                if (listField.size() > 0) {
                    request.setAttribute("LIST_FIELD", listField);
                    
                }
                endPage = count / 5;
                if (count % 5 != 0) {
                    endPage++;
                }
                url = OWNER_PAGE;
                request.setAttribute("END_PAGE", endPage);
            } else {
                    if (user.getRole().getRoleId().equals("US")) {
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
                        url = USER_PAGE;
                    } else if (user.getRole().getRoleId().equals("AD")) {
                        List<Field> listField = fieldDao.getListField(index);
                        if (listField.size() > 0) {
                        request.setAttribute("LIST_FIELD", listField);
                        }
                        count = fieldDao.countTotalFieldByAdmin();
                        endPage = count / 5;
                        if (count % 5 != 0) {
                            endPage++;
                        }
                        request.setAttribute("END_PAGE", endPage);
                        url = ADMIN_PAGE;
                    }
            }
            session.setAttribute("ACTION_FIELD", "Print");
        } catch (Exception e) {
            log("Error at PrintFieldController: " + e.toString());
        } finally {
            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception e) {
                log("Error at PrintFieldController: " + e.toString());
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
