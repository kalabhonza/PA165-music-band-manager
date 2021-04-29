package cz.fi.muni.pa165.api.dto;

import java.util.Objects;

public class TourCreateDTO {
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourCreateDTO that = (TourCreateDTO) o;
        return Objects.equals(Name, that.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name);
    }

    @Override
    public String toString() {
        return "TourCreateDTO{" +
                "Name='" + Name + '\'' +
                '}';
    }
}
