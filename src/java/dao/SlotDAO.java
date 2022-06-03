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
import utils.DBUtils;

/**
 *
 * @author NITRO 5
 */
public class SlotDAO {

    private static final String GET_ALL_INFO = "SELECT slotID, timeStart, timeEnd, status FROM tblSlot WHERE slotID like ?";

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
                    slot= new Slot(getSlotID, timeStart, timeEnd, status);
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
}
