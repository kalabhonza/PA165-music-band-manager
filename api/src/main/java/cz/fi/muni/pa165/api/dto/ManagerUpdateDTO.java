package cz.fi.muni.pa165.api.dto;

import java.util.Objects;

public class ManagerUpdateDTO {
    private Long id;
    private String name;
    private String userName;
    private String password;
    private BandDTO band;

    public Long getId() {
        return id;
    }

    public void setId(Long managerID) {
        this.id = managerID;
    }

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

    public BandDTO getBand() {
        return band;
    }

    public void setBand(BandDTO band) {
        this.band = band;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ManagerDTO)) return false;
        ManagerDTO that = (ManagerDTO) o;
        return getId().equals(that.getId()) &&
                getName().equals(that.getName()) &&
                getUserName().equals(that.getUserName()) &&
                getPassword().equals(that.getPassword()) &&
                Objects.equals(getBand(), that.getBand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUserName(), getPassword(), getBand());
    }

    @Override
    public String toString() {
        return "ManagerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", band=" + band +
                '}';
    }
}
