package cz.fi.muni.pa165.api.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Aleš Paroulek
 */
public class ConcertCreateDTO {
    private String name;
    private LocalDate date;

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
        if (!(o instanceof ConcertCreateDTO)) return false;
        ConcertCreateDTO that = (ConcertCreateDTO) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDate());
    }

    @Override
    public String toString() {
        return "ConcertCreateDTO{" +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }

}
