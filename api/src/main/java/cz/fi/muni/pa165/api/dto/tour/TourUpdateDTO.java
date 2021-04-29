package cz.fi.muni.pa165.api.dto.tour;

import cz.fi.muni.pa165.api.dto.concert.ConcertDTO;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TourUpdateDTO {
    private Long id;
    private String Name;
    private Set<ConcertDTO> concerts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Set<ConcertDTO> getConcerts() {
        return concerts;
    }

    public void setConcerts(Set<ConcertDTO> concerts) {
        this.concerts = concerts;


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TourUpdateDTO)) return false;
        TourUpdateDTO that = (TourUpdateDTO) o;
        return getId().equals(that.getId()) &&
                getName().equals(that.getName()) &&
                Objects.equals(getConcerts(), that.getConcerts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getConcerts());
    }
}
