package cz.fi.muni.pa165.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Albert Sukaný
 */
@Entity(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Band band;

    @OneToMany(mappedBy = "songs")
    private List<Song> songs;

    public Album(Long id,String name, Band band, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.band = band;
        this.songs = songs;
    }

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

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id) &&
                Objects.equals(name, album.name) &&
                Objects.equals(band, album.band) &&
                Objects.equals(songs, album.songs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, band, songs);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", band=" + band +
                ", songs=" + songs +
                '}';
    }
}
