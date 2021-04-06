package cz.fi.muni.pa165.entities;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Aleš Paroulek
 */
@Entity(name = "concerts")
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Tour tour;

    public Concert() {}

    public Concert(Long id, String placeName, LocalDate date, Tour tour) {
        this.id = id;
        this.name = placeName;
        this.date = date;
        this.tour = tour;
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

    public void setName(String placeName) {
        this.name = placeName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }



    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Concert)) return false;
        Concert concert = (Concert) o;

        return Objects.equals(getName(), concert.getName()) &&
                Objects.equals(getDate(), concert.getDate()) &&
                Objects.equals(getTour(), concert.getTour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDate(), getTour());
    }

    @Override
    public String toString() {
        return "Concert{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", tour=" + tour +
                '}';
    }
}
