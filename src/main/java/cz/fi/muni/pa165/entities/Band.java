package cz.fi.muni.pa165.entities;

import com.sun.istack.NotNull;
import cz.fi.muni.pa165.enums.Styles;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Igor Ignác
 */
@Entity
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private byte[] logo;

    @Column(nullable = false)
    private Styles style;

    @OneToMany(mappedBy = "musician")
    private Set<Musician> members = new HashSet<>();

    @OneToMany(mappedBy = "album")
    private Set<Album> albums = new HashSet<>();

    @OneToMany(mappedBy = "tour")
    private Set<Tour> tours = new HashSet<>();

    @OneToOne
    private Manager manager;

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

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Styles getStyle() {
        return style;
    }

    public void setStyle(Styles style) {
        this.style = style;
    }

    public Set<Musician> getMembers() {
        return members;
    }

    public void setMembers(Set<Musician> members) {
        this.members = members;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Band)) return false;
        Band band = (Band) o;

        if (!getName().equals(band.getName())) return false;
        if (!getManager().equals(band.getManager())) return false;
        return getStyle().equals(band.getStyle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
