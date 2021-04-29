package cz.fi.muni.pa165.service.facade;


import cz.fi.muni.pa165.api.dto.album.AlbumDTO;
import cz.fi.muni.pa165.api.facade.AlbumFacade;
import cz.fi.muni.pa165.entities.Album;
import cz.fi.muni.pa165.service.AlbumService;
import cz.fi.muni.pa165.service.mapping.modelmapper.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AlbumFacadeImpl implements AlbumFacade {

    private AlbumService albumService;
    private BeanMapper beanMapper;

    @Autowired
    public AlbumFacadeImpl(AlbumService albumService, BeanMapper beanMapper){
        this.albumService = albumService;
        this.beanMapper = beanMapper;
    }

    @Override
    public AlbumDTO findById(Long id) {
        Album album = albumService.findById(id);
        return beanMapper.mapTo(album, AlbumDTO.class);
    }

    @Override
    public List<AlbumDTO> findAllAlbums() {
        List<Album> albums = albumService.findAllAlbums();
        return beanMapper.mapTo(albums,AlbumDTO.class);
    }

    @Override
    public void create(AlbumDTO album) {
        Album createdAlbum = beanMapper.mapTo(album, Album.class);
        this.albumService.create(createdAlbum);
    }

    @Override
    public AlbumDTO update(AlbumDTO album) {
        Album updatedAlbum = beanMapper.mapTo(album,Album.class);
        updatedAlbum = albumService.update(updatedAlbum);
        return beanMapper.mapTo(updatedAlbum,AlbumDTO.class);
    }

    @Override
    public void remove(AlbumDTO album) {
        Album deletedAlbum = beanMapper.mapTo(album,Album.class);
        albumService.remove(deletedAlbum);
    }

    @Override
    public List<AlbumDTO> findAlbumsByName(String name) {
        List<Album> albums = albumService.findAlbumsByName(name);
        return beanMapper.mapTo(albums,AlbumDTO.class);
    }
}
