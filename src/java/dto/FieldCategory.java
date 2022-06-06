package dto;

public class FieldCategory {
    private String fieldCateId;
    private String fieldCateName;
    private String status;

    public FieldCategory() {
        this.fieldCateId = "";
        this.fieldCateName = "";
        this.status = "";
    }

    public FieldCategory(String fieldCateId, String fieldCateName, String status) {
        this.fieldCateId = fieldCateId;
        this.fieldCateName = fieldCateName;
        this.status = status;
    }

    public String getFieldCateId() {
        return fieldCateId;
    }

    public void setFieldCateId(String fieldCateId) {
        this.fieldCateId = fieldCateId;
    }

    public String getFieldCateName() {
        return fieldCateName;
    }

    public void setFieldCateName(String fieldCateName) {
        this.fieldCateName = fieldCateName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
