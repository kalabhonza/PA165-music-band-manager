package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.album.AlbumDTO;

import java.util.List;

public interface AlbumFacade {

    /**
     * Finds album by id
     * @param id unique identificator
     * @return Album or {@code null}, if there is no Album with requested id
     */
    AlbumDTO findById(Long id);

    /**
     * Find Album by its id
     * @return list of all albums
     */

    List<AlbumDTO> findAllAlbums();

    /**
     * creates new istance of class album
     * @param album to be created
     */
    Long create(AlbumDTO album);

    /**
     * update existing album
     * @param album to be changed
     */
    AlbumDTO update(AlbumDTO album);

    /**
     * delete existing album
     * @param album to be deleted
     */
    void remove(AlbumDTO album);

    /**
     * Find all albums with the specific name
     * @param name of the album
     * @return list of all albums having the given name
     */
    List<AlbumDTO> findAlbumsByName(String name);




}
