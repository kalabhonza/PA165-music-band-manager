package cz.fi.muni.pa165.api.dto;

import cz.fi.muni.pa165.api.dto.band.BandDTO;

public class ManagerUpdateDTO {
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
}
