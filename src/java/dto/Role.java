package dto;

public class Role {
    private String roleId;
    private String roleName;
    private String status;

    public Role() {
        this.roleId = "";
        this.roleName = "";
        this.status = "";
    }

    public Role(String roleId, String roleName, String status) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.status = status;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
