/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Field;
import dto.Slot;
import dto.SlotDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author NITRO 5
 */
public class SlotDetailDAO {
    private static final String GET_ALL_INFO = "SELECT slotDetailID, slotID, fieldID, status "
            + "FROM tblSlotDetail WHERE slotDetailID like ? ";
    
    public SlotDetail getSlotDetailByID(String slotDetailID) throws SQLException{
        SlotDetail slotDetail = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_INFO);
                ptm.setString(1, slotDetailID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getSlotDetailID = rs.getString("slotDetailID");
                    
                    String slotID = rs.getString("slotID");
                    SlotDAO slotDAO = new SlotDAO();
                    Slot slot = slotDAO.getSlotByID(slotID);
                    
                    String fieldID = rs.getString("fieldID");
                    FieldDAO fieldDAO = new FieldDAO();
                    Field field = fieldDAO.getFieldByID(fieldID);
                    
                    String status = rs.getString("status");

                    slotDetail = new SlotDetail(getSlotDetailID, slot, field, status);
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
        return slotDetail;
    }
    
    public List<SlotDetail> getListSlotDetailByID(String search) throws SQLException{
        List<SlotDetail> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_INFO);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getSlotDetailID = rs.getString("slotDetailID");
                    
                    String slotID = rs.getString("slotID");
                    SlotDAO slotDAO = new SlotDAO();
                    Slot slot = slotDAO.getSlotByID(slotID);
                    
                    String fieldID = rs.getString("fieldID");
                    FieldDAO fieldDAO = new FieldDAO();
                    Field field = fieldDAO.getFieldByID(fieldID);
                    
                    String status = rs.getString("status");

                    list.add(new SlotDetail(getSlotDetailID, slot, field, status));
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
}
