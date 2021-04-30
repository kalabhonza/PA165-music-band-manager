package cz.fi.muni.pa165.service.mapping.mapstruct;

import cz.fi.muni.pa165.api.dto.tour.TourCreateDTO;
import cz.fi.muni.pa165.api.dto.tour.TourDTO;
import cz.fi.muni.pa165.api.dto.tour.TourUpdateDTO;
import cz.fi.muni.pa165.entities.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TourMapper {
    TourDTO mapToTourDTO(Tour entity);

    Tour mapToEntity(TourDTO dto);

    TourCreateDTO mapToTourCreateDTO(Tour entity);

    Tour mapToEntity(TourCreateDTO dto);

    TourUpdateDTO mapToTourUpdateDTO(Tour entity);

    Tour mapToEntity(TourUpdateDTO dto);

    List<TourDTO> mapToListDTO(List<Tour> dto);

}
