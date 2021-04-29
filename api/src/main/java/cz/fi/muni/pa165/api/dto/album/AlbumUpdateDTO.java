package cz.fi.muni.pa165.api.dto.album;

import cz.fi.muni.pa165.api.dto.song.SongDTO;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AlbumUpdateDTO {
    @NotNull(message = "Id can not be null")
    private Long id;
    @NotNull(message = "name can not be null")
    private String name;
    @NotNull(message = "songs can not be null")
    private Set<SongDTO> songs = new HashSet<>();

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumUpdateDTO that = (AlbumUpdateDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(songs, that.songs);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, songs);
    }


    @Override
    public String toString() {
        return "AlbumUpdateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", songs=" + songs +
                '}';
    }



}
