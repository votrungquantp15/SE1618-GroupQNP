package dto;

public class Field {
    private String fieldId;
    private String fieldName;
    private String description;
    private String image;
    private FieldCategory fieldCate;
    private double price;
    private User user;
    private Location location;
    private City city;
    private String status;

    public Field() {
        this.fieldId = "";
        this.fieldName = "";
        this.description = "";
        this.image = "";
        this.fieldCate = null;
        this.price = 0;
        this.user = null;
        this.location = null;
        this.city = null;
        this.status = "";
    }

    public Field(String fieldId, String fieldName, String description, String image, FieldCategory fieldCate, double price, User user, Location location, City city, String status) {
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.description = description;
        this.image = image;
        this.fieldCate = fieldCate;
        this.price = price;
        this.user = user;
        this.location = location;
        this.city = city;
        this.status = status;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public FieldCategory getFieldCate() {
        return fieldCate;
    }

    public void setFieldCate(FieldCategory fieldCate) {
        this.fieldCate = fieldCate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
