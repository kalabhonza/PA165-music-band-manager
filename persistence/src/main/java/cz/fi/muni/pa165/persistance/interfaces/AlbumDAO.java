package cz.fi.muni.pa165.persistance.interfaces;

import cz.fi.muni.pa165.entities.Album;

import java.util.List;

/**
 * @author Albert Sukaný
 */
public interface AlbumDAO {

    /**
     * Finds album by id
     * @param id unique identificator
     * @return Album or {@code null}, if there is no Album with requested id
     */
    Album findById(Long id);

    /**
     * Find Album by its id
     * @return list of all albums
     */

    List<Album> findAllAlbums();

    /**
     * creates new istance of class album
     * @param album to be created
     */
    void create(Album album);

    /**
     * update existing album
     * @param album to be changed
     */
    void update(Album album);

    /**
     * delete existing album
     * @param album to be deleted
     */
    void remove(Album album);

    /**
     * Find all albums with the specific name
     * @param name of the album
     * @return list of all albums having the given name
     */
    List<Album> findAlbumsByName(String name);



}
