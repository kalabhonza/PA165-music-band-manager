package cz.fi.muni.pa165.entities;

import java.util.Objects;
import javax.persistence.*;

/**
 * @author Jan Kaláb
 */
@Entity
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerID;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @OneToOne
    private Band band;

    public Manager(Long managerID, String name, String userName, String password) {
        this.managerID = managerID;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public Long getManagerID() {
        return managerID;
    }

    public void setManagerID(Long managerID) {
        this.managerID = managerID;
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

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        Manager manager = (Manager) o;
        return getManagerID().equals(manager.getManagerID()) &&
                getName().equals(manager.getName()) &&
                getUserName().equals(manager.getUserName()) &&
                getPassword().equals(manager.getPassword()) &&
                Objects.equals(getBand(), manager.getBand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getManagerID(), getName(), getUserName(), getPassword(), getBand());
    }
}
