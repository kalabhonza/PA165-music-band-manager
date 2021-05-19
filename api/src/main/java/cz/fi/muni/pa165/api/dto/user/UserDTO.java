package cz.fi.muni.pa165.api.dto.user;

import java.util.Objects;

public class UserDTO {
    private Long id;
    private String userName;
    private boolean isManager;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return isManager() == userDTO.isManager() && getId().equals(userDTO.getId()) && getUserName().equals(userDTO.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), isManager());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", isManager=" + isManager +
                '}';
    }
}
