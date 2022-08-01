package dao;

import dto.Slot;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class SlotDAO {

    private static final String GET_ALL_INFO = "SELECT slotID, timeStart, timeEnd, status FROM tblSlots WHERE slotID like ?";
    
    private static final String GET_LIST_SLOT = "SELECT slotID, timeStart, timeEnd, status FROM tblSlots";
    private static final String GET_LIST_SLOT_BY_FIELD = "SELECT * FROM tblSlots sl LEFT JOIN tblSlotDetail sd ON sl.slotId = sd.slotId WHERE fieldId = ?";
    
    private static final String COUNT_ALL_SLOT = "SELECT COUNT(*) as totalSlot FROM tblSlots WHERE slotID like ?";
    private static final String COUNT_ALL_SLOT_BY_FIELD = "SELECT COUNT(*) as totalSlot FROM tblSlotDetail WHERE fieldId = ?";
    
    private static final String GET_SLOT_BY_FIELD = "SELECT sl.slotID, timeStart, timeEnd, sd.status FROM tblSlots sl LEFT JOIN tblSlotDetail sd ON sl.slotId = sd.slotId WHERE sd.fieldId = ? ORDER BY sl.slotId OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";


    private static final String PAGING_LIST_BOOKING = "SELECT * FROM tblSlots WHERE slotID like ? ORDER BY slotID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY ";
    
    private static final String CHECK_CAN_UPDATE_SLOT = "SELECT sl.slotId FROM tblBookingDetail bd LEFT JOIN tblSlotDetail sd ON bd.slotDetailId = sd.slotDetailId LEFT JOIN tblSlots sl ON sd.slotId = sl.slotId WHERE sl.slotId = ?";
    private static final String UPDATE_STATUS_SLOT = "UPDATE tblSlotDetail SET [status] = ? WHERE slotId = ? AND fieldId = ?";
    
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
    
    public List<Slot> getAllSlot() throws SQLException {
        List<Slot> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_SLOT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getSlotID = rs.getString("slotID");
                    String timeStart = rs.getString("timeStart");
                    String timeEnd = rs.getString("timeEnd");
                    String status = rs.getString("status");
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
    
    public List<Slot> getAllSlotByFieldId(String fieldId) throws SQLException {
        List<Slot> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_SLOT_BY_FIELD);
                ptm.setString(1, fieldId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getSlotID = rs.getString("slotID");
                    String timeStart = rs.getString("timeStart");
                    String timeEnd = rs.getString("timeEnd");
                    String status = rs.getString("status");
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
    
    public List<Slot> getSlotByFieldPaging(String fieldId, int index) throws SQLException {
        List<Slot> listSlot = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SLOT_BY_FIELD);
                ptm.setString(1, fieldId);
                ptm.setInt(2, (index - 1) * 5);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String slotID = rs.getString("slotID");
                    String timeStart = rs.getString("timeStart");
                    String timeEnd = rs.getString("timeEnd");
                    String status = rs.getString("status");
                    String statusAfter = changeNumberStatus(status);
                    listSlot.add(new Slot(slotID, timeStart, timeEnd, statusAfter));
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
        return listSlot;
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
    
    public boolean updateStatusSlot(String slotId, String fieldId, String status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STATUS_SLOT);
                ptm.setString(1, status);
                ptm.setString(2, slotId);
                ptm.setString(3, fieldId);
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
    
    public boolean checkCanUpdateSlot(String slotId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_CAN_UPDATE_SLOT);
                ptm.setString(1, slotId);
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
    
    public int countTotalSlotByField(String fieldId) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ALL_SLOT_BY_FIELD);
                ptm.setString(1, fieldId);
                rs = ptm.executeQuery();
                if(rs.next()){
                    count = rs.getInt("totalSlot");
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
        return count;
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
