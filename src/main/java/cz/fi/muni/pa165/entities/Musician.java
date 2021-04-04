package cz.fi.muni.pa165.entities;

import cz.fi.muni.pa165.enums.Instruments;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Aleš Paroulek
 */
@Entity
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

    @ManyToMany
    private Set<Instruments> instruments = new HashSet<>();

    @ManyToMany
    private Set<Band> offers = new HashSet<>();

    @ManyToOne
    private Band band;

    public Musician() {}

    public Musician(Long id, String name, String username, String password, Set<Instruments> instruments, Set<Band> offers, Tour tour) {
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

    public Set<Instruments> getInstruments() {
        return instruments;
    }

    public void setInstruments(Set<Instruments> instruments) {
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
}
