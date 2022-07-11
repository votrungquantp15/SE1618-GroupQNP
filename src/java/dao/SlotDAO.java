/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Slot;
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
public class SlotDAO {

    private static final String GET_ALL_INFO = "SELECT slotID, timeStart, timeEnd, status FROM tblSlots WHERE slotID like ?";
    
    private static final String COUNT_ALL_SLOT = "SELECT COUNT(*) as totalSlot FROM tblSlots WHERE slotID like ? ";

    private static final String PAGING_LIST_BOOKING = "SELECT * FROM tblSlots WHERE slotID like ? ORDER BY slotID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY ";
    
    public Slot getSlotByID(String slotID) throws SQLException {
        Slot slot = new Slot();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_INFO);
                ptm.setString(1, slotID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String getSlotID = rs.getString("slotID");
                    String timeStart = rs.getString("timeStart");
                    String timeEnd = rs.getString("timeEnd");
                    String status = rs.getString("status");
                    slot = new Slot(getSlotID, timeStart, timeEnd, status);
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
        return slot;
    }

    public List<Slot> getListSlotByID(String search, int index) throws SQLException {
        List<Slot> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PAGING_LIST_BOOKING);
                ptm.setString(1, "%" + search + "%");
                ptm.setInt(2, (index - 1) * 12);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getSlotID = rs.getString("slotID");
                    String timeStart = rs.getString("timeStart");
                    String timeEnd = rs.getString("timeEnd");
                    String status = rs.getString("status");
                    if (status.equals("1")) {
                        status = "True";
                    } else {
                        status = "False";
                    }
                    list.add(new Slot(getSlotID, timeStart, timeEnd, status));
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

    public int getTotalSlot(String search) throws SQLException {
        int index = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_SLOT);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                if(rs.next()){
                    index = rs.getInt("totalSlot");
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
    
}
