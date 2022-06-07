package dto;

public class User {

    private String userID;
    private String fullName;
    private String address;
    private City city;
    private String birth;
    private String phone;
    private String email;
    private String accName;
    private String password;
    private Role role;
    private String status;

    public User() {
        this.userID = "";
        this.fullName = "";
        this.address = "";
        this.city = null;
        this.birth = "";
        this.phone = "";
        this.email = "";
        this.accName = "";
        this.password = "";
        this.role = null;
        this.status = "";
    }

    public User(String userID, String fullName, String address, City city, String birth, String phone, String email, String accName, String password, Role role, String status) {
        this.userID = userID;
        this.fullName = fullName;
        this.address = address;
        this.city = city;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.accName = accName;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirth() {
        return birth;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
