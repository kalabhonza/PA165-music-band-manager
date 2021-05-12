package cz.fi.muni.pa165.service.mapping.mapstruct;

import cz.fi.muni.pa165.api.dto.album.AlbumCreateDTO;
import cz.fi.muni.pa165.api.dto.album.AlbumDTO;
import cz.fi.muni.pa165.api.dto.album.AlbumUpdateDTO;
import cz.fi.muni.pa165.entities.Album;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    AlbumDTO mapToAlbumDTO(Album entity);

    Album mapToEntity(AlbumDTO dto);

    AlbumCreateDTO mapToAlbumCreateDTO(Album entity);

    Album mapToEntity(AlbumCreateDTO dto);

    AlbumUpdateDTO mapToAlbumUpdateDTO(Album entity);

    Album mapToEntity(AlbumUpdateDTO dto);

    List<AlbumDTO> mapToListDTO(List<Album> dto);
}
