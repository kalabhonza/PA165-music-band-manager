package cz.fi.muni.pa165.api.dto;


import java.sql.Time;
import java.util.Objects;

/**
 * Information about Song
 *
 * @author Igor Ignac
 */
public class SongDTO {

    private Long id;
    private String name;
    private Time duration;

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

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongDTO songDTO = (SongDTO) o;
        return Objects.equals(getId(), songDTO.getId()) &&
                Objects.equals(getName(), songDTO.getName()) &&
                Objects.equals(getDuration(), songDTO.getDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDuration());
    }


    @Override
    public String toString() {
        return "SongDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }
}
