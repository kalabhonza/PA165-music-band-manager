package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.tour.TourCreateDTO;
import cz.fi.muni.pa165.api.dto.tour.TourDTO;
import cz.fi.muni.pa165.api.dto.tour.TourUpdateDTO;
import cz.fi.muni.pa165.api.facade.TourFacade;
import cz.fi.muni.pa165.entities.Tour;
import cz.fi.muni.pa165.service.TourService;
import cz.fi.muni.pa165.service.mapping.mapstruct.AlbumMapperImpl;
import cz.fi.muni.pa165.service.mapping.mapstruct.TourMapperImpl;
import cz.fi.muni.pa165.service.mapping.modelmapper.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourFacadeImpl implements TourFacade {

    private TourService tourService;

    private TourMapperImpl tourMapper;

    @Autowired
    public TourFacadeImpl(TourService tourService, TourMapperImpl tourMapper) {
        this.tourService = tourService;
        this.tourMapper = tourMapper;
    }

    @Override
    public TourDTO findById(Long id) {
        Tour tour = tourService.findById(id);
        return tourMapper.mapToTourDTO(tour);
    }

    @Override
    public List<TourDTO> findAllTours() {
        List<Tour> tours = tourService.findAllTours();
        return tourMapper.mapToListDTO(tours);
    }

    @Override
    public Long create(TourCreateDTO tour) {
        Tour createdTour = tourMapper.mapToEntity(tour);
        return this.tourService.create(createdTour);

    }

    @Override
    public TourDTO update(TourUpdateDTO tour) {
        Tour updatedTour = tourMapper.mapToEntity(tour);
        updatedTour = tourService.update(updatedTour);
        return tourMapper.mapToTourDTO(updatedTour);
    }

    @Override
    public void remove(TourDTO tour) {
        Tour deletedTour = tourMapper.mapToEntity(tour);
        tourService.remove(deletedTour);
    }

    @Override
    public List<TourDTO> findByName(String name) {
        List<Tour> tours = tourService.findByName(name);
        return tourMapper.mapToListDTO(tours);
    }
}
