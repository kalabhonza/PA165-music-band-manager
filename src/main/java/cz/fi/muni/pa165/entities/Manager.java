package cz.fi.muni.pa165.entities;

import java.util.Objects;
import javax.persistence.*;

/**
 * @author Jan Kaláb
 */
@Entity(name = "managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @OneToOne
    private Band band;

    public Manager(Long id, String name, String userName, String password) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

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
        return getId().equals(manager.getId()) &&
                getName().equals(manager.getName()) &&
                getUserName().equals(manager.getUserName()) &&
                getPassword().equals(manager.getPassword()) &&
                Objects.equals(getBand(), manager.getBand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUserName(), getPassword(), getBand());
    }
}
