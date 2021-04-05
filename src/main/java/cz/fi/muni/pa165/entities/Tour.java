package cz.fi.muni.pa165.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Albert Sukaný
 */
@Entity(name = "tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Band band;

    @OneToMany
    private List<Concert> concerts;

    public Tour(Long id,String name, Band band, List<Concert> concerts) {
        this.id = id;
        this.name = name;
        this.band = band;
        this.concerts = concerts;
    }

    public Tour() { }

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

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public List<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return name.equals(tour.name) && band.equals(tour.band) && concerts.equals(tour.concerts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, band, concerts);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", band=" + band +
                ", concerts=" + concerts +
                '}';
    }
}
