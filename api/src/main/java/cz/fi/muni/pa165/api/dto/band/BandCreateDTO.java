package cz.fi.muni.pa165.api.dto.band;

import cz.fi.muni.pa165.api.dto.ManagerDTO;
import cz.fi.muni.pa165.enums.Style;

import java.util.Objects;

public class BandCreateDTO {
    private String name;
    private Style style;
    private ManagerDTO manager;

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

    public ManagerDTO getManager() {
        return manager;
    }

    public void setManager(ManagerDTO manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BandCreateDTO that = (BandCreateDTO) o;
        return Objects.equals(getName(), that.getName()) && getStyle() == that.getStyle() && Objects.equals(getManager(), that.getManager());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStyle(), getManager());
    }

    @Override
    public String toString() {
        return "BandCreateDTO{" +
                "name='" + name + '\'' +
                ", style=" + style +
                ", manager=" + manager +
                '}';
    }
}
