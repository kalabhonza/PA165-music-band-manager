package cz.fi.muni.pa165.api.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Aleš Paroulek
 */
public class ConcertDTO {
    private Long id;
    private String name;
    private LocalDate date;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConcertDTO)) return false;
        ConcertDTO that = (ConcertDTO) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDate());
    }

    @Override
    public String toString() {
        return "ConcertDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
