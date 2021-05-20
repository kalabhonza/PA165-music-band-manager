package cz.fi.muni.pa165.api.dto.musician;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.enums.Instrument;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Aleš Paroulek
 */
public class MusicianUpdateDTO {
    @NotNull(message = "Id can not be null")
    private Long id;
    @NotEmpty(message = "Name can not be empty")
    private String name;
    @NotEmpty(message = "Username can not be empty")
    private String username;
    @NotEmpty(message = "Password can not be empty")
    private String password;
    @NotEmpty(message = "Instruments can not be empty")
    private List<Instrument> instruments;
    private Set<Band> offers = new HashSet<>();
    private Long bandId;

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

    public void setBand(Long bandId) {
        this.bandId = bandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MusicianUpdateDTO)) return false;
        MusicianUpdateDTO that = (MusicianUpdateDTO) o;
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
        return "MusicianUpdateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", instruments=" + instruments +
                ", offers=" + offers +
                ", band=" + bandId +
                '}';
    }
}
