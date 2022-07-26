package dao;

import dto.Feedback;
import dto.Field;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class FeedbackDAO {

    private static final String PRINT_ALL_FEEDBACK = "SELECT feedbackId, content, userId, fieldId, status FROM tblFeedback ORDER BY feedbackId OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
    private static final String PRINT_ALL_FEEDBACK_BY_FIELD_OWNER = "SELECT feedbackId, content, fe.userId, fe.fieldId, fe.status FROM tblFeedback fe LEFT JOIN tblFields f ON f.fieldId = fe.fieldId LEFT JOIN tblUsers us ON f.userId = us.userId WHERE us.userId = ? ORDER BY feedbackId OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
    private static final String PRINT_ALL_FEEDBACK_BY_ID = "SELECT feedbackId, content, userId, fieldId, status FROM tblFeedback WHERE feedbackId = ?";
    private static final String PRINT_ALL_FEEDBACK_BY_USER = "SELECT feedbackId, content, userId, fieldId, status FROM tblFeedback WHERE userId = ? ORDER BY feedbackId OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
    private static final String PRINT_ALL_FEEDBACK_BY_FIELD = "SELECT feedbackId, content, userId, fieldId, status FROM tblFeedback WHERE fieldId = ? AND status <> 'false' ORDER BY feedbackId OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";

    private static final String CHECK_FEED_BACK_ID = "SELECT feedbackId FROM tblFeedback WHERE feedbackId = ?";
    private static final String CHECK_CAN_FEED_BACK = "SELECT bo.userId FROM tblBooking bo LEFT JOIN tblBookingDetail bd ON bo.bookingId = bd.bookingId WHERE bo.userId = ? AND bd.fieldId = ?";
    private static final String CREATE_FEED_BACK = "INSERT INTO tblFeedback(feedbackId, content, userId, fieldId) VALUES(?, ?, ?, ?)";

    private static final String COUNT_ALL_FEEDBACK = "SELECT COUNT(*) as totalFeedback FROM tblFeedback";
    private static final String COUNT_ALL_FEEDBACK_BY_OWNER = "SELECT COUNT(*) as totalFeedback FROM tblFeedback fe LEFT JOIN tblFields f ON f.fieldId = fe.fieldId LEFT JOIN tblUsers us ON f.userId = us.userId WHERE us.userId = ?";
    private static final String COUNT_ALL_FEEDBACK_BY_USER = "SELECT COUNT(*) as totalFeedback FROM tblFeedback WHERE userId = ?";

    private static final String UPDATE_STATUS_FEEDBACK = "UPDATE tblFeedback SET [status] = ? WHERE feedbackId = ?";
    private static final String DELETE_FEEDBACK = "UPDATE tblFeedback SET [status] = 'false' WHERE feedbackId = ?";
    private static final String SEARCH_FEEDBACK_BY_FIELD_NAME = "SELECT feedbackId, content, fe.userId, fe.fieldId, fe.status FROM tblFeedback fe LEFT JOIN tblFields f ON f.fieldId = fe.fieldId LEFT JOIN tblUsers us ON f.userId = us.userId WHERE us.userId = ? AND f.fieldName like ? AND fe.status like ?";
    private static final String SEARCH_FEEDBACK_CUSTOMER_BY_FIELD_NAME = "SELECT feedbackId, content, fe.userId, fe.fieldId, fe.status FROM tblFeedback fe LEFT JOIN tblFields f ON f.fieldId = fe.fieldId LEFT JOIN tblUsers us ON fe.userId = us.userId WHERE us.userId = ? AND f.fieldName like ? AND fe.status like ?";

    public List<Feedback> getAllFeedback(int index) throws SQLException {
        List<Feedback> listFeedback = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRINT_ALL_FEEDBACK);
                ptm.setInt(1, (index - 1) * 5);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("feedbackId");
                    String content = rs.getString("content");
                    String id_of_user = rs.getString("userId");
                    UserDAO user = new UserDAO();
                    User userID = user.getUserByID(id_of_user);
                    String id_of_field = rs.getString("fieldId");
                    FieldDAO field = new FieldDAO();
                    Field fieldID = field.getFieldByID(id_of_field);
                    String status = rs.getString("status");
                    String statusAfter = changeNumberStatus(status);
                    listFeedback.add(new Feedback(feedbackId, content, userID, fieldID, statusAfter));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listFeedback;
    }
    
    public List<Feedback> getAllFeedbackByFieldOwner(String idUser, int index) throws SQLException {
        List<Feedback> listFeedback = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRINT_ALL_FEEDBACK_BY_FIELD_OWNER);
                ptm.setString(1, idUser);
                ptm.setInt(2, (index - 1) * 5);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("feedbackId");
                    String content = rs.getString("content");
                    String id_of_user = rs.getString("userId");
                    UserDAO user = new UserDAO();
                    User userID = user.getUserByID(id_of_user);
                    String id_of_field = rs.getString("fieldId");
                    FieldDAO field = new FieldDAO();
                    Field fieldID = field.getFieldByID(id_of_field);
                    String status = rs.getString("status");
                    String statusAfter = changeNumberStatus(status);
                    listFeedback.add(new Feedback(feedbackId, content, userID, fieldID, statusAfter));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listFeedback;
    }

    public Feedback getFeedbackByID(String feedbackId) throws SQLException {
        Feedback feedback = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRINT_ALL_FEEDBACK_BY_ID);
                ptm.setString(1, feedbackId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String content = rs.getString("content");
                    String id_of_user = rs.getString("userId");
                    UserDAO user = new UserDAO();
                    User userID = user.getUserByID(id_of_user);
                    String id_of_field = rs.getString("fieldId");
                    FieldDAO field = new FieldDAO();
                    Field fieldID = field.getFieldByID(id_of_field);
                    String status = rs.getString("status");
                    feedback = new Feedback(feedbackId, content, userID, fieldID, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return feedback;
    }

    public List<Feedback> getAllFeedbackByFieldId(String fieldId, int index) throws SQLException {
        List<Feedback> listFeedback = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRINT_ALL_FEEDBACK_BY_FIELD);
                ptm.setString(1, fieldId);
                ptm.setInt(2, (index - 1) * 3);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("feedbackId");
                    String content = rs.getString("content");
                    String id_of_user = rs.getString("userId");
                    UserDAO user = new UserDAO();
                    User userID = user.getUserByID(id_of_user);
                    String id_of_field = rs.getString("fieldId");
                    FieldDAO field = new FieldDAO();
                    Field fieldID = field.getFieldByID(id_of_field);
                    String status = rs.getString("status");
                    listFeedback.add(new Feedback(feedbackId, content, userID, fieldID, status));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listFeedback;
    }

    public List<Feedback> getFeedbackByUserId(int index, String userId) throws SQLException {
        List<Feedback> listFeedback = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRINT_ALL_FEEDBACK_BY_USER);
                ptm.setString(1, userId);
                ptm.setInt(2, (index - 1) * 5);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String feedbackId = rs.getString("feedbackId");
                    String content = rs.getString("content");
                    String id_of_user = rs.getString("userId");
                    UserDAO user = new UserDAO();
                    User userID = user.getUserByID(id_of_user);
                    String id_of_field = rs.getString("fieldId");
                    FieldDAO field = new FieldDAO();
                    Field fieldID = field.getFieldByID(id_of_field);
                    String status = rs.getString("status");
                    String statusAfter = changeNumberStatus(status);
                    listFeedback.add(new Feedback(feedbackId, content, userID, fieldID, statusAfter));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listFeedback;
    }

    public int countTotalFeedback() throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_FEEDBACK);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    index = rs.getInt("totalFeedback");
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return index;
    }

    public int countTotalFeedbackByOwner(String userId) throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_FEEDBACK_BY_OWNER);
                ptm.setString(1, userId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    index = rs.getInt("totalFeedback");
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return index;
    }
    
    public int countTotalFeedbackByUser(String userId) throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_FEEDBACK_BY_USER);
                ptm.setString(1, userId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    index = rs.getInt("totalFeedback");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return index;
    }

    public boolean checkFeedbackId(String feedbackId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_FEED_BACK_ID);
                ptm.setString(1, feedbackId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public String handleFeedbackId() {
        int max = 999999;
        int min = 1;
        int random_double = (int) (Math.random() * (max - min + 1) + min);
        String s = String.valueOf(random_double);
        return "FB" + s;
    }

    public String createFeedbackId() throws SQLException {
        String feedbackId = handleFeedbackId();
        boolean check = false;
        do {
            check = checkFeedbackId(feedbackId);
            if (check == false) {
                feedbackId = handleFeedbackId();
            }
        } while (check);
        return feedbackId;
    }

    public boolean createFeedback(Feedback feedback) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_FEED_BACK);
                ptm.setString(1, feedback.getFeedbackId());
                ptm.setString(2, feedback.getContent());
                ptm.setString(3, feedback.getUser().getUserID());
                ptm.setString(4, feedback.getField().getFieldId());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public boolean checkCanFeedback(String userId, String fieldId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_CAN_FEED_BACK);
                ptm.setString(1, userId);
                ptm.setString(2, fieldId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateStatusFeedback(String feedbackId, String status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STATUS_FEEDBACK);
                ptm.setString(1, status);
                ptm.setString(2, feedbackId);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean deleteFeedback(String feedbackId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_FEEDBACK);
                ptm.setString(1, feedbackId);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<Feedback> searchFeedbackByOwner(String search, String feedbackStatus, String userId) throws SQLException {
        List<Feedback> listFeedback = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                    ptm = conn.prepareStatement(SEARCH_FEEDBACK_BY_FIELD_NAME);
                    ptm.setString(1, userId);
                    ptm.setString(2, "%" + search + "%");
                    ptm.setString(3, "%" + feedbackStatus + "%");
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        String feedbackId = rs.getString("feedbackId");
                        String content = rs.getString("content");
                        String id_of_user = rs.getString("userId");
                        UserDAO user = new UserDAO();
                        User userID = user.getUserByID(id_of_user);
                        String id_of_field = rs.getString("fieldId");
                        FieldDAO field = new FieldDAO();
                        Field fieldID = field.getFieldByID(id_of_field);
                        String status = rs.getString("status");
                        String statusAfter = changeNumberStatus(status);
                        listFeedback.add(new Feedback(feedbackId, content, userID, fieldID, statusAfter));
                    }
                } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listFeedback;
    }
    
    public List<Feedback> searchFeedbackByCustomer(String search, String feedbackStatus, String userId) throws SQLException {
        List<Feedback> listFeedback = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                    ptm = conn.prepareStatement(SEARCH_FEEDBACK_CUSTOMER_BY_FIELD_NAME);
                    ptm.setString(1, userId);
                    ptm.setString(2, "%" + search + "%");
                    ptm.setString(3, "%" + feedbackStatus + "%");
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        String feedbackId = rs.getString("feedbackId");
                        String content = rs.getString("content");
                        String id_of_user = rs.getString("userId");
                        UserDAO user = new UserDAO();
                        User userID = user.getUserByID(id_of_user);
                        String id_of_field = rs.getString("fieldId");
                        FieldDAO field = new FieldDAO();
                        Field fieldID = field.getFieldByID(id_of_field);
                        String status = rs.getString("status");
                        String statusAfter = changeNumberStatus(status);
                        listFeedback.add(new Feedback(feedbackId, content, userID, fieldID, statusAfter));
                    }
                } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listFeedback;
    }

    public String changeNumberStatus(String status) {
        if (status.equals("1")) {
            status = "Active";
        } else {
            status = "In-active";
        }
        return status;
    }

    public String changeStringStatus(String status) {
        if (status.equals("Active")) {
            status = "1";
        } else {
            status = "0";
        }
        return status;
    }

}
