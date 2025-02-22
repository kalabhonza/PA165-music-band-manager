package cz.fi.muni.pa165.api.dto.manager;

import java.util.Objects;

public class ManagerCreateDTO {
    private String name;
    private String userName;
    private String password;
    private Long bandId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getBand() {
        return bandId;
    }

    public void setBand(Long bandId) {
        this.bandId = bandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ManagerCreateDTO)) return false;
        ManagerCreateDTO that = (ManagerCreateDTO) o;
        return getName().equals(that.getName()) &&
                getUserName().equals(that.getUserName()) &&
                getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getUserName(), getPassword());
    }

    @Override
    public String toString() {
        return "ManagerCreateDTO{" +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
