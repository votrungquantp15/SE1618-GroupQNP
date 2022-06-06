package dto;

public class Location {
    private String locationId;
    private String locationName;
    private String status;

    public Location() {
        this.locationId = "";
        this.locationName = "";
        this.status = "";
    }

    public Location(String locationId, String locationName, String status) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.status = status;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
