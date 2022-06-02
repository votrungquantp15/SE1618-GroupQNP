package dto;


public class CustomerError {
     private String userIDError;
    private String fullNameError;
    private String addressError;
    private String birthError;
    private String phoneError;
    private String emailError;
    private String accNameError;
    private String passwordError;
    private String roleIDError;
    private String statusError;
    private String confirmError;
    private String messageError;

    public CustomerError() {
        this.userIDError = "";
        this.fullNameError = "";
        this.addressError = "";
        this.birthError = "";
        this.phoneError = "";
        this.emailError = "";
        this.accNameError = "";
        this.passwordError = "";
        this.roleIDError = "";
        this.statusError = "";
        this.confirmError="";
        this.messageError = "";
    }

    public CustomerError(String userIDError, String fullNameError, String addressError, String birthError, String phoneError, String emailError, String accNameError, String passwordError, String roleIDError, String statusError,String confirmError, String messageError) {
        this.userIDError = userIDError;
        this.fullNameError = fullNameError;
        this.addressError = addressError;
        this.birthError = birthError;
        this.phoneError = phoneError;
        this.emailError = emailError;
        this.accNameError = accNameError;
        this.passwordError = passwordError;
        this.roleIDError = roleIDError;
        this.statusError = statusError;
        this.confirmError = confirmError;
        this.messageError = messageError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getBirthError() {
        return birthError;
    }

    public void setBirthError(String birthError) {
        this.birthError = birthError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getAccNameError() {
        return accNameError;
    }

    public void setAccNameError(String accNameError) {
        this.accNameError = accNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }
    
    
    
}
