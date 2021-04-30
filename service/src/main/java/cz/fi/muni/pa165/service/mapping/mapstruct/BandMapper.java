package cz.fi.muni.pa165.service.mapping.mapstruct;

import cz.fi.muni.pa165.api.dto.band.BandCreateDTO;
import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.band.BandUpdateDTO;
import cz.fi.muni.pa165.entities.Band;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
/**
 * Mapper used for mapping of entities to DTO
 *
 * @author Igor Ignác
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BandMapper {
    BandDTO mapToBandDTO(Band entity);

    Band mapToEntity(BandDTO dto);

    BandCreateDTO mapToBandCreateDTO(Band entity);

    Band mapToEntity(BandCreateDTO dto);

    BandUpdateDTO mapToBandUpdateDTO(Band entity);

    Band mapToEntity(BandUpdateDTO dto);

    List<BandDTO> mapToListDTO(List<Band> dto);
}
