package cz.fi.muni.pa165.service.service.album;

import cz.fi.muni.pa165.entities.Album;
import cz.fi.muni.pa165.persistence.interfaces.AlbumDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private AlbumDAO albumDAO;

    @Autowired
    public AlbumServiceImpl(AlbumDAO albumDAO){
        this.albumDAO = albumDAO;
    }

    @Override
    public Album findById(Long id) {
        Album album = albumDAO.findById(id);
        if (album == null) {
            throw new DataAccessException("Band with id: " + id + "not found") {};
        }
        return album;

    }

    @Override
    public List<Album> findAllAlbums() {
        return albumDAO.findAllAlbums();
    }

    @Override
    public Long create(Album album) {
        return albumDAO.create(album);
    }

    @Override
    public Album update(Album album) {
        Album updatedAlbum = albumDAO.update(album);
        if (updatedAlbum == null) {
            throw new DataAccessException("Album with id: " + album.getId() + " does not exist") {};
        }
        if(!updatedAlbum.equals(album)){
            throw new DataAccessException("Album with id: " + album.getId() + " was not updated"){};
        }
        return updatedAlbum;
    }

    @Override
    public void remove(Album album) {
        albumDAO.remove(album);
        if (albumDAO.findById(album.getId()) != null){
            throw new DataAccessException("Album with id: " + album.getId() + "was not deleted"){};
        }
    }

    @Override
    public List<Album> findAlbumsByName(String name) {
        List<Album> albums = albumDAO.findAlbumsByName(name);
        if (albums == null){
            throw new DataAccessException("Album with name: " + name + "not found") {};
        }
        return albums;
    }
}
