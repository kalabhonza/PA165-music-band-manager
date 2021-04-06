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

    public Manager(Long id, String name, String userName, String password, Band band) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.band = band;
    }

    public Manager() {}

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
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return name.equals(manager.name) && userName.equals(manager.userName) && password.equals(manager.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userName, password, band);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", band=" + band +
                '}';
    }
}
