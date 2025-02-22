package cz.fi.muni.pa165.api.dto.song;


import java.sql.Time;
import java.util.Objects;

/**
 * Information about Song
 *
 * @author Igor Ignác
 */
public class SongDTO {

    private Long id;
    private String name;
    private String duration;

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongDTO songDTO = (SongDTO) o;
        return Objects.equals(getName(), songDTO.getName()) &&
                Objects.equals(getDuration(), songDTO.getDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDuration());
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
