package dto;

public class Slot {
    private String slotId;
    private String timeStart;
    private String timeEnd;
    private String status;

    public Slot() {
        this.slotId = "";
        this.timeStart = "";
        this.timeEnd = "";
        this.status = "";
    }

    public Slot(String slotId, String timeStart, String timeEnd, String status) {
        this.slotId = slotId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.status = status;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
