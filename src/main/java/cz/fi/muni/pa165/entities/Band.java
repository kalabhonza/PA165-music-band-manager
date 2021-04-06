package cz.fi.muni.pa165.entities;


import cz.fi.muni.pa165.enums.Style;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Igor Ignác
 */
@Entity(name = "bands")
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private byte[] logo;

    @Column
    private Style style;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Musician> members = new HashSet<>();

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Album> albums = new HashSet<>();

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Tour> tours = new HashSet<>();

    @OneToOne(cascade = {CascadeType.ALL})
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

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
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
        if (o == null || getClass() != o.getClass()) return false;
        Band band = (Band) o;
        return name.equals(band.name) && Arrays.equals(logo, band.logo) && style == band.style && members.equals(band.members) && albums.equals(band.albums) && tours.equals(band.tours) && manager.equals(band.manager);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, style, members, albums, tours, manager);
        result = 31 * result + Arrays.hashCode(logo);
        return result;
    }

    @Override
    public String toString() {
        return "Band{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo=" + Arrays.toString(logo) +
                ", style=" + style +
                ", members=" + members +
                ", albums=" + albums +
                ", tours=" + tours +
                ", manager=" + manager +
                '}';
    }
}
