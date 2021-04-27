package cz.fi.muni.pa165.api.dto;

import cz.fi.muni.pa165.api.dto.song.SongDTO;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AlbumDTO {

    private Long id;
    private String name;
    private Set<SongDTO> songs = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumDTO albumDTO = (AlbumDTO) o;
        return id.equals(albumDTO.id) && name.equals(albumDTO.name) && Objects.equals(songs, albumDTO.songs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, songs);
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

    public Set<SongDTO> getSongs() {
        return songs;
    }

    public void setSongs(Set<SongDTO> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "AlbumDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", songs=" + songs +
                '}';
    }
}
