package cz.fi.muni.pa165.api.dto.musician;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.enums.Instrument;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Aleš Paroulek
 */
public class MusicianDTO {
    private Long id;
    private String name;
    private String username;
    private String password;
    private List<Instrument> instruments;
    private Set<BandDTO> offers = new HashSet<>();
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

    public Set<BandDTO> getOffers() {
        return offers;
    }

    public void setOffers(Set<BandDTO> offers) {
        this.offers = offers;
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
        if (!(o instanceof MusicianDTO)) return false;
        MusicianDTO that = (MusicianDTO) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getInstruments(), that.getInstruments()) &&
                Objects.equals(getBand(), that.getBand()) &&
                Objects.equals(getOffers(), that.getOffers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getUsername(), getBand());
    }

    @Override
    public String toString() {
        return "MusicianDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", instruments=" + instruments +
                ", offers=" + offers +
                ", band=" + band +
                '}';
    }
}
