package cz.fi.muni.pa165.api.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TourDTO {
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
        if (o == null || getClass() != o.getClass()) return false;
        TourDTO tourDTO = (TourDTO) o;
        return id.equals(tourDTO.id) && Name.equals(tourDTO.Name) && Objects.equals(concerts, tourDTO.concerts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name, concerts);
    }

    @Override
    public String toString() {
        return "TourDTO{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", concerts=" + concerts +
                '}';
    }


}
