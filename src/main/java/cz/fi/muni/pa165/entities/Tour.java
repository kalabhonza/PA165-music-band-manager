package cz.fi.muni.pa165.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany//TODO: (fetch = FetchType.EAGER)
    private Set<Concert> concerts = new HashSet<>();

    public Tour(Long id,String name, Set<Concert> concerts) {
        this.id = id;
        this.name = name;
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

    public Set<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(Set<Concert> concerts) {
        this.concerts = concerts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return name.equals(tour.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, concerts);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", concerts=" + concerts +
                '}';
    }
}
