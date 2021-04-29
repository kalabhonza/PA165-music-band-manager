package cz.fi.muni.pa165.service.mapping.mapstruct;

import cz.fi.muni.pa165.api.dto.band.BandCreateDTO;
import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.band.BandUpdateDTO;
import cz.fi.muni.pa165.api.dto.tour.TourCreateDTO;
import cz.fi.muni.pa165.api.dto.tour.TourDTO;
import cz.fi.muni.pa165.api.dto.tour.TourUpdateDTO;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Tour;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TourMapper {
    TourDTO mapToTourDTO(Tour entity);

    Tour mapToEntity(TourDTO dto);

    TourCreateDTO mapToTourCreateDTO(Tour entity);

    Tour mapToEntity(TourCreateDTO dto);

    TourUpdateDTO mapToTourUpdateDTO(Tour entity);

    Tour mapToEntity(TourUpdateDTO dto);

    List<TourDTO> mapToListDTO(List<Tour> dto);

}
