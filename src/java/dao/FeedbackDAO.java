/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author ROG STRIX
 */
public class FeedbackDAO {
    private static final String CHECK_FEEDBACK_ID = "SELECT feedbackId from Feedback where feedbackId like ?";
    private static final String INSERT_FEEDBACK = "INSERT INTO tblFeedback(feedbackId, title, content, userId, fieldId, status) VALUES(?,?,?,?,?,?) ";
    private static final String PRINT_ALL_FEEDBACK = "SELECT feedbackId, title, content, userId, fieldId, status FROM tblFeedback";
    private static final String SEARCH_FEEDBACK_BY_ADMIN = "SELECT feedbackId, title, content, us.userId, fi.fieldId, f.status" +
"   FROM tblFeedback f left join tblUsers us  on f.userId = us.userId left join tblFields fi on f.fieldId = fi.fieldId " +
"   where us.fullName like ? AND fi.fieldName like ?";
    
    public boolean checkFeedbackId(String feedbackID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_FEEDBACK_ID);
                ptm.setString(1, feedbackID);
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
    
    public String randomFeedbackId() {
        int max = 999999;
        int min = 1;
        int random_double = (int) (Math.random() * (max - min + 1) + min);
        String s = String.valueOf(random_double);
        return "FB" + s;
    }

    public String createFeedbackId() throws SQLException {
        String feedbackID = randomFeedbackId();
        boolean check = false;
        do {
            check = checkFeedbackId(feedbackID);
            if (check == false) {
                feedbackID = randomFeedbackId();
            }
        } while (check);
        return feedbackID;
    }
    
    public boolean insertFeedback(Feedback create) throws SQLException{
        boolean check = false;
        Connection conn=null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(INSERT_FEEDBACK);
            ptm.setString(1, create.getFeedbackId());
            ptm.setString(2, create.getTitle());
            ptm.setString(3, create.getContent());
            ptm.setString(4, create.getUser().getUserID());
            ptm.setString(5, create.getField().getFieldId());
            ptm.setString(6, create.getStatus());
            check = ptm.executeUpdate()>0;           
        } catch (Exception e) {
        }finally{
            if(ptm!= null) ptm.close();
            if(conn!= null) conn.close();
        }
        
        return check;
    }
    
    public List<Feedback> getListFeedback() throws SQLException{
        List<Feedback> listFeedback = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(PRINT_ALL_FEEDBACK);
            rs=ptm.executeQuery();
            while(rs.next()){
                String feedbackID = rs.getString("feedbackId");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String userID = rs.getString("userId");
                UserDAO userDao = new UserDAO();
                User user = userDao.getUserByID(userID);
                String fieldID = rs.getString("fieldId");
                FieldDAO fieldDao = new FieldDAO();
                Field field = fieldDao.getFieldByID(fieldID);
                String status = rs.getString("status");
//                Feedback feeback = new Feedback(feedbackID, title, content, user, field, status);
                Feedback fb = new Feedback(feedbackID, title, content, user, field, status);
                listFeedback.add(fb);               
            }
        } catch (Exception e) {
        }finally{
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
    
    public List<Feedback> searchFeedback(String fieldName, String userName) throws SQLException{
        List<Feedback> list = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(userName);
        } catch (Exception e) {
        }finally{
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
        
        return list;
        
    }
    
    public List<Feedback> searchFBByAdmin(String userName, String fieldName) throws SQLException{
        List<Feedback> listFeedback = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(SEARCH_FEEDBACK_BY_ADMIN);
            ptm.setString(1,"%" + userName + "%");
            ptm.setString(2, "%" + fieldName + "%");
            rs = ptm.executeQuery();
            while(rs.next()){
                String FeddbackID = rs.getString("feedbackId");
                String Title = rs.getString("title");
                String content = rs.getString("content");
                String userID = rs.getString("userId");
                UserDAO userDao = new UserDAO();
                User user = userDao.getUserByID(userID);
                String FieldID = rs.getString("fieldId");
                FieldDAO fieldDao = new FieldDAO();
                Field field = fieldDao.getFieldByID(FieldID);
                String status = rs.getString("status");
                listFeedback.add(new Feedback(FeddbackID, Title, content, user, field, status));
            
            }
            
        } catch (Exception e) {
        }finally{
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
    
}
