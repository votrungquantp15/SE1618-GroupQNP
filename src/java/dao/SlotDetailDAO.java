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

public class SlotDetailDAO {
    private static final String GET_ALL_INFO = "SELECT slotDetailID, slotID, fieldID, status "
            + "FROM tblSlotDetail WHERE fieldID = ? ";
    private static final String GET_ALL_INFO_BY_CUSTOMER = "SELECT slotDetailID, slotID, fieldID, status "
            + "FROM tblSlotDetail WHERE fieldID = ? AND status <> 'false'";
    private static final String GET_SLOT_DETAIL_BY_ID = "SELECT slotDetailID, slotID, fieldID, status "
            + "FROM tblSlotDetail WHERE slotDetailID = ? ";
    private static final String GET_SLOT_DETAIL_BY_SLOTID_FIELDID = "SELECT slotDetailID, slotID, fieldID, status "
            + "FROM tblSlotDetail WHERE slotID = ? AND fieldID = ?";
    private static final String GET_SLOT_BY_FIELD_ID = "SELECT slotDetailID, slotID, fieldID, status "
            + "FROM tblSlotDetail WHERE fieldId = ? ";
    
    private static final String GET_SLOT_DETAIL_ID = "SELECT slotDetailID "
            + "FROM tblSlotDetail WHERE slotDetailID = ? ";
    
    private static final String CHECK_SLOT_ID = "SELECT slotID "
            + "FROM tblSlotDetail WHERE slotID = ? AND fieldID = ?";
    
    private static final String CREATE_SLOT_DETAIL = "INSERT INTO tblSlotDetail(slotDetailID, slotID, fieldID) VALUES(?, ?, ?)";
    
    public SlotDetail getSlotDetailByID(String slotDetailID) throws SQLException{
        SlotDetail slotDetail = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SLOT_DETAIL_BY_ID);
                ptm.setString(1, slotDetailID);
                rs = ptm.executeQuery();
                if (rs.next()) {
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
    
    public SlotDetail getSlotDetailBySlotIdAndFieldId(String slotId, String fieldId) throws SQLException{
        SlotDetail slotDetail = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SLOT_DETAIL_BY_SLOTID_FIELDID);
                ptm.setString(1, slotId);
                ptm.setString(2, fieldId);
                rs = ptm.executeQuery();
                if (rs.next()) {
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
    
    public List<SlotDetail> getListSlotDetailByID(String fieldID) throws SQLException{
        List<SlotDetail> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_INFO_BY_CUSTOMER);
                ptm.setString(1, fieldID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String getSlotDetailID = rs.getString("slotDetailID");
                    
                    String slotID = rs.getString("slotID");
                    SlotDAO slotDAO = new SlotDAO();
                    Slot slot = slotDAO.getSlotByID(slotID);
                    
                    String getFieldID = rs.getString("fieldID");
                    FieldDAO fieldDAO = new FieldDAO();
                    Field field = fieldDAO.getFieldByID(getFieldID);
                    
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
    public ArrayList<SlotDetail> getListSlotFieldByID(String id) throws SQLException{
        ArrayList<SlotDetail> arr = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SLOT_BY_FIELD_ID);
//                    private static final String GET_SLOT_BY_FIELD_ID = "SELECT slotDetailID, slotID, fieldID, status "
//            + "FROM tblSlotDetail WHERE fieldId = ? ";
                ptm.setString(1,id);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String slotDetailID = rs.getString("slotDetailID");
                    String getSlotID = rs.getString("slotID");
                    SlotDAO slotDao = new SlotDAO();
                    String fieldID = rs.getString("fieldID");
                    FieldDAO fieldDao = new FieldDAO();
                    String status = rs.getString("status");
                    
                    arr.add(new SlotDetail(slotDetailID, slotDao.getSlotByID(getSlotID), fieldDao.getFieldByID(fieldID), status));
                                       
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
        return arr;
    }
    
    public String handleSlotDetailID(){
        int max = 999999;
        int min = 1;
        int random_double = (int) (Math.random() * (max - min + 1) + min);
        String s = String.valueOf(random_double);
        return "SD" + s;
    }

    public String createSlotDetailId() throws SQLException {
        String slotDetailID = handleSlotDetailID();
        boolean check = false;
        do {
            check = checkDuplicate(slotDetailID);
            if (check == false) {
                slotDetailID = handleSlotDetailID();
            }
        } while (check);
        return slotDetailID;
    }

    public boolean createSlotDetail(SlotDetail slotDetail) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_SLOT_DETAIL);
                ptm.setString(1, slotDetail.getSlotDetailID());
                ptm.setString(2, slotDetail.getSlot().getSlotId());
                ptm.setString(3, slotDetail.getField().getFieldId());
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
    
    public boolean checkDuplicate(String slotDetailID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SLOT_DETAIL_ID);
                ptm.setString(1, slotDetailID);
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
    
    public boolean checkSlotId(String slotId, String fieldId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_SLOT_ID);
                ptm.setString(1, slotId);
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
    
    public SlotDetail getSlotByID(String search) throws SQLException{
        SlotDetail slotDetail = null;
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
    
    
//    public static void main(String[] args) throws SQLException {
//        System.out.println("aaaa");
////        System.out.println(getListSlotFieldByID("FI1"));
//    }
    public static void aaaa() {
        System.out.println("aaa111");
        }
}
