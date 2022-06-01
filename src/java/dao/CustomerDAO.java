/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author votru
 */
public class CustomerDAO {
    public boolean checkDuplicate(String userID) throws SQLException{
        boolean check=false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs =null;
        try {
            conn = DBUtils.getConnection();
            String sql="select userID from tblUsers where userID=?";
            stm= conn.prepareStatement(sql);
            stm.setString(1, userID);
            rs=stm.executeQuery();
            if(rs.next()){
                check = true;
            }
        } catch (Exception e){
        }finally{
            if(rs!=null) rs.close();
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();
        }    
        return check;
    }
    
    public boolean insert(UserDTO cus) throws SQLException{
        boolean check = false;
        Connection conn= null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            String sql="insert into tblUsers(userID, fullName, password, accName, address, birthday, phone, email, roleID, status) values(?,?,?,?,?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, cus.getUserID());
            stm.setString(2, cus.getFullName());
            stm.setString(3, cus.getPassword());
            stm.setString(4, cus.getAccName());
            stm.setString(5, cus.getAddress());
            stm.setString(6, cus.getBirth());
            stm.setString(7, cus.getPhone());
            stm.setString(8, cus.getEmail());
            stm.setString(9, cus.getRoleID());
            stm.setString(10, cus.getStatus());
            check = stm.executeUpdate()>0;
        } catch (Exception e) {
        }finally{
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();
        }    
        return check;
    }
    
    public UserDTO checkLogin(String email, String password) throws SQLException{
        UserDTO customer = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs= null;
        try {
            conn = DBUtils.getConnection();
            String sql = "select userID, fullName, address,birthday, phone, accName, roleID, status from tblUsers where email=? and password=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            rs=stm.executeQuery();
            if(rs.next()){
                String userID = rs.getString("UserID");
                String fullName = rs.getString("fullName");
                String roleID = rs.getString("roleID");
                String address = rs.getString("address");
                String accName = rs.getString("accName");
                String phone = rs.getString("phone");
                String status = rs.getString("status");
                String birthday = rs.getString("birthday");
                customer = new UserDTO(userID, fullName, address, birthday, phone, email, accName, "", roleID, status);
            }
        } catch (Exception e) {
        }finally{
            if(rs!=null) rs.close();
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();     
        }
        return customer;
        
    }
    
    public boolean checkPass(String email, String password) throws SQLException{
        boolean check =false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "select email, password from tblUsers where email=? and password=?";
            stm=conn.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            rs =stm.executeQuery();
            if(rs.next()){
                check=true;
            }         
        } catch (Exception e) {
        }finally{
           if(rs!=null) rs.close();
           if(stm!=null) stm.close();
           if(conn!=null) conn.close();
        }
        return check;
    }
    
    public boolean UpdatePass(String email, String oldPass, String newPass) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn=DBUtils.getConnection();
            String sql ="update tblUsers set password=? where email=? and password=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, newPass);
            stm.setString(2, email);
            stm.setString(3, oldPass);
            check = stm.executeUpdate() > 0;
        } catch (Exception e) {
        }finally{
           if(stm!=null) stm.close();
           if(conn!=null) conn.close();
        }
        return check;
    }
    public  String handleUserID () {
        int max = 999999;
        int min = 1;
        int random_double = (int) (Math.random() * (max - min + 1) + min); 
        
        String s=String.valueOf(random_double);
        return "CU"+ s;
    }
    
    public List<UserDTO> SearchAccountForAdmin() throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT userID, fullName, address, birthday, phone, email, accName, password, roleID, status FROM tblUsers";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String address = rs.getString("address");
                    String birthday = rs.getString("birthday");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String accName = rs.getString("accName");
                    String password = rs.getString("password");
                    String status = rs.getString("status");
                    String roleID = rs.getString("roleID");
                    list.add(new UserDTO(userID, fullName, address, birthday, phone, email, accName, password, status, roleID));
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
        return list;
    }
    
    public boolean deleteUser(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblUsers SET status = 0 WHERE userID = ?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public boolean update(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblUsers SET fullName = ?, address = ?, birthday = ?, phone = ?, email = ?, status = ?  WHERE userID = ?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getAddress());
                ptm.setString(3, user.getBirth());
                ptm.setString(3, user.getPhone());
                ptm.setString(3, user.getEmail());
                ptm.setString(3, user.getStatus());
                
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
}
