package cz.fi.muni.pa165.api.dto.song;

import java.sql.Time;
import java.util.Objects;

public class SongCreateDTO {
    private String name;
    private String duration;

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
        SongCreateDTO that = (SongCreateDTO) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDuration(), that.getDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDuration());
    }

    @Override
    public String toString() {
        return "SongCreateDTO{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }
}
