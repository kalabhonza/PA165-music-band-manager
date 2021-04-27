package cz.fi.muni.pa165.api.dto.band;

import cz.fi.muni.pa165.api.dto.AlbumDTO;
import cz.fi.muni.pa165.api.dto.ManagerDTO;
import cz.fi.muni.pa165.api.dto.MusicianDTO;
import cz.fi.muni.pa165.api.dto.TourDTO;
import cz.fi.muni.pa165.enums.Style;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BandUpdateDTO {
    @NotNull(message = "Id can not be null")
    private Long id;
    @NotEmpty(message = "Name can not be empty")
    private String name;
    @NotNull(message = "Manager can not be empty")
    private ManagerDTO manager;
    private Style style;
    private byte[] logo;
    private Set<MusicianDTO> members = new HashSet<>();
    private Set<AlbumDTO> albums = new HashSet<>();
    private Set<TourDTO> tours = new HashSet<>();

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

    public ManagerDTO getManager() {
        return manager;
    }

    public void setManager(ManagerDTO manager) {
        this.manager = manager;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Set<MusicianDTO> getMembers() {
        return members;
    }

    public void setMembers(Set<MusicianDTO> members) {
        this.members = members;
    }

    public Set<AlbumDTO> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<AlbumDTO> albums) {
        this.albums = albums;
    }

    public Set<TourDTO> getTours() {
        return tours;
    }

    public void setTours(Set<TourDTO> tours) {
        this.tours = tours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BandUpdateDTO that = (BandUpdateDTO) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getManager(), that.getManager()) && getStyle() == that.getStyle();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getManager(), getStyle());
    }

    @Override
    public String toString() {
        return "BandUpdateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager=" + manager.getName() +
                ", style=" + style +
                ", logo=" + Arrays.toString(logo) +
                ", members=" + members +
                ", albums=" + albums +
                ", tours=" + tours +
                '}';
    }
}
