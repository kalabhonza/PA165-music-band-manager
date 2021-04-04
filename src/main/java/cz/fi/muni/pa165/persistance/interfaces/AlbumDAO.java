package cz.fi.muni.pa165.persistance.interfaces;

import cz.fi.muni.pa165.entities.Album;

import java.util.List;

/**
 * @author Albert Sukaný
 */
public interface AlbumDAO {
    Album getById(Long id);
    List<Album> getAllAlbums();
    void create(Album album);
    void update(Album album);
    void remove(Album album);
    List<Album> getAlbumsByName(String name);



}
