package cz.fi.muni.pa165.entities;

import cz.fi.muni.pa165.enums.Instrument;
import org.springframework.dao.DataAccessException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Aleš Paroulek
 */
@Entity(name = "musicians")
public class Musician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection
    private List<Instrument> instruments;

    @ManyToMany
    private Set<Band> offers = new HashSet<>();

    @Column
    private Long bandId;

    public Musician() {}

    public Musician(Long id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Set<Band> acceptOffer(Band band) {
        if (this.offers.contains(band)) {
            this.offers.remove(band);
            this.bandId = band.getId();
            return this.offers;
        } else {
            throw new DataAccessException("Band " + band.getId() + " is not in offers of musician " + this.id) {};
        }
    }

    public void setBand(Long bandId) {
        this.bandId = bandId;
    }

    public void declineOffer(Band band) {
        if (this.offers.contains(band)) {
            this.offers.remove(band);

        } else {
            throw new DataAccessException("Band " + band.getId() + " is not in offers of musician " + this.id) {};
        }

    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    public Set<Band> getOffers() {
        return offers;
    }

    public void setOffers(Set<Band> offers) {
        this.offers = offers;
    }

    public Long getBand() {
        return bandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Musician)) return false;
        Musician musician = (Musician) o;

        return Objects.equals(getName(), musician.getName()) &&
                Objects.equals(getUsername(), musician.getUsername()) &&
                Objects.equals(getInstruments(), musician.getInstruments()) &&
                Objects.equals(getBand(), musician.getBand()) &&
                Objects.equals(getOffers(), musician.getOffers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getUsername(), getBand());
    }

    @Override
    public String toString() {
        return "Musician{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", instruments=" + instruments +
                ", offers=" + offers +
                ", band=" + bandId +
                '}';
    }
}
