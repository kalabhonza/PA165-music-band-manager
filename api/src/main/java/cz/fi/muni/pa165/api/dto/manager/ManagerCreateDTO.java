package cz.fi.muni.pa165.api.dto.manager;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import java.util.Objects;

public class ManagerCreateDTO {
    private Long id;
    private String name;
    private String userName;
    private String password;
    private BandDTO band;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(o instanceof ManagerCreateDTO)) return false;
        ManagerCreateDTO that = (ManagerCreateDTO) o;
        return getId().equals(that.getId()) &&
                getName().equals(that.getName()) &&
                getUserName().equals(that.getUserName()) &&
                getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUserName(), getPassword());
    }

    @Override
    public String toString() {
        return "ManagerCreateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
