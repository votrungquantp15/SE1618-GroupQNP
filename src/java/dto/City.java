package dto;

public class City {
    private String cityId;
    private String cityName;
    private String status;

    public City() {
        this.cityId = "";
        this.cityName = "";
        this.status = "";
    }

    public City(String cityId, String cityName, String status) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.status = status;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
