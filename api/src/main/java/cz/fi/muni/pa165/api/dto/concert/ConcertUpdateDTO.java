package cz.fi.muni.pa165.api.dto.concert;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Aleš Paroulek
 */
public class ConcertUpdateDTO {
    @NotNull(message = "Id can not be null")
    private Long id;
    @NotEmpty(message = "Name can not be empty")
    private String name;
    @NotNull(message = "Date can not be null")
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
        if (!(o instanceof  ConcertUpdateDTO)) return false;
        ConcertUpdateDTO that = (ConcertUpdateDTO) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDate());
    }

    @Override
    public String toString() {
        return " ConcertUpdateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
