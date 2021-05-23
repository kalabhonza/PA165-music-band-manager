package cz.fi.muni.pa165.api.dto.band;

import cz.fi.muni.pa165.api.dto.album.AlbumDTO;
import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianDTO;
import cz.fi.muni.pa165.api.dto.tour.TourDTO;
import cz.fi.muni.pa165.enums.Style;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Information about Band
 *
 * @author Igor Ignác
 */
public class BandDTO {
    private Long id;
    private String name;
    private String logo;
    private Style style;
    private ManagerDTO manager;
    private Set<MusicianDTO> members = new HashSet<>();
    private Set<AlbumDTO> albums = new HashSet<>();
    private Set<TourDTO> tours = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public Style getStyle() {
        return style;
    }

    public ManagerDTO getManager() {
        return manager;
    }

    public Set<MusicianDTO> getMembers() {
        return members;
    }

    public Set<AlbumDTO> getAlbums() {
        return albums;
    }

    public Set<TourDTO> getTours() {
        return tours;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public void setManager(ManagerDTO manager) {
        this.manager = manager;
    }

    public void setMembers(Set<MusicianDTO> members) {
        this.members = members;
    }

    public void setAlbums(Set<AlbumDTO> albums) {
        this.albums = albums;
    }

    public void setTours(Set<TourDTO> tours) {
        this.tours = tours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BandDTO bandDTO = (BandDTO) o;
        return Objects.equals(getName(), bandDTO.getName()) &&
                Objects.equals(getManager(), bandDTO.getManager()) &&
                Objects.equals(getMembers(), bandDTO.getMembers()) &&
                Objects.equals(getAlbums(), bandDTO.getAlbums()) &&
                Objects.equals(getTours(), bandDTO.getTours());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getManager(), getMembers(), getAlbums(), getTours());
    }

    @Override
    public String toString() {
        return "BandDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager=" + manager +
                ", members=" + members +
                ", albums=" + albums +
                ", tours=" + tours +
                '}';
    }
}
