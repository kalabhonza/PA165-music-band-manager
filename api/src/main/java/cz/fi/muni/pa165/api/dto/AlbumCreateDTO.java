package cz.fi.muni.pa165.api.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AlbumCreateDTO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumCreateDTO that = (AlbumCreateDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "AlbumCreateDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
