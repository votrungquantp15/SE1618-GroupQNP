package dto;

public class District {
    private String districtId;
    private String districtName;
    private String status;

    public District() {
        this.districtId = "";
        this.districtName = "";
        this.status = "";
    }

    public District(String districtId, String districtName, String status) {
        this.districtId = districtId;
        this.districtName = districtName;
        this.status = status;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
