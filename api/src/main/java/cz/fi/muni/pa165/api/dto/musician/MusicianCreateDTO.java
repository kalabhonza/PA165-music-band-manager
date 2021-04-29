package cz.fi.muni.pa165.api.dto.musician;

import cz.fi.muni.pa165.enums.Instrument;

import java.util.List;
import java.util.Objects;

/**
 * @author Aleš Paroulek
 */
public class MusicianCreateDTO {
    private String name;
    private String username;
    private String password;
    private List<Instrument> instruments;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MusicianCreateDTO)) return false;
        MusicianCreateDTO that = (MusicianCreateDTO) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getInstruments(), that.getInstruments());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getUsername());
    }

    @Override
    public String toString() {
        return "MusicianCreateDTO{" +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", instruments=" + instruments +
                '}';
    }
}
