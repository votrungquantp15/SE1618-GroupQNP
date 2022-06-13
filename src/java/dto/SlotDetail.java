/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author NITRO 5
 */
public class SlotDetail {

    private String slotDetailID;
    private Slot slot;
    private Field field;
    private String status;

    public SlotDetail() {
        this.slotDetailID = "";
        this.slot = null;
        this.field = null;
        this.status = "";
    }

    public SlotDetail(String slotDetailID, Slot slot, Field field, String status) {
        this.slotDetailID = slotDetailID;
        this.slot = slot;
        this.field = field;
        this.status = status;
    }

    public String getSlotDetailID() {
        return slotDetailID;
    }

    public void setSlotDetailID(String slotDetailID) {
        this.slotDetailID = slotDetailID;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
