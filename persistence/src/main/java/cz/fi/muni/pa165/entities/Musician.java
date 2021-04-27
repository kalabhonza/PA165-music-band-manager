package cz.fi.muni.pa165.entities;

import cz.fi.muni.pa165.enums.Instrument;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Band band;

    public Musician() {}

    public Musician(Long id, String name, String username, String password, List<Instrument> instruments, Set<Band> offers) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.instruments = instruments;
        this.offers = offers;
    }

    public void acceptOffer(Band band) {
        if (offers.contains(band)) {
            this.band = band;
        }
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public void declineOffer(Band band) {
        offers.remove(band);
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

    public Band getBand() {
        return band;
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
                ", band=" + band +
                '}';
    }
}
