package cz.fi.muni.pa165.service.mapping.mapstruct;

import cz.fi.muni.pa165.api.dto.song.SongCreateDTO;
import cz.fi.muni.pa165.api.dto.song.SongDTO;
import cz.fi.muni.pa165.api.dto.song.SongUpdateDTO;
import cz.fi.muni.pa165.entities.Song;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper used for mapping of entities to DTO
 *
 * @author Igor Ignác
 */
@Mapper(componentModel = "spring")
public interface SongMapper {
    SongDTO mapToSongDTO(Song entity);

    Song mapToEntity(SongDTO dto);

    SongCreateDTO mapToSongCreateDTO(Song entity);

    Song mapToEntity(SongCreateDTO dto);

    SongUpdateDTO mapToSongUpdateDTO(Song entity);

    Song mapToEntity(SongUpdateDTO dto);

    List<SongDTO> mapToListDTO(List<Song> dto);
}
