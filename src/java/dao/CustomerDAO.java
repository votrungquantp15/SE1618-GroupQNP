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

}
