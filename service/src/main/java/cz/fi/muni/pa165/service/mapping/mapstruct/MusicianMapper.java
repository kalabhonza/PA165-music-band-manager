package cz.fi.muni.pa165.service.mapping.mapstruct;

import cz.fi.muni.pa165.api.dto.musician.MusicianCreateDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianDTO;
import cz.fi.muni.pa165.api.dto.musician.MusicianUpdateDTO;
import cz.fi.muni.pa165.entities.Musician;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Aleš Paroulek
 */
@Mapper(componentModel = "spring")
public interface MusicianMapper {
    MusicianDTO mapToMusicianDTO(Musician entity);

    Musician mapToEntity(MusicianDTO dto);

    MusicianCreateDTO mapToMusicianCreateDTO(Musician entity);

    Musician mapToEntity(MusicianCreateDTO dto);

    MusicianUpdateDTO mapToMusicianUpdateDTO(Musician entity);

    Musician mapToEntity(MusicianUpdateDTO dto);

    List<MusicianDTO> mapToListDTO(List<Musician> entities);
}
