package cz.fi.muni.pa165.api.dto;

import cz.fi.muni.pa165.enums.Style;

import java.util.Objects;

public class BandCreateDTO {
    private String name;
    private Style style;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BandCreateDTO that = (BandCreateDTO) o;
        return Objects.equals(name, that.name) && style == that.style;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, style);
    }

    @Override
    public String toString() {
        return "BandCreateDTO{" +
                "name='" + name + '\'' +
                ", style=" + style +
                '}';
    }
}
