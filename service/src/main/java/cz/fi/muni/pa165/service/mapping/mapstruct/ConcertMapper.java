package cz.fi.muni.pa165.service.mapping.mapstruct;

import cz.fi.muni.pa165.api.dto.concert.ConcertCreateDTO;
import cz.fi.muni.pa165.api.dto.concert.ConcertDTO;
import cz.fi.muni.pa165.api.dto.concert.ConcertUpdateDTO;
import cz.fi.muni.pa165.entities.Concert;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Aleš Paroulek
 */
@Mapper(componentModel = "spring")
public interface ConcertMapper {
    ConcertDTO mapToConcertDTO(Concert entity);

    Concert mapToEntity(ConcertDTO dto);

    ConcertCreateDTO mapToConcertCreateDTO(Concert entity);

    Concert mapToEntity(ConcertCreateDTO dto);

    ConcertUpdateDTO mapToConcertUpdateDTO(Concert entity);

    Concert mapToEntity(ConcertUpdateDTO dto);

    List<ConcertDTO> mapToListDTO(List<Concert> entities);
}
