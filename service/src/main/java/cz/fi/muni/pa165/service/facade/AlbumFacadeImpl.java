package cz.fi.muni.pa165.service.facade;


import cz.fi.muni.pa165.api.dto.album.AlbumDTO;
import cz.fi.muni.pa165.api.facade.AlbumFacade;
import cz.fi.muni.pa165.entities.Album;
import cz.fi.muni.pa165.service.AlbumService;
import cz.fi.muni.pa165.service.mapping.mapstruct.AlbumMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumFacadeImpl implements AlbumFacade {

    private AlbumService albumService;

    @Autowired
    private AlbumMapperImpl albumMapper;

    @Autowired
    public AlbumFacadeImpl(AlbumService albumService){
        this.albumService = albumService;
    }

    @Override
    public AlbumDTO findById(Long id) {
        Album album = albumService.findById(id);
        return albumMapper.mapToAlbumDTO(album);
    }

    @Override
    public List<AlbumDTO> findAllAlbums() {
        List<Album> albums = albumService.findAllAlbums();
        return albumMapper.mapToListDTO(albums);
    }

    @Override
    public Long create(AlbumDTO album) {
        Album createdAlbum = albumMapper.mapToEntity(album);
        return this.albumService.create(createdAlbum);
    }

    @Override
    public AlbumDTO update(AlbumDTO album) {
        Album updatedAlbum = albumMapper.mapToEntity(album);
        updatedAlbum = albumService.update(updatedAlbum);
        return albumMapper.mapToAlbumDTO(updatedAlbum);
    }

    @Override
    public void remove(AlbumDTO album) {
        Album deletedAlbum = albumMapper.mapToEntity(album);
        albumService.remove(deletedAlbum);
    }

    @Override
    public List<AlbumDTO> findAlbumsByName(String name) {
        List<Album> albums = albumService.findAlbumsByName(name);
        return albumMapper.mapToListDTO(albums);
    }
}
